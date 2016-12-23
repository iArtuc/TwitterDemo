package com.tretton.app.di.component;


import com.tretton.app.BaseApplication;

import timber.log.Timber;

public interface BaseAppComponent
{

    void inject(BaseApplication application);

    Timber.Tree tree();
}
