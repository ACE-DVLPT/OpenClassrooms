package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class UnfavoriteNeighbourEvent {

    /**
     * Neighbour to show detail
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public UnfavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

