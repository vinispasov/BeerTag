package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.repositories.base.BeersRepository;
import com.beertag.beertagfinalproject.services.base.BeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeersServiceImpl implements BeersService {

    private final BeersRepository beersRepository;

    @Autowired
    public BeersServiceImpl(BeersRepository beersRepository){
        this.beersRepository=beersRepository;
    }

    @Override
    public Beer getBeerById(int id) {
        return beersRepository.getBeerById(id);
    }

    @Override
    public List<Beer> getAllBeers() {
        return beersRepository.getAllBeers();
    }

    @Override
    public List<Beer> getAllBeersSortedByRating() {
        return beersRepository.getAllBeersSortedByRating();
    }

    @Override
    public List<Beer> getAllBeersSortedByAbv() {
        return beersRepository.getAllBeersSortedByAbv();
    }

    @Override
    public List<Beer> getAllBeersSortedByName() {
        return beersRepository.getAllBeersSortedByName();
    }

    @Override
    public List<Beer> getBeersByTag(String tag) {
        return beersRepository.getBeersByTag(tag);
    }

    @Override
    public List<Beer> getBeersByStyle(String style) {
        return beersRepository.getBeersByStyle(style);
    }

    @Override
    public List<Beer> getBeersByCountry(String country) {
        return beersRepository.getBeersByCountry(country);
    }

    @Override
    public Beer addNewBeer(Beer newBeer) {
        return beersRepository.addNewBeer(newBeer);
    }

    @Override
    public Beer editBeer(Beer beerToEdit, int id) {
        return beersRepository.editBeer(beerToEdit,id);
    }

    @Override
    public void deleteBeer(int id) {
        beersRepository.deleteBeer(id);
    }
}
