package com.tretton.app.restservice.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ilkinartuc on 24/12/2016.
 */

public class TwitterTokenObj
{

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

}
