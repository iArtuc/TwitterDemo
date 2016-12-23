package com.tretton.app.di.module;



import com.tretton.app.base.BaseFragment;

import dagger.Module;

@Module
public class FragmentModule
{
    private final BaseFragment baseFragment;

    public FragmentModule(BaseFragment baseFragment)
    {
        this.baseFragment = baseFragment;
    }


}
