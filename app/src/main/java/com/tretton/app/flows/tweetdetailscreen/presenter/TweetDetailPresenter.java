package com.tretton.app.flows.tweetdetailscreen.presenter;


import com.tretton.app.base.BasePresenter;
import com.tretton.app.flows.tweetdetailscreen.view.TweetDetailView;
import com.twitter.sdk.android.core.models.Tweet;

public interface TweetDetailPresenter extends BasePresenter<TweetDetailView>
{
    void setTweetData(Tweet tweet);

    void setLayoutData();
}
