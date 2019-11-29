package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Amine K. on 29/11/19.
 */
public class ShowNeighbourDetailsEvent {

    public Neighbour neighbour;

    public ShowNeighbourDetailsEvent(Neighbour neighbour) {

        this.neighbour = neighbour;
    }
}
