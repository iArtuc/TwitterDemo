package com.tretton.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public final class UnitTestUtil
{

    private UnitTestUtil()
    {
    }

    public static List<Tweet> readTweetListRestResponse(String name, Gson gson) throws Exception
    {

        String path = UnitTestUtil.class.getClassLoader().getResource("assets/" + name).getPath()
                .replace("%20", "\\ ");
        File file = new File(path);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        return gson.fromJson(reader, new TypeToken<List<Tweet>>()
        {
        }.getType());
    }

    public static TwitterTokenObj readTokenRestResponse(String name, Gson gson) throws Exception
    {

        String path = UnitTestUtil.class.getClassLoader().getResource("assets/" + name).getPath()
                .replace("%20", "\\ ");
        File file = new File(path);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        return gson.fromJson(reader, TwitterTokenObj.class);
    }

    public static Tweet readTweetRestResponse(String name, Gson gson) throws Exception
    {

        String path = UnitTestUtil.class.getClassLoader().getResource("assets/" + name).getPath()
                .replace("%20", "\\ ");
        File file = new File(path);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
        return gson.fromJson(reader, Tweet.class);
    }




}

