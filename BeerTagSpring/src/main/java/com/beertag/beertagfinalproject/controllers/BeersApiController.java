package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.services.base.BeersService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.BEERS_ROOT_MAPPING)
public class BeersApiController {

    private final BeersService beersService;

    @Autowired
    public BeersApiController(BeersService beersService){
        this.beersService=beersService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable int id) {
        return beersService.getBeerById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> getAllBeers() {
        return beersService.getAllBeers();
    }
    @RequestMapping(value = "/sort/rating",method = RequestMethod.GET)
    public List<Beer> getAllBeersSortedByRating(@RequestBody Beer beer) {
        return beersService.getAllBeersSortedByRating(beer);
    }
    @RequestMapping(value = "/sort/abv", method = RequestMethod.GET)
    public List<Beer> getAllBeersSortedByAbv() {
        return beersService.getAllBeersSortedByAbv();
    }
    @RequestMapping(value = "/sort/name", method = RequestMethod.GET)
    public List<Beer> getAllBeersSortedByName() {
        return beersService.getAllBeersSortedByName();
    }
    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public List<Beer> getBeersByTag(@PathVariable String tag) {
        return beersService.getBeersByTag(tag);
    }
    @RequestMapping(value = "/style/{style}", method = RequestMethod.GET)
    public List<Beer> getBeersByStyle(@PathVariable String style) {
        return beersService.getBeersByStyle(style);
    }
    @RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
    public List<Beer> getBeersByCountry(@PathVariable String country) {
        return beersService.getBeersByCountry(country);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Beer addNewBeer(@RequestBody @Valid Beer newBeer) {
        return beersService.addNewBeer(newBeer);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Beer editBeer(@RequestBody @Valid Beer beerToEdit, @PathVariable int id) {
        return beersService.editBeer(beerToEdit,id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBeer(@PathVariable(value = "id") @Valid int id) {
        beersService.deleteBeer(id);
    }

}
