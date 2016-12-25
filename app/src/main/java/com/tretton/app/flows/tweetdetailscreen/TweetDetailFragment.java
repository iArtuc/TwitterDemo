package com.tretton.app.flows.tweetdetailscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.tretton.app.BusinessService;
import com.tretton.app.R;
import com.tretton.app.base.BaseFragment;
import com.tretton.app.flows.tweetdetailscreen.presenter.TweetDetailPresenter;
import com.tretton.app.flows.tweetdetailscreen.view.TweetDetailView;
import com.tretton.app.ui.CircleStrokeTransformation;
import com.tretton.app.ui.CustomTextView;
import com.twitter.sdk.android.core.models.Tweet;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tretton.app.util.TimeDateConverter.timeConverter;

/**
 * Created by ilkinartuc on 25/12/2016.
 */
public class TweetDetailFragment extends BaseFragment implements TweetDetailView
{
    @Inject
    Bus bus;
    @Inject
    Picasso picasso;
    @Inject
    TweetDetailPresenter presenter;
    @Inject
    BusinessService businessService;

    private RelativeLayout view;
    @BindView(R.id.ll_tweet_list_fragment_error)
    LinearLayout errorHolder;


    @BindView(R.id.txt_tweet_list_fragment_user_name)
    CustomTextView userName;
    @BindView(R.id.txt_tweet_list_fragment_user_account)
    CustomTextView userAccount;
    @BindView(R.id.txt_tweet_list_fragment_user_time)
    CustomTextView userTweetTime;
    @BindView(R.id.txt_tweet_list_fragment_user_tweet)
    CustomTextView userTweet;
    @BindView(R.id.txt_tweet_list_fragment_user_retweet)
    CustomTextView userRetweet;


    @BindView(R.id.iv_tweet_list_fragment_user_pic)
    ImageView userPic;
    @BindView(R.id.iv_tweet_list_fragment_user_media)
    ImageView userMedia;

    @BindView(R.id.rl_tweet_list_fragment_user_media_holder)
    RelativeLayout userMediaHolder;


    @BindView(R.id.txt_tweet_list_fragment_tweet_retweet_count)
    CustomTextView reTweetCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = (RelativeLayout) inflater.inflate(R.layout.tweet_list_fragment, container,
                false);
        bindViews(view);

        if (businessService.getSelectedTweet() == null)
        {
            showError();
        }

        if (businessService.getSelectedTweet().retweetedStatus == null)
        {
            getUserRetweet().setVisibility(View.GONE);
            setLayoutData(businessService.getSelectedTweet());
        } else
        {
            getUserRetweet().setVisibility(View.VISIBLE);
            getUserRetweet().setText("Retweeted by " + businessService.getSelectedTweet().user.name);
            setLayoutData(businessService.getSelectedTweet().retweetedStatus);
        }


        return view;
    }

    private void setLayoutData(Tweet selectedTweet)
    {
        getUserName().setText(selectedTweet.user.name);
        getUserAccount().setText(getTweeterUserName(selectedTweet));
        getUserTweetTime().setText(timeConverter(selectedTweet.createdAt));
        getUserTweet().setText(getClearTweet(selectedTweet));
        picasso.load(selectedTweet.user.profileImageUrlHttps).transform(new
                CircleStrokeTransformation(getContext(), getContext().getResources().getColor(R
                .color.DarkGray), 0)).error(R.drawable.error_icon).into
                (getUserPic());

        String tweetMedia = getTweetMediaUrl(selectedTweet);
        if (tweetMedia.length() > 0)
        {
            getUserMediaHolder().setVisibility(View.VISIBLE);
            picasso.load(tweetMedia).error(R.drawable.error_icon).into
                    (getUserMedia());
        } else
        {
            getUserMediaHolder().setVisibility(View.GONE);
        }

        getReTweetCount().setText(String.valueOf(selectedTweet.retweetCount));


    }

    private void showError()
    {
        errorHolder.setVisibility(View.VISIBLE);
        errorHolder.bringToFront();
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

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        getComponent().inject(this);
        presenter.start(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        bus.register(this);
    }

    public CustomTextView getUserName()
    {
        return userName;
    }

    public CustomTextView getUserAccount()
    {
        return userAccount;
    }

    public CustomTextView getUserTweetTime()
    {
        return userTweetTime;
    }

    public CustomTextView getUserTweet()
    {
        return userTweet;
    }

    public CustomTextView getUserRetweet()
    {
        return userRetweet;
    }

    public ImageView getUserPic()
    {
        return userPic;
    }

    public ImageView getUserMedia()
    {
        return userMedia;
    }

    public RelativeLayout getUserMediaHolder()
    {
        return userMediaHolder;
    }

    public CustomTextView getReTweetCount()
    {
        return reTweetCount;
    }

}
