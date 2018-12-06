package com.beertag.diconfig;

import com.beertag.models.Beer;
import com.beertag.models.User;
import com.beertag.repositories.base.BeersRepository;
import com.beertag.repositories.base.UsersRepository;
import com.beertag.services.HttpBeersService;
import com.beertag.services.HttpUsersService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.UsersService;
import com.beertag.utils.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public BeersService beersListService(BeersRepository repository, Validator<Beer> beerValidator) {
        return new HttpBeersService(repository, beerValidator);
    }
    @Provides
    public UsersService usersListService(UsersRepository repository, Validator<User> userValidator) {
        return new HttpUsersService(repository, userValidator);
    }
}
