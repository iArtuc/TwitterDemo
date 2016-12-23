package com.tretton.app.base;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.tretton.app.BaseApplication;
import com.tretton.app.di.component.ActivityComponent;
import com.tretton.app.di.component.AppComponent;
import com.tretton.app.di.module.ActivityModule;
import com.tretton.app.di.module.ActivityPresenterModule;
import butterknife.ButterKnife;

public class BaseAppCompatActivity extends AppCompatActivity
{


    private ActivityComponent component;

    public ActivityComponent getComponent()
    {
        if (component == null)
        {
            AppComponent twitterAppComponent = (AppComponent) getTwitterApplication()
                    .getComponent();
            setComponent(twitterAppComponent.plus(new ActivityModule(this), new
                    ActivityPresenterModule()));
        }
        return component;
    }

    @Override
    public void onContentChanged()
    {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    public void setComponent(ActivityComponent component)
    {
        this.component = component;
    }

    public BaseApplication getTwitterApplication()
    {
        return (BaseApplication) getApplication();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // If the user is offline, let them know they are not connected
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if ((ni == null) || (!ni.isConnected()))
        {
//            new CustomDialogOneButton(this,
//                    getResources().getString(R.string.device_offline_message));

        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        component = null;
    }


    public boolean isConnected()
    {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }


}
