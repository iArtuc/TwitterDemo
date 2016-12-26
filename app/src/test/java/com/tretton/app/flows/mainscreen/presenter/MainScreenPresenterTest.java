package com.tretton.app.flows.mainscreen.presenter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tretton.app.flows.mainscreen.view.MainActivityView;
import com.tretton.app.restservice.RestService;
import com.tretton.app.restservice.models.TwitterTokenObj;
import com.tretton.app.util.SchedulerHolder;
import com.tretton.app.util.SharedPreferencesManager;
import com.tretton.app.util.Util;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static com.tretton.app.UnitTestUtil.readTokenRestResponse;
import static com.tretton.app.UnitTestUtil.readTweetListRestResponse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainScreenPresenterTest
{
    //    @Mock
    private Gson restGson;
    private MainActivityPresenter presenter;
    private RestService restService;
    private SharedPreferencesManager sharedPreferencesManager;
    private MainActivityView view;


    SchedulerHolder schedulerHolder;

    private List<Tweet> tweetResponse;
    private TwitterTokenObj tokenResponse;

    @Before
    public void setUp() throws Exception
    {
//        AppUnitTestComponent.Initializer.init().presenterActivityUnitTestComponent().inject(this);

        MockitoAnnotations.initMocks(this);
        schedulerHolder = new SchedulerHolder(Schedulers.immediate(), Schedulers.immediate());
        restService = Mockito.mock(RestService.class);
        sharedPreferencesManager = Mockito.mock(SharedPreferencesManager.class);
        view = Mockito.mock(MainActivityView.class);
        presenter = new MainActivityPresenterImpl(restService, sharedPreferencesManager, schedulerHolder);
        restGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        tweetResponse = readTweetListRestResponse("user_timeline_response.json", restGson);
        tokenResponse = readTokenRestResponse("token_response.json", restGson);
        presenter.start(view);
    }

    @Test
    public void testTokenExists()
    {
        when(sharedPreferencesManager.getString(anyString(), anyString())).thenReturn("asdas");
        doReturn(Observable.just
                (tokenResponse)).when(restService).getToken(anyString(), anyString());
        doReturn(Observable.just(tweetResponse)).when(restService).getUserTimeline(anyString(),
                anyString(), anyInt());
        presenter.getUserToken();

        verify(restService).getUserTimeline(anyString(), anyString(), anyInt());

    }

    @Test
    public void testTokenDoesntExist()
    {
        when(sharedPreferencesManager.getString(anyString(), anyString())).thenReturn(null);
        doReturn(Observable.just
                (tokenResponse)).when(restService).getToken(anyString(), anyString());
        doReturn(Observable.just(tweetResponse)).when(restService).getUserTimeline(anyString(),
                anyString(), anyInt());


        Util util = spy(new Util());
        try
        {
            when(util.getBase64String(anyString())).thenReturn("Asd");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        presenter.getUserToken();
        verify(restService).getToken(anyString(), anyString());
        verify(restService).getUserTimeline(anyString(), anyString(), anyInt());
        verify(view).setTweets((List<Tweet>) any());

    }

    @Test
    public void testTokenGetFail()
    {
        when(sharedPreferencesManager.getString(anyString(), anyString())).thenReturn(null);
        doReturn(Observable.error(new Throwable())).when(restService).getToken(anyString(), anyString());
        presenter.getUserToken();
        verify(view).requestFailed();
    }

    @Test
    public void testGetTweetFail()
    {
        when(sharedPreferencesManager.getString(anyString(), anyString())).thenReturn("asdas");
        doReturn(Observable.just
                (tokenResponse)).when(restService).getToken(anyString(), anyString());
        doReturn(Observable.error(new Throwable())).when(restService).getUserTimeline(anyString(),
                anyString(), anyInt());
        doReturn(Observable.error(new Throwable())).when(restService).getToken(anyString(), anyString());
        presenter.getUserToken();
        verify(restService).getUserTimeline(anyString(), anyString(), anyInt());
        verify(view).requestFailed();
    }

}
