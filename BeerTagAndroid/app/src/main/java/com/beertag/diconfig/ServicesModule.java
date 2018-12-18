package com.beertag.diconfig;

import com.beertag.models.Beer;
import com.beertag.models.Drink;
import com.beertag.models.User;
import com.beertag.repositories.base.BeerTagsRepository;
import com.beertag.repositories.base.BeersRepository;
import com.beertag.repositories.base.DrinksRepository;
import com.beertag.repositories.base.TagsRepository;
import com.beertag.repositories.base.UsersRepository;
import com.beertag.services.HttpBeerTagsService;
import com.beertag.services.HttpBeersService;
import com.beertag.services.HttpDrinksService;
import com.beertag.services.HttpTagsService;
import com.beertag.services.HttpUsersService;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.services.base.TagsService;
import com.beertag.services.base.UsersService;
import com.beertag.utils.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public BeersService beersService(BeersRepository repository, Validator<Beer> beerValidator) {
        return new HttpBeersService(repository, beerValidator);
    }
    @Provides
    public UsersService usersService(UsersRepository repository, Validator<User> userValidator) {
        return new HttpUsersService(repository, userValidator);
    }
    @Provides
    public BeerTagsService beerTagsService(BeerTagsRepository repository) {
        return new HttpBeerTagsService(repository);
    }
    @Provides
    public DrinksService drinksService(DrinksRepository repository) {
        return new HttpDrinksService(repository);
    }
    @Provides
    public TagsService tagsService(TagsRepository repository) {
        return new HttpTagsService(repository);
    }

}
