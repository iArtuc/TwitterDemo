package com.tretton.app.flows.mainscreen;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.tretton.app.R;
import com.tretton.app.base.BaseAppCompatActivity;
import com.tretton.app.flows.mainscreen.adapters.TweetListRecycleViewAdapter;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements MainActivityView
{

    @Inject
    MainActivityPresenter presenter;
    @Inject
    Picasso picasso;
    @Inject
    Bus bus;

    @BindView(R.id.rv_activity_main_tweet_list)
    RecyclerView tweetList;
    @BindView(R.id.srl_activity_main_tweet_list)
    SwipeRefreshLayout tweetListRefresh;
    @BindView(R.id.pb_activity_main_loading)
    ProgressBar loading;

    private TweetListRecycleViewAdapter tweetAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Tweet> tweetArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        presenter.start(this);
        initLayout();
        setAdapters();
        presenter.getTweets();

    }

    private void setAdapters()
    {
        tweetAdapter = new TweetListRecycleViewAdapter(getApplicationContext(), tweetArrayList,
                bus, picasso);

        tweetList.setAdapter(tweetAdapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        tweetList.setLayoutManager(linearLayoutManager);
        tweetList.setItemAnimator(new DefaultItemAnimator());
        tweetList.setHasFixedSize(true);
    }

    private void initLayout()
    {

        tweetArrayList = new ArrayList<>();

        tweetListRefresh.setColorSchemeColors(getResources().getColor(R.color.Blue), getResources().getColor(R.color.Red), getResources().getColor(R.color.Orange));
        tweetListRefresh.bringToFront();

        tweetListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                presenter.getTweets();
            }
        });

    }

    @Override
    public void setTweets(List<Tweet> body)
    {
        loading.setVisibility(View.GONE);
        tweetArrayList.clear();
        if (body == null || body.size() == 0)
        {
            return;
        }
        for (Tweet dataObj : body)
        {
            tweetArrayList.add(dataObj);
        }
        tweetAdapter.notifyDataSetChanged();
        tweetListRefresh.setRefreshing(false);
    }
}
