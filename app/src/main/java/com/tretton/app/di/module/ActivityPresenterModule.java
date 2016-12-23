package com.tretton.app.di.module;


import com.tretton.app.di.scopes.ActivityScope;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenterImpl;
import com.tretton.app.restservice.RestService;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityPresenterModule
{
    @Provides
    @ActivityScope
    public MainActivityPresenter provideMainActivityPresenter(RestService restService)
    {
        return new MainActivityPresenterImpl(restService);
    }

}
