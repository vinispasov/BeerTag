package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.Drink;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface DrinksRepository {
    Drink addDrink(Drink newDrink);

    List<Drink> getTopThreeRatedDrinksByUser(int userId);

    List<Drink> getAllDrinksByBeerId(int beerId);

    void deleteDrinksByBeerId(int beerId);

    Drink setDrankBeer(int drinkId,Drink updatedDrink);

    Drink rateBeer(int beerId,int userId,Drink updatedDrink);

    Drink getDrinkById(int drinkId);

    List<Integer> getAllBeerIds();

    Drink checkIfBeerIsRated(int beerId,int userId);

}
