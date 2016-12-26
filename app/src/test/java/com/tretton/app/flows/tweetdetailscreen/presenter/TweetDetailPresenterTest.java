package com.tretton.app.flows.tweetdetailscreen.presenter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenter;
import com.tretton.app.flows.mainscreen.presenter.MainActivityPresenterImpl;
import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.flows.tweetdetailscreen.view.TweetDetailView;
import com.tretton.app.restservice.RestService;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.tretton.app.util.SchedulerHolder;
import com.tretton.app.util.SharedPreferencesManager;
import com.tretton.app.util.Util;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static com.tretton.app.UnitTestUtil.readTokenRestResponse;
import static com.tretton.app.UnitTestUtil.readTweetListRestResponse;
import static com.tretton.app.UnitTestUtil.readTweetRestResponse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetDetailPresenterTest
{
    //    @Mock
    private Gson restGson;
    private TweetDetailPresenter presenter;
    @Mock
    private TweetDetailView view;
    private Tweet tweetResponse;


    @Before
    public void setUp() throws Exception
    {
//        AppUnitTestComponent.Initializer.init().presenterActivityUnitTestComponent().inject(this);

        MockitoAnnotations.initMocks(this);
//        view = Mockito.mock(TweetDetailView.class);
        presenter = new TweetDetailPresenterImpl();
        restGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        tweetResponse = readTweetRestResponse("single_tweet_response.json", restGson);
//        tokenResponse = readTokenRestResponse("token_response.json", restGson);
        presenter.start(view);

    }

    @Test
    public void testNullResponse()
    {
        presenter.setTweetData(null);
        presenter.setLayoutData();
        verify(view,times(1)).error();
        verify(view,times(0)).setUserName(anyString());
    }

    @Test
    public void testNormalResponse()
    {
        presenter.setTweetData(tweetResponse);
        presenter.setLayoutData();
        verify(view,times(0)).error();
        verify(view,times(1)).setUserName(anyString());
        verify(view,times(1)).setUserScreenName(anyString());
        verify(view,times(1)).setUserPicture(anyString());
        verify(view,times(1)).setMediaPicture(anyString());
    }

}
