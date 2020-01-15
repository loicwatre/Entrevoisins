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

    /**
     * Get the list of favorites neighbours given their ids
     * @param favoriteIds
     */

    List<Neighbour> getFavoriteNeighbours(List<Integer> favoriteIds);

    /**
     * Get a Neighbour given its id
     * @param id
     */
    Neighbour getSpecificNeighbour(Integer id);
}
