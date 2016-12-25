package com.tretton.app.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.tretton.app.BaseApplication;

public class SharedPreferencesManager
{
    SharedPreferences sharedPreferences;

    public SharedPreferencesManager(BaseApplication application)
    {
        sharedPreferences = application.getSharedPreferences(KeyClass.USER_INFO, Context
                .MODE_PRIVATE);
    }

    public void putString(String key, String value)
    {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String value)
    {
        return sharedPreferences.getString(key, value);
    }

    public void putBoolean(String key, boolean value)
    {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key)
    {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultVal)
    {
        return sharedPreferences.getBoolean(key, defaultVal);
    }
}
