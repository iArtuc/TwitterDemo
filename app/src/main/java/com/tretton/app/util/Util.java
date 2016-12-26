package com.tretton.app.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Util
{
    public Util()
    {
        //To work with mockito i couldn't use static methods
    }
    /**
     * it should be
     * public static String getBase64String(String value)
     * and no need for util constructor;
     */
    public String getBase64String(String value) throws UnsupportedEncodingException
    {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }
}
