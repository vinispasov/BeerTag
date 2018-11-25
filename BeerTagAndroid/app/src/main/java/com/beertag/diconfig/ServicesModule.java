package com.beertag.diconfig;

import com.beertag.repositories.base.BeersListRepository;
import com.beertag.services.HttpBeersListService;
import com.beertag.services.base.BeersListService;
import com.beertag.utils.validators.base.Validator;
import com.beertag.models.Beer;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public BeersListService beersListService(BeersListRepository repository, Validator<Beer> beerValidator) {
        return new HttpBeersListService(repository, beerValidator);
    }
}
