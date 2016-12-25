package com.tretton.app;

import android.app.Application;

import com.google.gson.Gson;
import com.tretton.app.di.component.BaseAppComponent;
import com.tretton.app.di.component.DaggerAppComponent;
import com.tretton.app.di.module.AppModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
