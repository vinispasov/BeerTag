package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.BeersListRepository;
import com.beertag.models.Beer;

import java.io.IOException;
import java.util.List;

public class HttpBeersListRepository implements BeersListRepository{

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Beer> mJsonParser;


    public HttpBeersListRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Beer> jsonParser) {
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

        String itemsJson = mHttpRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(itemsJson);
    }
}
