package com.tretton.app.flows.mainscreen;

import android.os.Bundle;
import android.widget.ListView;

import com.tretton.app.R;
import com.tretton.app.base.BaseAppCompatActivity;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;

/**
 * Created by ilkinartuc on 24/12/2016.
 */

public class MainScreenWithTwitterCode extends BaseAppCompatActivity
{

    @BindView(R.id.lv_activity_main_tweets)
    ListView tweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_twitter);


        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("tretton37")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

        setListAdapter(adapter);

    }

    private void setListAdapter(TweetTimelineListAdapter adapter)
    {
        tweetList.setAdapter(adapter);
    }
}
