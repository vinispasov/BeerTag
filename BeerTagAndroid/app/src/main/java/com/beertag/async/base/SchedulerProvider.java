package com.beertag.async.base;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler backgroundThread();
    Scheduler uiThread();
}
