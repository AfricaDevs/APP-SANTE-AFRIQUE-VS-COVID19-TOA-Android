package com.africadevs.toa.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.africadevs.toa.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ToaMainActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void toaMainActivityTest() {
        ViewInteraction cardView = onView(
                allOf(withId(R.id.card_prevention),
                        childAtPosition(
                                allOf(withId(R.id.prevention_container),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(R.id.coordinator_layout),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.item_when), withContentDescription("When ?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.item_how), withContentDescription("How ?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.backArrow),
                        childAtPosition(
                                allOf(withId(R.id.toolbarRelative),
                                        childAtPosition(
                                                withId(R.id.coordinatorLayout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(R.id.coordinator_layout),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.item_when), withContentDescription("When ?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.item_how), withContentDescription("How ?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.backArrow),
                        childAtPosition(
                                allOf(withId(R.id.toolbarRelative),
                                        childAtPosition(
                                                withId(R.id.coordinatorLayout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.collapsing_toolabar),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction cardView3 = onView(
                allOf(withId(R.id.card_diagnosis),
                        childAtPosition(
                                allOf(withId(R.id.diagnosis_container),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        cardView3.perform(click());

        ViewInteraction smoothCheckBox = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        smoothCheckBox.perform(click());

        ViewInteraction smoothCheckBox2 = onView(
                allOf(withId(R.id.checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        smoothCheckBox2.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.btn_next),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.coordinator_layout),
                                        1),
                                2),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.btn_next),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.coordinator_layout),
                                        1),
                                2),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction relativeLayout3 = onView(
                allOf(withId(R.id.btn_no_disease),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.coordinator_layout),
                                        1),
                                2),
                        isDisplayed()));
        relativeLayout3.perform(click());

        ViewInteraction relativeLayout4 = onView(
                allOf(withId(R.id.btn_no_disease),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.coordinator_layout),
                                        1),
                                2),
                        isDisplayed()));
        relativeLayout4.perform(click());

        ViewInteraction relativeLayout5 = onView(
                allOf(withId(R.id.btn_yes),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.coordinator_layout),
                                        0),
                                1),
                        isDisplayed()));
        relativeLayout5.perform(click());

        ViewInteraction view = onView(
                allOf(withId(R.id.touch_outside),
                        childAtPosition(
                                allOf(withId(R.id.coordinator),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                0),
                        isDisplayed()));
        view.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
