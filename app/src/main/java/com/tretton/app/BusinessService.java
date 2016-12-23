package com.tretton.app;


import com.tretton.app.restservice.RestService;

/**
 * Created by ilkinartuc on 10/08/16.
 */

public class BusinessService
{

    private RestService restService;

    public BusinessService(RestService restService)
    {
        this.restService = restService;
    }


}
