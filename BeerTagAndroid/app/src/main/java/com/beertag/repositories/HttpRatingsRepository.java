package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.models.Rating;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.RatingsRepository;
import com.beertag.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpRatingsRepository implements RatingsRepository {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Rating> mJsonParser;

    public HttpRatingsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Rating> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public List<Rating> getBeerRatingById(int id) throws IOException {

        String url = mServerUrl + "/ratings/" + id;
        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }

    @Override
    public Rating checkIfBeerAlreadyRatedByVoter(Rating rating) throws IOException {
        String requestBody = mJsonParser.toJson(rating);

        String postServerUrl = mServerUrl +"/ratings/check";
        String responseBody = mHttpRequester.post(postServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);

    }

    @Override
    public Rating submitRating(Rating newRating) throws IOException {
        String requestBody = mJsonParser.toJson(newRating);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);

    }
}
