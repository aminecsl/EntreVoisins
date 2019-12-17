package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for Neighbour Name TextView and Favorites List
 */

@RunWith(AndroidJUnit4.class)
public class NeighbourDetailsActivityTest {

    private ListNeighbourActivity mActivity;

    private NeighbourApiService mApiService;

    private List<Neighbour> mNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNewInstanceApiService();
        mNeighbours = mApiService.getNeighbours();
    }

    /*Goal: we grab the name of a neighbour from the apiservice list at a certain position. When we click on the item of the RecyclerView
     * at the same position, we check that the name TextView of the displayed NeighbourDetails activity contains the same name string.*/
    @Test
    public void detailsActivityHasRightNameInTextView() {
        String name = mNeighbours.get(3).getName();
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(ViewMatchers.withId(R.id.neighbour_details_firstname_txt)).check(matches(withText(name)));
    }

    /*We grab the name of the neighbour at position 2 in the list. We click on him and add him to the favorites. We go back to the
     * ListNeighbourActivity, switch to the page of favorites tab, check that we have only 1 item and that the item has the same name
     * as the neighbour's name we added to favorite.*/
    @Test
    public void favoritesTabShouldContainFavoriteNeighboursOnly() {
        String name = mNeighbours.get(2).getName();
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(ViewMatchers.withId(R.id.neighbour_details_favorite_btn))
                .perform(click());
        pressBack();
        onView(withContentDescription("Favorites"))
                .perform(click());
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(1));
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.neighbour_details_firstname_txt)).check(matches(withText(name)));
    }

}
