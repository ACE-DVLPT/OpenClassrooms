package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourToFavoriteEvent {

    public Neighbour neighbour;

    public AddNeighbourToFavoriteEvent(Neighbour neighbour){
        this.neighbour = neighbour;
    }
}
