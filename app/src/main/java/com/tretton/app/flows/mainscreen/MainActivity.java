package com.tretton.app.flows.mainscreen;

import android.os.Bundle;
import android.widget.ListView;

import com.tretton.app.R;
import com.tretton.app.base.BaseAppCompatActivity;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements MainActivityView
{

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        presenter.start(this);

        presenter.getTweets();

    }

}
