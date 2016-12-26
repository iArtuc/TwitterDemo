package com.tretton.app.di.module;


import com.tretton.app.di.scopes.ActivityScope;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenterImpl;
import com.tretton.app.restservice.RestService;
import com.tretton.app.util.SchedulerHolder;
import com.tretton.app.util.SharedPreferencesManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityPresenterModule
{
    @Provides
    @ActivityScope
    public MainActivityPresenter provideMainActivityPresenter(RestService restService,
                                                              SharedPreferencesManager
                                                                      sharedPreferencesManager,
                                                              SchedulerHolder schedulerHolder)
    {
        return new MainActivityPresenterImpl(restService, sharedPreferencesManager, schedulerHolder);
    }

}
