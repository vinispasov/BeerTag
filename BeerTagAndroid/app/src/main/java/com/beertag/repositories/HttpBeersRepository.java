package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.parsers.base.JsonParser;
import com.beertag.models.Beer;
import com.beertag.repositories.base.BeersRepository;

import java.io.IOException;
import java.util.List;

public class HttpBeersRepository implements BeersRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Beer> mJsonParser;


    public HttpBeersRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Beer> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public Beer addBeer(Beer newBeer) throws IOException {
        String requestBody = mJsonParser.toJson(newBeer);
        String responseBody=mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public void deleteBeer(int id) throws IOException {
        String deleteServerUrl = mServerUrl + "/" + id;
        mHttpRequester.delete(deleteServerUrl, id);

    }

    @Override
    public Beer updateBeer(Beer beerToUpdate, int id) throws IOException {
        String updateServerUrl = mServerUrl + "/" + id;
        String requestBody = mJsonParser.toJson(beerToUpdate);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody, id);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Beer getBeerById(int id) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + "/" + id);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<Beer> getAllBeers() throws IOException {

        String itemsJson = mHttpRequester.get(mServerUrl+"/beers");
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByRating() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+"/beers/sort/rating");
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByAbv() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+"/beers/sort/abv");
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByName() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+"/beers/sort/name");
        return mJsonParser.fromJsonArray(itemsJson);
    }
}
