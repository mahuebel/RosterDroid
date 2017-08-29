package com.huebelancer.rosterdroid;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.huebelancer.rosterdroid.Activities.RosterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RosterActivityEspressoTest {

    @Rule
    public ActivityTestRule<RosterActivity> mActivityRule =
            new ActivityTestRule(RosterActivity.class);

    @Test
    public void testDoListItemsLoad() {
        onView(allOf(withId(R.id.recyclerView), hasMinimumChildCount(3)))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testDoDetailsLoadOnClick() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.detail_view))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testDoesRosterFragmentLoadOnBack() {
        RosterActivity activity = mActivityRule.getActivity();

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        activity.getSupportFragmentManager().popBackStack();

        onView(allOf(withId(R.id.recyclerView), hasMinimumChildCount(3)))
                .check(matches(isDisplayed()));
    }
}
