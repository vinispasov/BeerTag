package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.Drink;
import com.beertag.beertagfinalproject.repositories.base.DrinksRepository;
import com.beertag.beertagfinalproject.services.base.DrinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinksServiceImpl implements DrinksService {

    private final DrinksRepository drinksRepository;

    @Autowired
    public DrinksServiceImpl(DrinksRepository drinksRepository){
        this.drinksRepository=drinksRepository;
    }

    @Override
    public Drink addDrink(Drink newDrink) {
        return drinksRepository.addDrink(newDrink);
    }

    @Override
    public List<Drink> getTopThreeRatedDrinksByUser(int userId) {
        return drinksRepository.getTopThreeRatedDrinksByUser(userId);
    }

    @Override
    public List<Drink> getAllDrinksByBeerId(int beerId) {
        return drinksRepository.getAllDrinksByBeerId(beerId);
    }

    @Override
    public void deleteDrinkById(int drinkId) {
        drinksRepository.deleteDrinkById(drinkId);
    }

    @Override
    public Drink setDrankBeer(int drinkId,Drink updatedDrink) {
        return drinksRepository.setDrankBeer(drinkId,updatedDrink);
    }

    @Override
    public Drink rateBeer(int beerId, int userId, Drink updatedDrink) {
        return drinksRepository.rateBeer(beerId,userId,updatedDrink);
    }

    @Override
    public Drink getDrinkById(int drinkId) {
        return drinksRepository.getDrinkById(drinkId);
    }

    @Override
    public List<Integer> getAllBeerIds() {
        return drinksRepository.getAllBeerIds();
    }

    @Override
    public Drink checkIfBeerIsRated(int beerId, int userId) {

        return drinksRepository.checkIfBeerIsRated(beerId,userId);
    }
}
