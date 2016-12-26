package com.tretton.app.flows.mainscreen.presenter;


import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.restservice.RestService;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.tretton.app.util.KeyClass;
import com.tretton.app.util.SchedulerHolder;
import com.tretton.app.util.SharedPreferencesManager;
import com.tretton.app.util.Util;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.UnsupportedEncodingException;
import java.util.List;

import rx.Subscriber;

import static com.tretton.app.util.Constants.TOKEN_CREDENTIALS;
import static com.tretton.app.util.KeyClass.USER_TOKEN;

public class MainActivityPresenterImpl implements MainActivityPresenter
{
    private MainActivityView mainActivityView;
    private RestService restService;
    private SharedPreferencesManager sharedPreferencesManager;
    private String userToken = null;
    private SchedulerHolder schedulerHolder;
    private Util util;

    public MainActivityPresenterImpl(RestService restService, SharedPreferencesManager
            sharedPreferencesManager, SchedulerHolder schedulerHolder)
    {
        this.restService = restService;
        this.sharedPreferencesManager = sharedPreferencesManager;
        this.schedulerHolder = schedulerHolder;
        util = new Util();
    }

    @Override
    public void start(MainActivityView view)
    {
        mainActivityView = view;
    }

    @Override
    public void getUserToken()
    {
        if (userToken == null)
        {
            userToken = sharedPreferencesManager.getString(USER_TOKEN, null);
        }
        if (userToken == null)
        {
            getOAuth();
        } else
        {
            getTweetsFrom(userToken);
        }
    }

    @Override
    public void getOAuth()
    {
        try
        {
            restService.getToken("Basic " + util.getBase64String(TOKEN_CREDENTIALS),
                    "client_credentials").subscribeOn(schedulerHolder.getWorkerScheduler())
                    .observeOn(schedulerHolder.getMainScheduler()).subscribe(new Subscriber<TwitterTokenObj>()
            {
                @Override
                public void onCompleted()
                {

                }

                @Override
                public void onError(Throwable e)
                {
                    mainActivityView.requestFailed();
                }

                @Override
                public void onNext(TwitterTokenObj twitterTokenObj)
                {
                    userToken = "Bearer " + twitterTokenObj.accessToken;
                    sharedPreferencesManager.putString(KeyClass.USER_TOKEN, userToken);
                    getTweetsFrom(userToken);
                }
            });

        } catch (UnsupportedEncodingException e)
        {
            mainActivityView.requestFailed();
            e.printStackTrace();
        }
    }

    @Override
    public void getTweets()
    {
        getTweetsFrom(userToken);
    }

    public void getTweetsFrom(String accessToken)
    {

        restService.getUserTimeline(accessToken, "tretton37", 20).subscribeOn(schedulerHolder.getWorkerScheduler())
                .observeOn(schedulerHolder.getMainScheduler()).subscribe(new Subscriber<List<Tweet>>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {
                getOAuth();
            }

            @Override
            public void onNext(List<Tweet> tweets)
            {
                mainActivityView.setTweets(tweets);
            }
        });
    }
}

