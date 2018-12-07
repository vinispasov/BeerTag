package com.beertag.services.base;

import com.beertag.models.Beer;

import java.io.IOException;
import java.util.List;

public interface BeersService {
    Beer addBeer(Beer newBeer) throws Exception;

    void deleteBeer(int id) throws Exception;

    Beer updateBeer(Beer beerToUpdate, int id) throws IOException;

    Beer getBeerById(int id) throws IOException;

    List<Beer> getAllBeers() throws IOException;

    List<Beer> getAllBeersSortedByRating() throws IOException;

    List<Beer> getAllBeersSortedByAbv() throws IOException;

    List<Beer> getAllBeersSortedByName() throws IOException;

    List<Beer> getFilteredBeers(String searchPattern)throws IOException;

}
