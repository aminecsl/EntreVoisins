package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

/*import testing classes so we can use comparing syntaxes such ass :
 * assertThat(x, is(3));
 * assertThat(x, is(not(4)));
 * assertThat(responseString, either(containsString("color")).or(containsString("colour")));
 * assertThat(myList, hasItem("3"));
 * https://github.com/junit-team/junit4/wiki/Matchers-and-assertthat
 */
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {

        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void changeFavoriteStatusWithSuccess() {

        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighb = neighbours.get(0);
        boolean neighbBeginFavStatus = neighb.isFavorite();
        service.changeFavoriteStatus(neighb);
        assertThat(neighbBeginFavStatus, is(not(service.getNeighbours().get(0).isFavorite())));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {

        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighb1 = neighbours.get(0);
        service.changeFavoriteStatus(neighb1);
        Neighbour neighb2 = neighbours.get(1);
        service.changeFavoriteStatus(neighb2);
        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighboursFromList();
        assertThat(favoriteNeighbours.size(), is(2));
        assertTrue(favoriteNeighbours.get(0).isFavorite());
        assertTrue(favoriteNeighbours.get(1).isFavorite());
    }
}
