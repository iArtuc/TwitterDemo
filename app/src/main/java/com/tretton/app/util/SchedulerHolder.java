package com.tretton.app.util;

import rx.Scheduler;

public class SchedulerHolder
{
  private final Scheduler workerScheduler;
  private final Scheduler mainScheduler;

  public SchedulerHolder(Scheduler workerScheduler, Scheduler mainScheduler) {
    this.workerScheduler = workerScheduler;
    this.mainScheduler = mainScheduler;
  }

  public Scheduler getWorkerScheduler() {
    return workerScheduler;
  }

  public Scheduler getMainScheduler() {
    return mainScheduler;
  }
}
