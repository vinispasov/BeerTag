package com.beertag.repositories.base;

import com.beertag.models.Drink;

import java.io.IOException;
import java.util.List;

public interface DrinksRepository {
    Drink addDrink(Drink newDrink) throws IOException;

    List<Drink> getTopThreeRatedDrinksByUser(int userId) throws IOException;

    List<Drink> getAllDrinksByBeerId(int beerId) throws IOException;

    void deleteDrinkByDrinkId(int drinkId) throws IOException;

    Drink setDrankBeer(int drinkId,Drink drink) throws IOException;

    Drink rateBeer(int beerId,int userId,Drink updatedDrink) throws IOException;

    Drink getDrinkById(int drinkId) throws IOException;

    List<Integer> getAllBeerIds() throws IOException;

    Drink checkIfBeerIsRated(int beerId,int userId) throws IOException;
}
