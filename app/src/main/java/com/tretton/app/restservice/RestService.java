package com.tretton.app.restservice;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestService
{


    @POST("test")
    Call<String> test(@Body String request);


}
