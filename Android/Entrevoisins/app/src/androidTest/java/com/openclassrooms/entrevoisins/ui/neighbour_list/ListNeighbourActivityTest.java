package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListNeighbourActivityTest {

    private NeighbourApiService service;
    ArrayList<Neighbour> mGeneralList;
    ArrayList<Neighbour> mFavoriteList;

    @Before
    public void setup(){
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void manageFavoriteList() {

        mGeneralList = (ArrayList<Neighbour>) service.getNeighbours();
        Neighbour neighbourTested =  mGeneralList.get(0);

        assertFalse(mGeneralList.get(0).getFavorite());
        assertFalse(neighbourTested.getFavorite());

        neighbourTested.setFavorite(true);

        assertEquals(mGeneralList.get(0).getFavorite(),false);
        assertEquals(neighbourTested.getFavorite(),true);

//        assertTrue(mFavoriteList.isEmpty());



    }
}