package com.tretton.app.flows.tweetdetailscreen.view;


import com.tretton.app.base.BaseView;

public interface TweetDetailView extends BaseView
{

    void setUserName(String name);

    void setUserScreenName(String screenName);

    void setTweetTime(String tweetTime);

    void setTweet(String tweet);

    void setUserPicture(String pictureURL);

    void setMediaPicture(String mediaPictureUrl);

    void setreTweetCount(String tweetCount);

    void error();

}

