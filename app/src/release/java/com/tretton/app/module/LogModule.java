package com.tretton.app.module;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
public class LogModule
{

    @Provides
    public Timber.Tree provideTimberTree()
    {
        return new Timber.DebugTree();
        //TODO change to crashlytics tree when implement crashlytics
    }


    @Provides
    public HttpLoggingInterceptor.Level provideServiceLogLevel()
    {
        return HttpLoggingInterceptor.Level.BODY;
    }
}
