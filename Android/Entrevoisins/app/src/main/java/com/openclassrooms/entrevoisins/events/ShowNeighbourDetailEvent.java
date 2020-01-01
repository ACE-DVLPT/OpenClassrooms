package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ShowNeighbourDetailEvent {

    /**
     * Neighbour to show detail
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public ShowNeighbourDetailEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
