package com.tretton.app.di.module;

import com.tretton.app.di.scopes.FragmentScope;
import com.tretton.app.flows.tweetdetailscreen.presenter.TweetDetailPresenter;
import com.tretton.app.flows.tweetdetailscreen.presenter.TweetDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentPresenterModule
{

    @Provides
    @FragmentScope

    public TweetDetailPresenter provideTweetDetailPresenter()
    {
        return new TweetDetailPresenterImpl();
    }

}
