package com.beertag.services;

import com.beertag.models.Drink;
import com.beertag.repositories.base.DrinksRepository;
import com.beertag.services.base.DrinksService;

import java.io.IOException;
import java.util.List;

public class HttpDrinksService implements DrinksService{
    private final DrinksRepository mDrinksRepository;


    public HttpDrinksService(DrinksRepository drinksRepository) {
        mDrinksRepository=drinksRepository;
    }

    @Override
    public Drink addDrink(Drink newDrink) throws IOException {
        return mDrinksRepository.addDrink(newDrink);
    }

    @Override
    public List<Drink> getTopThreeRatedDrinksByUser(int userId) throws IOException {
        return mDrinksRepository.getTopThreeRatedDrinksByUser(userId);
    }

    @Override
    public List<Drink> getAllDrinksByBeerId(int beerId) throws IOException {
        return mDrinksRepository.getAllDrinksByBeerId(beerId);
    }

    @Override
    public void deleteDrinksByBeerId(int beerId) {

    }

    @Override
    public Drink setDrankBeer(int drinkId,Drink updatedDrink) throws IOException {
        return mDrinksRepository.setDrankBeer(drinkId,updatedDrink);
    }

    @Override
    public Drink rateBeer(int beerId, int userId, Drink updatedDrink) throws IOException {
        return mDrinksRepository.rateBeer(beerId,userId,updatedDrink);
    }

    @Override
    public Drink getDrinkById(int drinkId) throws IOException {
        return mDrinksRepository.getDrinkById(drinkId);
    }

    @Override
    public List<Integer> getAllBeerIds() throws IOException {
        return mDrinksRepository.getAllBeerIds();
    }

    @Override
    public Drink checkIfBeerIsRated(int beerId, int userId) throws IOException {
        return mDrinksRepository.checkIfBeerIsRated(beerId,userId);
    }
}
