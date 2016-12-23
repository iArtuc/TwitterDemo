package com.tretton.app.di.module;

import com.tretton.app.BaseApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{

    private final BaseApplication application;

    public AppModule(BaseApplication application)
    {
        this.application = application;
    }

    @Provides
    public BaseApplication provideApplication()
    {
        return application;
    }
}
