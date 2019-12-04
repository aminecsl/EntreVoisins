package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {


    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**Return a list of my favorite neighbours*/
    List<Neighbour> getFavoriteNeighboursFromList();


    /**Adds or Removes a neighbour from the list of favorites and updates the star color*/
    void changeFavoriteStatus(Neighbour neighbour);


}
