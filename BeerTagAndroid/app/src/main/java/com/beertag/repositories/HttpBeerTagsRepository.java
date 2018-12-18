package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;

import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerTagDTO;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.BeerTagsRepository;
import com.beertag.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpBeerTagsRepository implements BeerTagsRepository {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<BeerTag> mJsonParser;
    private final JsonParser<BeerTagDTO> mJsonParserDTO;


    public HttpBeerTagsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<BeerTag> jsonParser,JsonParser<BeerTagDTO> jsonParserDTO) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
        mJsonParserDTO=jsonParserDTO;
    }


    @Override
    public BeerTagDTO createBeerTag(BeerTagDTO newBeerTag) throws IOException {
        String requestBody = mJsonParserDTO.toJson(newBeerTag);
        String responseBody = mHttpRequester.post(mServerUrl+Constants.BEERSTAGS_ROOT_MAPPING, requestBody);

        return mJsonParserDTO.fromJson(responseBody);
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
                .append("/beerstags/")
                .append("beer")
                .append("/")
                .append(beerId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByTag(String tag) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append("beerstags/")
                .append("tag")
                .append("/")
                .append(tag)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
