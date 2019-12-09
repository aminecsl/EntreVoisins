package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }


    /* Return a new list with only the neighbours who have a "true" value in their "isFavorite" attribute*/
    @Override
    public List<Neighbour> getFavoriteNeighboursFromList(){

        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (Neighbour neighb : neighbours){
            if (neighb.isFavorite()){
                favoriteNeighbours.add(neighb);
            }
        }

        return favoriteNeighbours;
    }

    @Override
    public void changeFavoriteStatus(Neighbour neighbour){

        int neighbourIndex = neighbours.indexOf(neighbour);
        neighbours.get(neighbourIndex).setFavorite(!neighbour.isFavorite());

    }

}
