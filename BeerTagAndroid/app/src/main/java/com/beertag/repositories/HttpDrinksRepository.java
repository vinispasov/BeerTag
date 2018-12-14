package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.models.Drink;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.DrinksRepository;
import com.beertag.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpDrinksRepository implements DrinksRepository{
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Drink> mJsonParser;
    private final JsonParser<Integer> mJsonParserInteger;



    public HttpDrinksRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Drink> jsonParser,JsonParser<Integer> jsonParserInteger) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
        mJsonParserInteger=jsonParserInteger;
    }

    @Override
    public Drink addDrink(Drink newDrink) throws IOException {
        String requestBody = mJsonParser.toJson(newDrink);
        String responseBody = mHttpRequester.post(mServerUrl +"/drinks/", requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<Drink> getTopThreeRatedDrinksByUser(int userId) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("/drinks/")
                .append("top")
                .append("/")
                .append(userId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<Drink> getAllDrinksByBeerId(int beerId) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("/drinks/")
                .append("beer")
                .append("/")
                .append(beerId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public void deleteDrinksByBeerId(int beerId) {

    }

    @Override
    public Drink setDrankBeer(int beerId, int userId) throws IOException {
        String updateServerUrl = mServerUrl
                +Constants.DRINKS_ROOT_MAPPING
                +"/drank/"
                +beerId
                +"/"
                +userId;

        String responseBody = mHttpRequester.update(updateServerUrl, beerId,userId);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Drink rateBeer(int beerId, int userId, Drink updatedDrink) throws IOException {
        String updateServerUrl = mServerUrl
                +Constants.DRINKS_ROOT_MAPPING
                +"/rate/"
                +beerId
                +"/"
                +userId;
        String requestBody = mJsonParser.toJson(updatedDrink);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody, beerId, userId);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Drink getDrinkById(int drinkId) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + Constants.DRINKS_ROOT_MAPPING + drinkId);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<Integer> getAllBeerIds() throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("/drinks/")
                .append("beerids")
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParserInteger.fromJsonArray(itemsJson);
    }

    @Override
    public Drink checkIfBeerIsRated(int beerId, int userId) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + Constants.DRINKS_ROOT_MAPPING + "/check/"+beerId+"/"+userId);
        return mJsonParser.fromJson(itemJson);
    }
}
