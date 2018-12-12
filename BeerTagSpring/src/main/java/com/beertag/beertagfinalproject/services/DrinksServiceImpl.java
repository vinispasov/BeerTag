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
    public void deleteDrinksByBeerId(int beerId) {
        deleteDrinksByBeerId(beerId);
    }

    @Override
    public Drink setDrankBeer(int beerId, int userId) {
        return drinksRepository.setDrankBeer(beerId,userId);
    }

    @Override
    public Drink rateBeer(int beerId, int userId, Drink updatedDrink) {
        return drinksRepository.rateBeer(beerId,userId,updatedDrink);
    }

    @Override
    public Drink getDrinkById(int drinkId) {
        return drinksRepository.getDrinkById(drinkId);
    }
}
