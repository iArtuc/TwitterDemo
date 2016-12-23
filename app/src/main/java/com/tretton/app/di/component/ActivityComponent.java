package com.tretton.app.di.component;


import com.tretton.app.di.module.ActivityModule;
import com.tretton.app.di.module.ActivityPresenterModule;
import com.tretton.app.di.module.FragmentModule;
import com.tretton.app.di.scopes.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, ActivityPresenterModule.class})
public interface ActivityComponent extends BaseActivityComponent
{
    FragmentComponent plus(FragmentModule fragmentModule);
}
