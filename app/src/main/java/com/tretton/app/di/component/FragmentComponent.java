package com.tretton.app.di.component;


import com.tretton.app.di.module.FragmentModule;
import com.tretton.app.di.module.FragmentPresenterModule;
import com.tretton.app.di.scopes.FragmentScope;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {FragmentModule.class, FragmentPresenterModule.class})
public interface FragmentComponent
{
//    void inject(BakkalSelectionFragment fragment);
}
