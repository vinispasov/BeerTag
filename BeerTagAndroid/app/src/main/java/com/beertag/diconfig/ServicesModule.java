package com.beertag.diconfig;

import com.beertag.models.User;
import com.beertag.repositories.base.BeersListRepository;
import com.beertag.repositories.base.UsersListRepository;
import com.beertag.services.HttpBeersListService;
import com.beertag.services.HttpUsersListService;
import com.beertag.services.base.BeersListService;
import com.beertag.services.base.UsersListService;
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
    @Provides
    public UsersListService usersListService(UsersListRepository repository, Validator<User> userValidator) {
        return new HttpUsersListService(repository, userValidator);
    }
}
