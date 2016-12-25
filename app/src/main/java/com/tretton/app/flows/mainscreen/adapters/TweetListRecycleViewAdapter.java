package com.tretton.app.flows.mainscreen.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.tretton.app.R;
import com.tretton.app.events.TweetClicked;
import com.tretton.app.ui.CircleStrokeTransformation;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tretton.app.util.TimeDateConverter.timeConverter;


/**
 * Created by ilkinartuc on 17/08/16.
 */
public class TweetListRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    // The items to display in your RecyclerView

    public Context getContext()
    {
        return context;
    }

    private Context context;
    private List<Tweet> items;
    private Bus bus;
    private Picasso picasso;


    // Provide a suitable constructor (depends on the kind of dataset)
    public TweetListRecycleViewAdapter(Context cntxt, ArrayList<Tweet> item, Bus bus,
                                       Picasso picasso)
    {
        this.items = item;
        this.context = cntxt;
        this.bus = bus;
        this.picasso = picasso;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        //More to come

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v1 = inflater.inflate(R.layout.custom_tweet_item, viewGroup, false);
        viewHolder = new ViewHolderTweetListCustomItem(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        //More to come

        ViewHolderTweetListCustomItem vh1 = (ViewHolderTweetListCustomItem) viewHolder;
//        configureViewHolderTweetCustomItem(vh1, position);
//      for performance setviews on a new thread
        Observable.just(configureViewHolderTweetCustomItem(vh1,
                position)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();

    }


    private String configureViewHolderTweetCustomItem(final ViewHolderTweetListCustomItem vh1,
                                                      final int position)
    {

        if (items.get(position).retweetedStatus == null)
        {
            vh1.getUserRetweet().setVisibility(View.GONE);
            setTweetData(vh1, items.get(position));
        } else
        {
            vh1.getUserRetweet().setVisibility(View.VISIBLE);
            vh1.getUserRetweet().setText("Retweeted by " + items.get(position).user.name);
            setTweetData(vh1, items.get(position).retweetedStatus);
        }

        vh1.getUserTweetRoot().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bus.post(new TweetClicked(items.get(position)));
            }
        });

        return null;
    }

    private String getTweetMediaUrl(Tweet tweet)
    {

        if (tweet.entities == null || tweet.entities.media == null)
        {
            return "";
        }

        return tweet.entities.media.get(0).mediaUrlHttps;
    }

    private String getClearTweet(Tweet tweet)
    {
        if (tweet.entities == null || tweet.entities.media == null || tweet.entities.media.size()
                == 0)
        {
            return tweet.text;
        }
        String result = tweet.text.replace(tweet.entities.media.get(0).url, "");
        return result;
    }

    private String getTweeterUserName(Tweet tweet)
    {
        return "@" + tweet.user.screenName;
    }

    private void setTweetData(ViewHolderTweetListCustomItem vh1, Tweet tweet)
    {
        vh1.getUserName().setText(tweet.user.name);
        vh1.getUserAccount().setText(getTweeterUserName(tweet));
        vh1.getUserTweetTime().setText(timeConverter(tweet.createdAt));
        vh1.getUserTweet().setText(getClearTweet(tweet));
        picasso.load(tweet.user.profileImageUrlHttps).transform(new
                CircleStrokeTransformation(getContext(), getContext().getResources().getColor(R
                .color.DarkGray), 0)).error(R.drawable.error_icon).into
                (vh1.getUserPic());

        String tweetMedia = getTweetMediaUrl(tweet);
        if (tweetMedia.length() > 0)
        {
            vh1.getUserMediaHolder().setVisibility(View.VISIBLE);
            picasso.load(tweetMedia).error(R.drawable.error_icon).into
                    (vh1.getUserMedia());
        } else
        {
            vh1.getUserMediaHolder().setVisibility(View.GONE);
        }

    }

}

