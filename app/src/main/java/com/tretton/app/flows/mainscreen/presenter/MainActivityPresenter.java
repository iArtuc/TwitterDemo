package com.tretton.app.flows.mainscreen.presenter;


import com.tretton.app.base.BasePresenter;
import com.tretton.app.flows.mainscreen.view.MainActivityView;

public interface MainActivityPresenter extends BasePresenter<MainActivityView>
{

    void getUserToken();

    void getOAuth();

    void getTweets();

    void getTweetsFrom(String userToken);

}
