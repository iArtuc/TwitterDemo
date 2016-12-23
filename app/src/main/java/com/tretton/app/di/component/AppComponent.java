package com.tretton.app.di.component;

import com.tretton.app.di.module.ActivityModule;
import com.tretton.app.di.module.ActivityPresenterModule;
import com.tretton.app.di.module.AppModule;
import com.tretton.app.di.module.EndPointModule;
import com.tretton.app.di.module.LogModule;
import com.tretton.app.di.module.RestServiceModule;
import com.tretton.app.di.module.UtilityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class, UtilityModule.class, LogModule.class,
        EndPointModule.class, RestServiceModule.class
})
public interface AppComponent extends BaseAppComponent
{

    ActivityComponent plus(ActivityModule activityModule,
                           ActivityPresenterModule activityPresenterModule);
}
