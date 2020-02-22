
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourDetailActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.FavoriteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static android.support.test.espresso.action.ViewActions.swipeLeft;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class UITestListNeighbourActivity {

    // This is fixed
    private static int ITEMS_COUNT;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerView is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myFavoriteNeighboursList_shouldNotBeEmpty(){
        // Swipe left to show favorite list
        onView((withId(R.id.container))).perform(swipeLeft());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure that favorite list contain 2 items
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(2)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        ITEMS_COUNT = mActivityRule.getActivity().mGeneralList.size();

                // Given : We remove the element at position 2
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When favorite button is clicked, the item is no more shown
     */
    @Test
    public void myFavoriteList_whenFavoriteButtonIsClicked_shouldRemoveItemFromFavoriteList(){
        // Add fake neighbour to favorite list
        Neighbour mFakeNeighbour = new Neighbour(0, true ,"", "","","","","");
        mActivityRule.getActivity().mGeneralList.add(mFakeNeighbour);
        mActivityRule.getActivity().manageFavoriteList(
                mFakeNeighbour,
                mActivityRule.getActivity().mGeneralList,
                mActivityRule.getActivity().mFavoriteList
        );

        // Swipe left to show favorite list
        onView((withId(R.id.container))).perform(swipeLeft());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ITEMS_COUNT = mActivityRule.getActivity().mFavoriteList.size();

        // Perform a click on the favorite icon
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new FavoriteViewAction()));

        // Ensure that the recyclerView is empty
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When neighbour is clicked on main activity, neighbour detail activity must be displayed
     */
    @Test
    public void onNeighbourClick_shouldStartNeighbourDetailActivity(){
        Intents.init();

        // Perform click on the second item
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click() ));

        // Ensure that the correct activity has started
        intended(hasComponent(NeighbourDetailActivity.class.getName()));

        Intents.release();
    }

    /**
     * When NeighbourDetailActivity is started, we want to verify if the showed name is the expected neighbour name
     */
    @Test
    public void onNeighbourDetailActivityStarted_shouldDisplayExpectedNameOnTextView(){
        String neighbourNameChecked = "Jack";

        // Perform click on the second item "Jack"
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click() ));

        onView(withId(R.id.activityNeighbourDetailTxtTitle))
                .check(matches(withText(neighbourNameChecked)));
    }
}