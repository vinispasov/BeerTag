package com.beertag.diconfig;

import com.beertag.utils.validators.BeerValidator;
import com.beertag.utils.validators.base.Validator;
import com.beertag.models.Beer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ValidatorsModule {
    @Provides
    @Singleton
    public Validator<Beer> beerValidator() {
        return new BeerValidator();
    }
}
