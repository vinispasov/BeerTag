package com.beertag.diconfig;

import com.beertag.http.base.HttpRequester;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.HttpBeersListRepository;
import com.beertag.repositories.base.BeersListRepository;
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
    public BeersListRepository beersListRepository(
            @Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl,
            HttpRequester httpRequester,
            JsonParser<Beer> jsonParser) {

        return new HttpBeersListRepository(baseServerUrl, httpRequester, jsonParser);
    }
}
