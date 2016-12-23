package com.tretton.app.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tretton.app.di.Names;
import com.tretton.app.restservice.RestService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module()
public final class RestServiceModule
{

    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideLoggingCapableHttpClient(HttpLoggingInterceptor.Level logLevel)
    {
        HttpLoggingInterceptor logging =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
                {
                    @Override
                    public void log(String message)
                    {
                        Timber.tag("retrofit").d(message);
                    }
                });

        logging.setLevel(logLevel);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson)
    {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    public RestService provideRestService(Retrofit.Builder retrofitBuilder,
                                          @Named(Names.BASE_URL_END_POINT) String baseUrl)
    {
        return retrofitBuilder.baseUrl(baseUrl)
                .build()
                .create(RestService.class);
    }


    @Provides
    @Singleton
    public Gson provideGsonConverter()
    {
        return new GsonBuilder().create();
    }


}
