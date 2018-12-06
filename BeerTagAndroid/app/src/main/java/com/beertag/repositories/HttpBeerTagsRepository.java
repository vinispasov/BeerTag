package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;

import com.beertag.models.BeerTag;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.BeerTagsRepository;
import com.beertag.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpBeerTagsRepository implements BeerTagsRepository {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<BeerTag> mJsonParser;


    public HttpBeerTagsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<BeerTag> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public BeerTag createBeerTag(BeerTag newBeerTag) throws IOException {
        String requestBody = mJsonParser.toJson(newBeerTag);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public BeerTag getBeerTagByBeerAndTag(BeerTag beerTag) throws IOException {
        String requestBody = mJsonParser.toJson(beerTag);

        String url = mServerUrl + "/" + "relation";
        String responseBody = mHttpRequester.post(url, requestBody);

        return mJsonParser.fromJson(responseBody);
    }


    @Override
    public boolean isBeerTagCreated(BeerTag beerTag) throws IOException {
        String requestBody = mJsonParser.toJson(beerTag);
        String url = mServerUrl +"/" + "check";

        String responseBody = mHttpRequester.post(url, requestBody);

        return Boolean.valueOf(responseBody);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByBeer(int beerId) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("/")
                .append("beer")
                .append("/")
                .append(beerId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByTag(int tagId) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("/")
                .append("tag")
                .append("/")
                .append(tagId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
