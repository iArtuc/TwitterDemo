package com.tretton.app.flows.mainscreen.presenter;


import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.restservice.RestService;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.tretton.app.util.KeyClass;
import com.tretton.app.util.SharedPreferencesManager;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.tretton.app.util.Constants.TOKEN_CREDENTIALS;
import static com.tretton.app.util.KeyClass.USER_TOKEN;
import static com.tretton.app.util.Util.getBase64String;

public class MainActivityPresenterImpl implements MainActivityPresenter
{
    private MainActivityView mainActivityView;
    private RestService restService;
    private SharedPreferencesManager sharedPreferencesManager;
    private String userToken = null;

    public MainActivityPresenterImpl(RestService restService, SharedPreferencesManager sharedPreferencesManager)
    {
        this.restService = restService;
        this.sharedPreferencesManager = sharedPreferencesManager;
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
            restService.getToken("Basic " + getBase64String(TOKEN_CREDENTIALS),
                    "client_credentials").enqueue(new Callback<TwitterTokenObj>()
            {
                @Override
                public void onResponse(Call<TwitterTokenObj> call, Response<TwitterTokenObj> response)
                {

                    if (response.body() == null || response.errorBody() != null)
                    {
                        mainActivityView.requestFailed();
                        return;
                    }
                    userToken = "Bearer " + response.body().accessToken;
                    sharedPreferencesManager.putString(KeyClass.USER_TOKEN, userToken);
                    getTweetsFrom(userToken);
                }

                @Override
                public void onFailure(Call<TwitterTokenObj> call, Throwable t)
                {
                    mainActivityView.requestFailed();
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

    private void getTweetsFrom(String accessToken)
    {

        restService.getUserTimeline(accessToken, "tretton37", 20).enqueue(new Callback<List<Tweet>>()
        {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response)
            {
                if (response.body() == null || response.errorBody() != null)
                {
                    getOAuth();
                    return;
                }
                mainActivityView.setTweets(response.body());
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t)
            {
                mainActivityView.requestFailed();
                Timber.d("test");
            }
        });
    }
}

