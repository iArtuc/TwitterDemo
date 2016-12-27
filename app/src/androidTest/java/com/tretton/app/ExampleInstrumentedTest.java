package com.tretton.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.Espresso.*;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import com.tretton.app.flows.mainscreen.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.matches;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.tretton.app.debug", appContext.getPackageName());
    }

    @Test
    public void clickOnItem()
    {
        onView(withId(R.id.rv_activity_main_tweet_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));
    }

    @Test
    public void checkListItemCountIsTwenty()
    {
        onView(withId(R.id.rv_activity_main_tweet_list)).check(new RecyclerViewItemCountAssertion
                (20));
    }

    @Test
    public void checkIfFragmentDisplayed()
    {
        clickOnItem();
        onView(withText("RETWEETS")).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfBackWorksOnFragment(){
        checkIfFragmentDisplayed();
        pressBack();
        onView(withId(R.id.rv_activity_main_tweet_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_activity_main_tweet_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

    }
    @Test
    public void clickAllItemsOnRecyvlerView(){
        for(int i = 0; i <20;i++){
            onView(withId(R.id.rv_activity_main_tweet_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            pressBack();
        }
    }
}
