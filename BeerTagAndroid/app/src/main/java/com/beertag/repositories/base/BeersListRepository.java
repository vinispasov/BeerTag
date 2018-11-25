package com.beertag.repositories.base;

import com.beertag.models.Beer;

import java.io.IOException;
import java.util.List;

public interface BeersListRepository {
    Beer addBeer(Beer beer) throws IOException;

    void deleteBeer(int id) throws IOException;

    Beer updateBeer(Beer beerToUpdate, int id) throws IOException;

    Beer getBeerById(int id) throws IOException;

    List<Beer> getAllBeers() throws IOException;
}
