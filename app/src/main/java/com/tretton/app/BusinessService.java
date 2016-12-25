package com.tretton.app;


import com.twitter.sdk.android.core.models.Tweet;

public class BusinessService
{


    private Tweet selectedTweet;

    public BusinessService()
    {
    }

    public Tweet getSelectedTweet()
    {
        return selectedTweet;
    }

    public void setSelectedTweet(Tweet selectedTweet)
    {
        this.selectedTweet = selectedTweet;
    }
}
