package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListNeighbourActivityTest {

    ArrayList<Neighbour> mGeneralList = new ArrayList<>();
    ArrayList<Neighbour> mFavoriteList = new ArrayList<>();
    Neighbour neighbourTested = new Neighbour(1, true ,"Caroline", "","","","","");


    @Test
    public void manageFavoriteList() {

        assertTrue(mGeneralList.isEmpty());
        assertTrue(mFavoriteList.isEmpty());

        // Add neighbour to the favorite list
        mGeneralList.add(neighbourTested);
        assertEquals(mGeneralList.size(),1);
        assertTrue(mFavoriteList.isEmpty());
        ListNeighbourActivity.manageFavoriteList(neighbourTested,mGeneralList,mFavoriteList);
        assertTrue(mFavoriteList.contains(neighbourTested));
        assertEquals(mFavoriteList.size(),1);

        // Remove neighbour from the favorite list
        neighbourTested.setFavorite(false);
        assertTrue(mFavoriteList.contains(neighbourTested));
        assertEquals(mFavoriteList.size(),1);
        ListNeighbourActivity.manageFavoriteList(neighbourTested,mGeneralList,mFavoriteList);
        assertTrue(mFavoriteList.isEmpty());
        assertTrue(mGeneralList.contains(neighbourTested));
        assertEquals(mGeneralList.size(),1);

        // Neighbour is'nt favorite and in not contained in favorite list > do nothing
        assertTrue(mFavoriteList.isEmpty());
        assertEquals(mFavoriteList.size(),0);
        assertTrue(mGeneralList.contains(neighbourTested));
        assertEquals(mGeneralList.size(),1);
        ListNeighbourActivity.manageFavoriteList(neighbourTested,mGeneralList,mFavoriteList);
        assertTrue(mFavoriteList.isEmpty());
        assertEquals(mFavoriteList.size(),0);
        assertTrue(mGeneralList.contains(neighbourTested));
        assertEquals(mGeneralList.size(),1);

        // Neighbour is favorite and is contained in favorite list > do nothing
        neighbourTested.setFavorite(true);
        ListNeighbourActivity.manageFavoriteList(neighbourTested,mGeneralList,mFavoriteList);
        assertTrue(mGeneralList.contains(neighbourTested));
        assertEquals(mGeneralList.size(),1);
        assertTrue(mFavoriteList.contains(neighbourTested));
        assertEquals(mFavoriteList.size(),1);
        ListNeighbourActivity.manageFavoriteList(neighbourTested,mGeneralList,mFavoriteList);
        assertTrue(mGeneralList.contains(neighbourTested));
        assertEquals(mGeneralList.size(),1);
        assertTrue(mFavoriteList.contains(neighbourTested));
        assertEquals(mFavoriteList.size(),1);
    }
}