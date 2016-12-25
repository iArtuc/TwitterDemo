package com.tretton.app.flows.tweetdetailscreen.presenter;


import com.tretton.app.flows.tweetdetailscreen.view.TweetDetailView;

public class TweetDetailPresenterImpl implements TweetDetailPresenter
{
    private TweetDetailView tweetDetailView;

    public TweetDetailPresenterImpl()
    {

    }


    @Override
    public void start(TweetDetailView view)
    {
        tweetDetailView = view;
    }
}

