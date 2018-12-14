package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.parsers.base.JsonParser;
import com.beertag.models.Beer;
import com.beertag.repositories.base.BeersRepository;
import com.beertag.utils.Constants;

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
    public void deleteBeer(int beerId) throws IOException {
        String deleteServerUrl = mServerUrl + Constants.BEERS_ROOT_MAPPING + beerId;
        mHttpRequester.delete(deleteServerUrl, beerId);

    }

    @Override
    public Beer updateBeer(Beer beerToUpdate, int beerId) throws IOException {
        String updateServerUrl = mServerUrl + Constants.BEERS_ROOT_MAPPING + beerId;
        String requestBody = mJsonParser.toJson(beerToUpdate);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody, beerId);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Beer getBeerById(int beerId) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + Constants.BEERS_ROOT_MAPPING +"/"+ beerId);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<Beer> getAllBeers() throws IOException {

        String itemsJson = mHttpRequester.get(mServerUrl+Constants.BEERS_ROOT_MAPPING);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByRating() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+Constants.BEERS_ROOT_MAPPING_SORT_BY_RATING);
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByAbv() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+Constants.BEERS_ROOT_MAPPING_SORT_BY_ABV);
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getAllBeersSortedByName() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+Constants.BEERS_ROOT_MAPPING_SORT_BY_NAME);
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getBeersByStyle(String style) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("beers/")
                .append("style")
                .append("/")
                .append(style)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Beer> getBeersByCountry(String country) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("beers/")
                .append("country")
                .append("/")
                .append(country)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
