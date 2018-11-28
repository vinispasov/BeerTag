package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;

import java.util.List;

public interface BeersService {
    Beer getBeerById(int id);
    List<BeerDTO> getAllBeers();
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
