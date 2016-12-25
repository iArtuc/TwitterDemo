package com.tretton.app.flows.mainscreen.view;


import com.tretton.app.base.BaseView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public interface MainActivityView extends BaseView
{
    void setTweets(List<Tweet> body);
//    void getAnonymousIDFailure();
}

