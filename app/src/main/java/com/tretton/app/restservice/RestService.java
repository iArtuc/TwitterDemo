package com.tretton.app.restservice;


import com.tretton.app.restservice.models.TwitterTokenObj;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestService
{

    @GET("/1.1/statuses/user_timeline.json")
    Call<List<Tweet>> getUserTimeline(
            @Header("Authorization") String authorization,
            @Query("screen_name") String screenName,
            @Query("count") int count
    );

    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<TwitterTokenObj> getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType
    );

}
