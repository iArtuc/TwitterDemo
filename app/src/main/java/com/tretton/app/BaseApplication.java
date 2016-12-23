package com.tretton.app;

import android.app.Application;

import com.google.gson.Gson;
import com.tretton.app.di.component.BaseAppComponent;
import com.tretton.app.di.component.DaggerAppComponent;
import com.tretton.app.di.module.AppModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

import static com.tretton.app.util.Constants.TWITTER_KEY;
import static com.tretton.app.util.Constants.TWITTER_SECRET;

public class BaseApplication extends Application
{



    public BusinessService getBusinessService()
    {
        return businessService;
    }

    @Inject
    BusinessService businessService;
    @Inject
    Gson gson;


    private BaseAppComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        ButterKnife.setDebug(BuildConfig.DEBUG);
        getComponent().inject(this);
        Timber.plant(component.tree());

//
//        final TwitterSession activeSession = TwitterCore.getInstance()
//                .getSessionManager().getActiveSession();
//
//        // example of custom OkHttpClient with logging HttpLoggingInterceptor
//        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        final OkHttpClient customClient = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor).build();
//
//        // pass custom OkHttpClient into TwitterApiClient and add to TwitterCore
//        final TwitterApiClient customApiClient;
//        if (activeSession != null) {
//            customApiClient = new TwitterApiClient(activeSession, customClient);
//            TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
//        } else {
//            customApiClient = new TwitterApiClient(customClient);
//            TwitterCore.getInstance().addGuestApiClient(customApiClient);
//        }
//
//
//        Timber.d("test");

    }

    public BaseAppComponent getComponent()
    {
        if (component == null)
        {
            setComponent(DaggerAppComponent.builder().appModule(
                    new AppModule(this)).build());
        }
        return component;
    }

    public void setComponent(BaseAppComponent component)
    {
        this.component = component;
    }
}
