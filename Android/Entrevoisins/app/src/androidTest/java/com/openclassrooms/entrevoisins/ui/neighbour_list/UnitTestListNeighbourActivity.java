package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnitTestListNeighbourActivity {

    private ArrayList<Neighbour> mGeneralList = new ArrayList<>();
    private ArrayList<Neighbour> mFavoriteList = new ArrayList<>();
    private Neighbour mNeighbourTested = new Neighbour(0, false ,"", "","","","","");

    @Before
    public void ensureThatAllListsAreEmpty() {
        mGeneralList.clear();
        mFavoriteList.clear();
        mNeighbourTested.setFavorite(false);
    }

    /**
     * we ensure that getFavoriteNeighbours method works correctly
     */
    @Test
    public void whenGetFavoriteNeighboursIsCalled_allFavoriteNeighboursInTestedListMustBeAddedInFavoriteList(){
        ArrayList<Neighbour> mGeneralList = (ArrayList<Neighbour>) DI.getNeighbourApiService().getNeighbours();
        ListNeighbourActivity.getFavoriteNeighbours(mGeneralList,mGeneralList,mFavoriteList);
        assertEquals(2, mFavoriteList.size());
    }

    /**
     * Should verify if the neighbour passed in parameters is contained in the list passed in parameter too
     * The method must return a boolean
     * @param neighbourList
     * @param neighbour
     * @return
     */
    private Boolean EnsureThatListContainNeighbour(ArrayList<Neighbour> neighbourList, Neighbour neighbour){
        boolean result = false;

        if (neighbourList.contains(neighbour) && neighbourList.size() == 1){
            result = true;
        }
        return result;
    }

    /**
     * We ensure that method manageFavoriteList add the neighbour (with favorite parameter at true) in the favorite list
     */
    @Test
    public void whenManageFavoriteListIsCalled_shouldAddNeighbourToTheFavoriteList(){
        // Add neighbour to the general list; make neighbour favorite; and check favorite list is already empty
        mGeneralList.add(mNeighbourTested);
        mNeighbourTested.setFavorite(true);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(mFavoriteList.isEmpty());

        // Add neighbour to the favorite list with calling manageFavoriteList method
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);

        // Ensure that neighbour is added in favorite list
        assertTrue(EnsureThatListContainNeighbour(mFavoriteList,mNeighbourTested));
    }

    /**
     * We ensure that method manageFavoriteList remove the neighbour (with favorite parameter at false) from the favorite list
     */
    @Test
    public void whenManageFavoriteListIsCalled_shouldRemoveNeighbourFromFavoriteList() {
        // Add neighbour to the general list; make neighbour favorite; and manage favorite list
        // Neighbour should be contained in both list
        mGeneralList.add(mNeighbourTested);
        mNeighbourTested.setFavorite(true);
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(EnsureThatListContainNeighbour(mFavoriteList,mNeighbourTested));

        // Remove neighbour from the favorite list and call manage method
        mNeighbourTested.setFavorite(false);
        assertTrue(EnsureThatListContainNeighbour(mFavoriteList,mNeighbourTested));
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));

        // Ensure that favorite neighbour list is empty
        assertTrue(mFavoriteList.isEmpty());
    }

    /**
     * We ensure that method manageFavoriteList do nothing when neighbour is contained in the rights lists
     */
    @Test
    public void whenManageFavoriteListIsCalled_mustEnsureNothingIsDo() {
        // Neighbour is favorite and contained in favorite list
        mGeneralList.add(mNeighbourTested);
        mNeighbourTested.setFavorite(true);
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(EnsureThatListContainNeighbour(mFavoriteList,mNeighbourTested));

        // Ensure that manager don't change anything
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(EnsureThatListContainNeighbour(mFavoriteList,mNeighbourTested));

        // Neighbour isn't favorite and favorite list is empty
        mNeighbourTested.setFavorite(false);
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(mFavoriteList.isEmpty());

        // Ensure that manager don't change anything
        ListNeighbourActivity.manageFavoriteList(mNeighbourTested,mGeneralList,mFavoriteList);
        assertTrue(EnsureThatListContainNeighbour(mGeneralList,mNeighbourTested));
        assertTrue(mFavoriteList.isEmpty());
    }
}