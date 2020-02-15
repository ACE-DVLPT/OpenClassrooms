
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.FavoriteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static android.support.test.espresso.action.ViewActions.swipeLeft;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

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
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * We ensure that the recyclerView with the favorite list is empty
     */
    @Test
    public void myFavoriteList_shouldBeEmpty() {
        // Swipe left to show favorite list
        onView((withId(R.id.container))).perform(swipeLeft());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure that recyclerView is empty
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(0));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
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
        onView((withId(R.id.container))).perform(swipeLeft());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new FavoriteViewAction()));

        // Ensure that recyclerView is empty
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(0));
    }
}