package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.models.User;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.UsersListRepository;

import java.io.IOException;
import java.util.List;

public class HttpUsersListRepository implements UsersListRepository {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<User> mJsonParser;


    public HttpUsersListRepository(String serverUrl, HttpRequester httpRequester, JsonParser<User> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public User createUser(User userToCreate) throws IOException {
        String requestBody = mJsonParser.toJson(userToCreate);
        String responseBody=mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public User getUserById(int userId) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + "/" + userId);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public User updateUser(User userToUpdate, int userId) throws IOException {
        String updateServerUrl = mServerUrl + "/" + userId;
        String requestBody = mJsonParser.toJson(userToUpdate);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody, userId);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<User> getUsers() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(itemsJson);
    }
}
