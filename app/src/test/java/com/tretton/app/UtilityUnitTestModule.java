package com.tretton.app;


import com.tretton.app.di.Names;
import com.tretton.app.util.SchedulerHolder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static com.tretton.app.util.KeyClass.SCHEDULER_MAIN;

@Module
public class UtilityUnitTestModule
{
    @Provides
    @Singleton
    public SchedulerHolder provideSchedulerHolder(Scheduler workerScheduler,
                                                  @Named(SCHEDULER_MAIN) Scheduler
                                                          mainScheduler)
    {
        return new SchedulerHolder(workerScheduler, mainScheduler);
    }

    @Provides
    @Singleton
    @Named(SCHEDULER_MAIN)
    public Scheduler provideScheduler()
    {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    public Scheduler provideWorkerScheduler()
    {
        return Schedulers.immediate();
    }
}
