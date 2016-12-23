package com.tretton.app.flows.mainscreen.presenter;


import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.restservice.RestService;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.tretton.app.util.Constants.TOKEN_CREDENTIALS;
import static com.tretton.app.util.Util.getBase64String;

public class MainActivityPresenterImpl implements MainActivityPresenter
{
    private MainActivityView mainActivityView;
    private RestService restService;


    public MainActivityPresenterImpl(RestService restService)
    {
        this.restService = restService;
    }


    @Override
    public void start(MainActivityView view)
    {
        mainActivityView = view;
    }

    @Override
    public void getTweets()
    {

        try
        {
            restService.getToken("Basic " + getBase64String(TOKEN_CREDENTIALS),
                    "client_credentials").enqueue(new Callback<TwitterTokenObj>()
            {
                @Override
                public void onResponse(Call<TwitterTokenObj> call, Response<TwitterTokenObj> response)
                {
                    Timber.d("test");
                    getTweetsFrom(response.body().accessToken);
                }

                @Override
                public void onFailure(Call<TwitterTokenObj> call, Throwable t)
                {
                    Timber.d("test");
                }
            });
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

    }

    private void getTweetsFrom(String accessToken)
    {

        restService.getUserTimeline("Bearer " + accessToken, "tretton37", 20).enqueue(new Callback<List<Tweet>>()
        {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response)
            {
                Timber.d("test");
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t)
            {
                Timber.d("test");
            }
        });
    }
}

