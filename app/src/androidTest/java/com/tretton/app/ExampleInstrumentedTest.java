package com.tretton.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tretton.app.flows.mainscreen.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

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
}
