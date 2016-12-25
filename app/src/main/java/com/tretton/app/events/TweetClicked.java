package com.tretton.app.events;

import com.twitter.sdk.android.core.models.Tweet;

public class TweetClicked
{

    private Tweet tweet;

    public TweetClicked(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public Tweet getTweet()
    {
        return tweet;
    }


}
