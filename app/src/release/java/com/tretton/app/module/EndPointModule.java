package com.tretton.app.module;


import com.tretton.app.di.Names;
import com.tretton.app.restservice.BaseUrl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EndPointModule
{

    @Provides
    @Singleton
    @Named(Names.BASE_URL_END_POINT)
    public String provideBaseUrl()
    {
        return BaseUrl.SERVICE_BASE_URL;
    }

}
