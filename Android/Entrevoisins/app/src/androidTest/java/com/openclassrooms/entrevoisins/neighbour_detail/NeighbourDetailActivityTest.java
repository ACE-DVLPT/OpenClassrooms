package com.openclassrooms.entrevoisins.neighbour_detail;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourDetailActivity;
import com.openclassrooms.entrevoisins.utils.EspressoTestsMatchers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourFragment.KEY_NEIGHBOUR_DETAIL;

/**
 * Test class for Neighbour Detail Activity
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailActivityTest {

    @Rule
    public ActivityTestRule<NeighbourDetailActivity> mActivityRule =
            new ActivityTestRule(NeighbourDetailActivity.class,true,false);

    /**
     * Start NeighbourDetailActivity with fake neighbour
     */
    @Before
        public void setUp(){
        Neighbour mFakeNeighbour = new Neighbour(0, false ,"", "","","","","");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(KEY_NEIGHBOUR_DETAIL,mFakeNeighbour);
        mActivityRule.launchActivity(intent);
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

}
