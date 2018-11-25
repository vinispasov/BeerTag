package com.beertag.services;

import com.beertag.repositories.base.BeersListRepository;
import com.beertag.services.base.BeersListService;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;
import com.beertag.models.Beer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpBeersListService implements BeersListService {

    private final BeersListRepository mBeersListRepository;
    private final Validator<Beer> mBeerValidator;

    public HttpBeersListService(BeersListRepository beersListRepository, Validator<Beer> beerValidator) {
        mBeersListRepository = beersListRepository;
        mBeerValidator = beerValidator;
    }


    @Override
    public Beer addBeer(Beer newBeer) throws Exception {
        boolean isBeerValid = mBeerValidator.isItemValid(newBeer);
        if (!isBeerValid) {
            throw new Exception(Constants.ADD_OF_BEER_FAIL_MESSAGE);
        }
       return mBeersListRepository.addBeer(newBeer);
    }

    @Override
    public void deleteBeer(int id) throws Exception {
        mBeersListRepository.deleteBeer(id);
    }

    @Override
    public Beer updateBeer(Beer beerToUpdate, int id) throws IOException{
       return mBeersListRepository.updateBeer(beerToUpdate,id);
    }

    @Override
    public Beer getBeerById(int id) throws IOException {
        return mBeersListRepository.getBeerById(id);
    }

    @Override
    public List<Beer> getAllBeers() throws IOException {
        return mBeersListRepository.getAllBeers();
    }

    @Override
    public List<Beer> getFilteredBeers(String searchPattern) throws IOException {

        String searchPatternLowerCase = searchPattern.toLowerCase();

        List<Beer> allBeers = getAllBeers();
        List<Beer> filteredBeers = new ArrayList<>();

        allBeers
                .stream()
                .filter(beer ->beer.getBeerStyle().toLowerCase().contains(searchPatternLowerCase))
                .forEach(filteredBeers::add);
        allBeers
                .stream()
                .filter(beer -> beer.getOriginCountry().toLowerCase().contains(searchPatternLowerCase))
                .forEach(filteredBeers::add);

        return filteredBeers;

    }

}
