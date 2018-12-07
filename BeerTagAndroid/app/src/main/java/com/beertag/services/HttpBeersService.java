package com.beertag.services;

import com.beertag.repositories.base.BeersRepository;
import com.beertag.services.base.BeersService;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;
import com.beertag.models.Beer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpBeersService implements BeersService {

    private final BeersRepository mBeersRepository;
    private final Validator<Beer> mBeerValidator;

    public HttpBeersService(BeersRepository beersRepository, Validator<Beer> beerValidator) {
        mBeersRepository = beersRepository;
        mBeerValidator = beerValidator;
    }


    @Override
    public Beer addBeer(Beer newBeer) throws Exception {
        boolean isBeerValid = mBeerValidator.isItemValid(newBeer);
        if (!isBeerValid) {
            throw new Exception(Constants.ADD_OF_BEER_FAIL_MESSAGE);
        }
       return mBeersRepository.addBeer(newBeer);
    }

    @Override
    public void deleteBeer(int id) throws Exception {
        mBeersRepository.deleteBeer(id);
    }

    @Override
    public Beer updateBeer(Beer beerToUpdate, int id) throws IOException{
       return mBeersRepository.updateBeer(beerToUpdate,id);
    }

    @Override
    public Beer getBeerById(int id) throws IOException {
        return mBeersRepository.getBeerById(id);
    }

    @Override
    public List<Beer> getAllBeers() throws IOException {
        return mBeersRepository.getAllBeers();
    }

    @Override
    public List<Beer> getAllBeersSortedByRating() throws IOException {
        return mBeersRepository.getAllBeersSortedByRating();
    }

    @Override
    public List<Beer> getAllBeersSortedByAbv() throws IOException {
        return mBeersRepository.getAllBeersSortedByAbv();
    }

    @Override
    public List<Beer> getAllBeersSortedByName() throws IOException {
        return mBeersRepository.getAllBeersSortedByName();
    }


}
