package com.tretton.app.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by ilkinartuc on 24/12/2016.
 */

public class Util
{
    public static String getBase64String(String value) throws UnsupportedEncodingException
    {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }
}
