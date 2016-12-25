package com.tretton.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeDateConverter
{
    public static String timeConverter(String time)
    {
        String date = time;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.US);
        Date testDate = null;
        try
        {
            testDate = sdf.parse(date);
        } catch (Exception ex)
        {
            return "";// can be changed to something else error case
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd", Locale.US);
        String newFormat = formatter.format(testDate);
        return newFormat;
    }
}
