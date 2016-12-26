package com.tretton.app.flows.mainscreen.presenter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tretton.app.UnitTestUtil.readTweetListRestResponse;

public class MainScreenPresenterTest
{
//    @Inject
//    Gson restGson;
//    @Inject
//    MainActivityView view;
//    @Inject
//    MainActivityPresenter presenter;
//    @Inject
//    RestService restService;


    private Gson restGson;
    private MainActivityPresenter presenter;

    private List<Tweet> tweetResponse;

    @Before
    public void setUp() throws Exception
    {
//        AppUnitTestComponent.Initializer.init().presenterActivityUnitTestComponent().inject(this);

//        presenter = new MainActivityPresenterImpl();
        restGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        tweetResponse = readTweetListRestResponse("user_timeline_response.json", restGson);
//        presenter.start(view);
    }

    @Test
    public void test()
    {
        tweetResponse.size();
    }

//    @Test
//    public void testListenAllSubscriptionStatuses_ShouldCallShowLoading_WhenStatusTrue()
//    {
//        PublishSubject<Boolean> status = PublishSubject.create();
//        presenter.listenAllLoadingStatuses(status);
//        status.onNext(true);
//        onResumePauseCycleSubject.onNext(true);
//        verify(view).showLoading();
//        status.onNext(true);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testListenAllSubscriptionStatuses_ShouldCallShowContent_WhenStatusFalse()
//    {
//        PublishSubject<Boolean> status = PublishSubject.create();
//        presenter.listenAllLoadingStatuses(status);
//        status.onNext(true);
//        onResumePauseCycleSubject.onNext(true);
//        status.onNext(false);
//        onResumePauseCycleSubject.onNext(true);
//        verify(view).showLoading();
//        verify(view).showContent();
//        status.onNext(false);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testLoadBanners_ShouldCallShowBanners_WhenSuccess()
//    {
//        presenter.loadBanners().subscribe();
//        onViewCreatedSubject.onNext(null);
//        verify(view).showBanners(expectedBanners);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testLoadBanners_ShouldCallShowBannersWithEmptyList_WhenException()
//    {
//        when(bannersInteractor.getObservable()).thenReturn(
//                Observable.<List<BannerItem>>error(new RuntimeException()));
//
//        presenter.loadBanners().subscribe();
//        onViewCreatedSubject.onNext(null);
//
//        ArgumentCaptor<List<BannerItem>> captor = ArgumentCaptor.forClass((Class) List.class);
//        verify(view).showBanners(captor.capture());
//        assertEquals(0, captor.getValue().size());
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testPresenter_ShouldUnsubscribeAllObservables_WhenOnDestroy()
//    {
//        presenter.start(view);
//        onViewCreatedSubject.onNext(null);
//        reset(view);
//        onDestroySubject.onNext(null); //Destroyed
//
//        onViewCreatedSubject.onNext(null); //New events.. Should not be listened
//        onResumePauseCycleSubject.onNext(true);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testLoadCuisines_ShouldCallShowCuisines_WhenSuccess()
//    {
//        presenter.loadCuisines(PublishSubject.<Boolean>create()).subscribe();
//        onViewCreatedSubject.onNext(null);
//        verify(view).showCuisines(expectedCuisines);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testLoadCuisines_ShouldCallShowCuisinesWithEmptyList_WhenException()
//    {
//        when(cuisinesInteractor.getObservableWithSubsStatus(
//                Matchers.<Observer<Boolean>>any())).thenReturn(
//                Observable.<List<Cuisine>>error(new RuntimeException()));
//
//        presenter.loadCuisines(PublishSubject.<Boolean>create()).subscribe();
//        onViewCreatedSubject.onNext(null);
//
//        ArgumentCaptor<List<Cuisine>> captor = ArgumentCaptor.forClass((Class) List.class);
//        verify(view).showCuisines(captor.capture());
//        assertEquals(0, captor.getValue().size());
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testSuggesstedRestaurants_ShouldCallShowRestaurants_WhenSuccess()
//    {
//        presenter.loadSuggestedRestaurants(PublishSubject.<Boolean>create()).subscribe();
//        onViewCreatedSubject.onNext(null);
//        verify(view).showSuggestedRestaurants(expectedSuggestedRestaurants);
//        verifyNoMoreInteractions(view);
//    }
//
//    @Test
//    public void testSuggestedRestaurants_ShouldShowRestaurantsWithEmptyList_WhenException()
//    {
//        when(suggestedRestaurantsInteractor.getObservableWithSubsStatus(
//                Matchers.<Observer<Boolean>>any())).thenReturn(
//                Observable.<List<SuggestedRestaurant>>error(new RuntimeException()));
//
//        presenter.loadSuggestedRestaurants(PublishSubject.<Boolean>create()).subscribe();
//        onViewCreatedSubject.onNext(null);
//
//        ArgumentCaptor<List<SuggestedRestaurant>> captor = ArgumentCaptor.forClass((Class) List.class);
//        verify(view).showSuggestedRestaurants(captor.capture());
//        assertEquals(0, captor.getValue().size());
//        verifyNoMoreInteractions(view);
//    }
}
