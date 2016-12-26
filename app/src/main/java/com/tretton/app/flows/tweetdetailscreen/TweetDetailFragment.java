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
import android.widget.Toast;

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
            presenter.setTweetData(businessService.getSelectedTweet());
//            setLayoutData(businessService.getSelectedTweet());
        } else
        {
            getUserRetweet().setVisibility(View.VISIBLE);
            getUserRetweet().setText("Retweeted by " + businessService.getSelectedTweet().user.name);
            presenter.setTweetData(businessService.getSelectedTweet().retweetedStatus);
//            setLayoutData(businessService.getSelectedTweet().retweetedStatus);
        }

        presenter.setLayoutData();

        return view;
    }

    private void showError()
    {
        errorHolder.setVisibility(View.VISIBLE);
        errorHolder.bringToFront();
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

    @Override
    public void setUserName(String name)
    {
        getUserName().setText(name);
    }

    @Override
    public void setUserScreenName(String screenName)
    {
        getUserAccount().setText(screenName);
    }

    @Override
    public void setTweetTime(String tweetTime)
    {
        getUserTweetTime().setText(tweetTime);
    }

    @Override
    public void setTweet(String tweet)
    {
        getUserTweet().setText(tweet);
    }

    @Override
    public void setUserPicture(String pictureURL)
    {
        picasso.load(pictureURL).transform(new
                CircleStrokeTransformation(getContext(), getContext().getResources().getColor(R
                .color.DarkGray), 0)).error(R.drawable.error_icon).into
                (getUserPic());
    }

    @Override
    public void setMediaPicture(String mediaPictureUrl)
    {
        if (mediaPictureUrl.length() > 0)
        {
            getUserMediaHolder().setVisibility(View.VISIBLE);
            picasso.load(mediaPictureUrl).error(R.drawable.error_icon).into
                    (getUserMedia());
        } else
        {
            getUserMediaHolder().setVisibility(View.GONE);
        }
    }

    @Override
    public void setreTweetCount(String tweetCount)
    {
        getReTweetCount().setText(tweetCount);
    }

    @Override
    public void error()
    {
        Toast.makeText(getContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
    }
}
