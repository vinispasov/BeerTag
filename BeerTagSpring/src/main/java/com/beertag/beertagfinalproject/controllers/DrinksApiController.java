package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.Drink;
import com.beertag.beertagfinalproject.services.base.BeersTagsService;
import com.beertag.beertagfinalproject.services.base.DrinksService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.DRINKS_ROOT_MAPPING)
public class DrinksApiController {
    private final DrinksService drinksService;

    @Autowired
    public DrinksApiController(DrinksService drinksService){
        this.drinksService=drinksService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Drink addDrink(@RequestBody @Valid Drink newDrink) {
        return drinksService.addDrink(newDrink);
    }

    @RequestMapping(value = "/top/{userId}", method = RequestMethod.GET)
    public List<Drink> getTopThreeRatedDrinksByUser(@PathVariable int userId) {
        return drinksService.getTopThreeRatedDrinksByUser(userId);
    }

    @RequestMapping(value = "/beer/{beerId}", method = RequestMethod.GET)
    public List<Drink> getAllDrinksByBeerId(@PathVariable int beerId) {
        return drinksService.getAllDrinksByBeerId(beerId);
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.DELETE)
    public void deleteDrinksByBeerId(@PathVariable(value = "beerId") @Valid int beerId) {
        deleteDrinksByBeerId(beerId);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.PUT)
    public Drink setDrankBeer(@PathVariable int drinkId,@RequestBody @Valid Drink updatedDrink) {
        return drinksService.setDrankBeer(drinkId,updatedDrink);
    }

    @RequestMapping(value = "/rate/{beerId}/{userId}", method = RequestMethod.PUT)
    public Drink rateBeer(@PathVariable int beerId,@PathVariable int userId, @RequestBody @Valid Drink updatedDrink) {
        return drinksService.rateBeer(beerId,userId,updatedDrink);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public Drink getDrinkById(@PathVariable int drinkId) {
        return drinksService.getDrinkById(drinkId);
    }

    @RequestMapping(value = "/beerids",method = RequestMethod.GET)
    public List<Integer> getAllBeerIds() {
        return drinksService.getAllBeerIds();
    }

    @RequestMapping(value = "/check/{beerId}/{userId}", method=RequestMethod.GET)
    public Drink checkIfBeerIsRated(@PathVariable int beerId,@PathVariable int userId){
        return drinksService.checkIfBeerIsRated(beerId,userId);
    }
}
