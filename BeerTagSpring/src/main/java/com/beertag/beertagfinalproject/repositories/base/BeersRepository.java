package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.Beer;

import java.util.List;

public interface BeersRepository {
    Beer getBeerById(int id);
    List<Beer> getAllBeers();
    List<Beer> getAllBeersSortedByRating();
    List<Beer> getAllBeersSortedByAbv();
    List<Beer> getAllBeersSortedByName();
    List<Beer> getBeersByTag(String tag);
    List<Beer> getBeersByStyle(String style);
    List<Beer> getBeersByCountry(String country);
    Beer addNewBeer(Beer newBeer);
    Beer editBeer(Beer beerToEdit,int id);
    void deleteBeer(int id);
}
