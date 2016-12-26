package com.tretton.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.View;

import com.tretton.app.di.component.ActivityComponent;
import com.tretton.app.di.component.FragmentComponent;
import com.tretton.app.di.module.FragmentModule;

import butterknife.ButterKnife;


public class BaseFragment extends Fragment
{
    private FragmentComponent component;
    private BaseAppCompatActivity activity;
//    public AndroidDefaultProgressDialog cDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        activity = (BaseAppCompatActivity) context;
    }

    protected void bindViews(View view)
    {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    public FragmentComponent getComponent()
    {
        if (component == null)
        {
            ActivityComponent activityComponent = (ActivityComponent) activity.getComponent();
            setComponent(activityComponent.plus(new FragmentModule(this)));
        }
        return component;
    }

    @VisibleForTesting
    public void setComponent(FragmentComponent component)
    {
        this.component = component;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        activity = null;
        component = null;
    }

    public BaseAppCompatActivity getBaseActivity()
    {
        return activity;
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


}
