package com.beertag.diconfig;

import com.beertag.http.base.HttpRequester;
import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerTagDTO;
import com.beertag.models.Drink;
import com.beertag.models.Tag;
import com.beertag.models.User;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.HttpBeerTagsRepository;
import com.beertag.repositories.HttpBeersRepository;
import com.beertag.repositories.HttpDrinksRepository;
import com.beertag.repositories.HttpTagsRepository;
import com.beertag.repositories.HttpUsersRepository;
import com.beertag.repositories.base.BeerTagsRepository;
import com.beertag.repositories.base.BeersRepository;
import com.beertag.repositories.base.DrinksRepository;
import com.beertag.repositories.base.TagsRepository;
import com.beertag.repositories.base.UsersRepository;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public BeersRepository beersRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Beer> jsonParser) {

        return new HttpBeersRepository(baseServerUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public UsersRepository usersRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<User> jsonParser) {

        return new HttpUsersRepository(baseServerUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public BeerTagsRepository beerTagsRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<BeerTag> jsonParser,
            JsonParser<BeerTagDTO> jsonParserDTO) {

        return new HttpBeerTagsRepository(baseServerUrl, httpRequester, jsonParser,jsonParserDTO);
    }
    @Provides
    @Singleton
    public DrinksRepository drinksRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Drink> jsonParser,
            JsonParser<Integer> jsonParserInteger) {

        return new HttpDrinksRepository(baseServerUrl, httpRequester, jsonParser,jsonParserInteger);
    }
    @Provides
    @Singleton
    public TagsRepository tagsRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Tag> jsonParser) {

        return new HttpTagsRepository(baseServerUrl, httpRequester, jsonParser);
    }


}
