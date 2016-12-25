package com.tretton.app.flows.mainscreen.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tretton.app.R;
import com.tretton.app.ui.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ilkinartuc on 17/08/16.
 */
public class ViewHolderTweetListCustomItem extends RecyclerView.ViewHolder
{


    @BindView(R.id.txt_custom_tweet_item_user_name)
    CustomTextView userName;
    @BindView(R.id.txt_custom_tweet_item_user_account)
    CustomTextView userAccount;
    @BindView(R.id.txt_custom_tweet_item_user_time)
    CustomTextView userTweetTime;
    @BindView(R.id.txt_custom_tweet_item_user_tweet)
    CustomTextView userTweet;


    @BindView(R.id.iv_custom_tweet_item_user_pic)
    ImageView userPic;
    @BindView(R.id.iv_custom_tweet_item_user_media)
    ImageView userMedia;


    @BindView(R.id.rl_custom_tweet_item_user_media_holder)
    RelativeLayout userMediaHolder;

    public ViewHolderTweetListCustomItem(View v)
    {
        super(v);
        ButterKnife.bind(this, v);

    }


    public CustomTextView getUserName()
    {
        return userName;
    }

    public CustomTextView getUserTweetTime()
    {
        return userTweetTime;
    }

    public CustomTextView getUserAccount()
    {
        return userAccount;
    }

    public CustomTextView getUserTweet()
    {
        return userTweet;
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
}

