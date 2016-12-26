package com.tretton.app.flows.tweetdetailscreen.presenter;


import com.tretton.app.flows.tweetdetailscreen.view.TweetDetailView;
import com.twitter.sdk.android.core.models.Tweet;

import static com.tretton.app.util.TimeDateConverter.timeConverter;

public class TweetDetailPresenterImpl implements TweetDetailPresenter
{
    private TweetDetailView tweetDetailView;
    private Tweet tweet;

    public TweetDetailPresenterImpl()
    {

    }


    @Override
    public void start(TweetDetailView view)
    {
        tweetDetailView = view;
    }

    @Override
    public void setTweetData(Tweet tweet)
    {
        this.tweet = tweet;
    }

    @Override
    public void setLayoutData()
    {
        if (tweet == null)
        {
            tweetDetailView.error();
            return;
        }
        tweetDetailView.setUserName(tweet.user.name);
        tweetDetailView.setUserScreenName(getTweeterUserName(tweet));
        tweetDetailView.setTweetTime(timeConverter(tweet.createdAt));
        tweetDetailView.setTweet(getClearTweet(tweet));
        tweetDetailView.setUserPicture(tweet.user.profileImageUrlHttps);
        tweetDetailView.setMediaPicture(getTweetMediaUrl(tweet));
        tweetDetailView.setreTweetCount(String.valueOf(tweet.retweetCount));
    }

    private String getTweeterUserName(Tweet tweet)
    {
        return "@" + tweet.user.screenName;
    }

    private String getClearTweet(Tweet tweet)
    {
        if (tweet.entities == null || tweet.entities.media == null || tweet.entities.media.size()
                == 0)
        {
            return tweet.text;
        }
        String result = tweet.text.replace(tweet.entities.media.get(0).url, "");
        return result;
    }


    private String getTweetMediaUrl(Tweet tweet)
    {

        if (tweet.entities == null || tweet.entities.media == null)
        {
            return "";
        }

        return tweet.entities.media.get(0).mediaUrlHttps;
    }

}

