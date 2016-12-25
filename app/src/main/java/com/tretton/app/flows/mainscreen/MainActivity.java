package com.tretton.app.flows.mainscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.tretton.app.BusinessService;
import com.tretton.app.R;
import com.tretton.app.base.BaseAppCompatActivity;
import com.tretton.app.events.TweetClicked;
import com.tretton.app.flows.mainscreen.adapters.TweetListRecycleViewAdapter;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.flows.tweetdetailscreen.TweetDetailFragment;
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
    @Inject
    BusinessService businessService;

    @BindView(R.id.rv_activity_main_tweet_list)
    RecyclerView tweetList;
    @BindView(R.id.srl_activity_main_tweet_list)
    SwipeRefreshLayout tweetListRefresh;
    @BindView(R.id.pb_activity_main_loading)
    ProgressBar loading;

    @BindView(R.id.fl_activity_main_fragment_holder)
    FrameLayout fragmentHolder;

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
        presenter.getUserToken();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        bus.unregister(this);
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

                /**
                 * too see the refresh clearly uncomment here
                 * and comment line 135
                 */
//                tweetArrayList.clear();
//                tweetAdapter.notifyDataSetChanged();
//                new CountDownTimer(1000, 1000)
//                {
//                    @Override
//                    public void onTick(long l)
//                    {
//
//                    }
//
//                    @Override
//                    public void onFinish()
//                    {
//                        presenter.getTweets();
//                    }
//                }.start();

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

    @Override
    public void requestFailed()
    {
        loading.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "RequestFailed", Toast.LENGTH_LONG).show();
    }


    @SuppressWarnings("unused")
    @Subscribe
    public void onTweetClickedEvent(TweetClicked event)
    {

        fragmentHolder.setVisibility(View.VISIBLE);
        FragmentManager fm = getSupportFragmentManager();
        TweetDetailFragment tweetDetailFragment = new TweetDetailFragment();

        /**
         * if i had the full json i would use parceable to move data between fragments etc.
         * but i used the obj's in twitter kid model and they are not parcelable.
         */
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("TweetData", event.getTweet());
//        tweetDetailFragment.setArguments(bundle);


        businessService.setSelectedTweet(event.getTweet());
        fm.beginTransaction().replace(R.id.fl_activity_main_fragment_holder, tweetDetailFragment)
                .addToBackStack(TweetDetailFragment.class.getName())
                .commit();
        fragmentHolder.bringToFront();
    }

}
