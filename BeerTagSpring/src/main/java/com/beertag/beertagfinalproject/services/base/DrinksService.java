package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.Drink;

import java.util.List;

public interface DrinksService {
    Drink addDrink(Drink newDrink);

    List<Drink> getTopThreeRatedDrinksByUser(int userId);

    List<Drink> getAllDrinksByBeerId(int beerId);

    void deleteDrinksByBeerId(int beerId);

    Drink setDrankBeer(int beerId,int userId);

    Drink rateBeer(int beerId,int userId,Drink updatedDrink);

    Drink getDrinkById(int drinkId);
}
