package com.tretton.app;

import android.app.Application;

import com.google.gson.Gson;
import com.tretton.app.di.component.BaseAppComponent;
import com.tretton.app.di.component.DaggerAppComponent;
import com.tretton.app.di.module.AppModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

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
