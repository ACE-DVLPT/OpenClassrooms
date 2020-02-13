package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
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

    @Test
    public void onNeighbourClicked_shouldShowNeighbourDetails(){
        intended(hasComponent(NeighbourDetailActivity.class.getName()));
    }

    @Test
    public void onFavoriteButtonClicked_shouldSwitchAppearanceButton(){
        onView(withId(R.id.activityNeighbourDetailBtnFavorite))
                .check(matches(isClickable()));

    }

}
