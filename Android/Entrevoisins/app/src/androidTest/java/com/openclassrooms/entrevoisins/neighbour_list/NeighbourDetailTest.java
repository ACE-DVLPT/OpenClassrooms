package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourDetailActivity;
import com.openclassrooms.entrevoisins.utils.EspressoTestsMatchers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Test class for Neighbour Detail
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    @Rule
    public IntentsTestRule<ListNeighbourActivity> mListNeighbourActivityTestRule =
            new IntentsTestRule<>(ListNeighbourActivity.class);

    @Before
    public void clickOnNeighbour(){
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click() ));
    }

    /**
     * When neighbour is clicked on main activity, neighbour detail activity must be displayed
     */
    @Test
    public void onNeighbourClicked_shouldShowNeighbourDetails(){
        intended(hasComponent(NeighbourDetailActivity.class.getName()));
    }

    /**
     * When favorite button is clicked, the button appearance must be changed
     */
    @Test
    public void onFavoriteButtonClicked_shouldSwitchAppearanceButton(){
        // Ensure that icon must be hollow when activity is open
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .check(matches(EspressoTestsMatchers.withDrawable(R.drawable.ic_star_border_white_24dp)));

        // Ensure that icon must be full when favorite button is clicked
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .perform(click());
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .check(matches(EspressoTestsMatchers.withDrawable(R.drawable.ic_star_white_24dp)));

        // Ensure that icon must be hollow again when favorite button is clicked
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .perform(click());
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .check(matches(EspressoTestsMatchers.withDrawable(R.drawable.ic_star_border_white_24dp)));
    }

    @Test
    public void onBackButtonClicked_shouldBackToTheMainActivity(){

    }

}
