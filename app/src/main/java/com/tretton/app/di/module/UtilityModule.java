package com.tretton.app.di.module;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.picasso.Picasso;
import com.tretton.app.BaseApplication;
import com.tretton.app.BuildConfig;
import com.tretton.app.BusinessService;
import com.tretton.app.di.Names;
import com.tretton.app.util.SchedulerHolder;
import com.tretton.app.util.SharedPreferencesManager;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tretton.app.util.KeyClass.SCHEDULER_MAIN;

@Module
public class UtilityModule
{

    @Provides
    @Singleton
    public Bus provideBus()
    {
        return new Bus(ThreadEnforcer.MAIN);
    }

    @Provides
    @Singleton
    @Named(Names.DEFAULT_GSON)
    public Gson provideDefaultGson()
    {
        return new Gson();
    }

    @Provides
    @Singleton
    public Picasso providePicasso(BaseApplication baseApplication)
    {
        return new Picasso.Builder(baseApplication).loggingEnabled(BuildConfig.DEBUG)
                .executor(Executors.newSingleThreadExecutor())
                .build();
    }

    @Provides
    @Singleton
    public BusinessService provideBusinessService()
    {
        return new BusinessService();
    }

    @Provides
    @Singleton
    public SharedPreferencesManager provideSharedPreferencesManager(BaseApplication application)
    {
        return new SharedPreferencesManager(application);
    }

    @Provides
    @Singleton
    @Named(SCHEDULER_MAIN)
    public Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public Scheduler provideWorkerScheduler() {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Provides
    @Singleton
    public SchedulerHolder provideSchedulerHolder(Scheduler workerScheduler,
                                                  @Named(SCHEDULER_MAIN) Scheduler mainScheduler) {
        return new SchedulerHolder(workerScheduler, mainScheduler);
    }

}