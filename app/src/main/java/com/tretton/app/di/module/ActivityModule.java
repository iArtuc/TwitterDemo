package com.tretton.app.di.module;


import com.tretton.app.base.BaseAppCompatActivity;

import dagger.Module;

@Module
public class ActivityModule
{
    private final BaseAppCompatActivity baseActivity;

    public ActivityModule(BaseAppCompatActivity baseAppCompatActivity)
    {
        this.baseActivity = baseAppCompatActivity;
    }

}
