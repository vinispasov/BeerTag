package com.beertag.repositories;

import com.beertag.http.base.HttpRequester;
import com.beertag.models.Beer;
import com.beertag.models.Tag;
import com.beertag.parsers.base.JsonParser;
import com.beertag.repositories.base.TagsRepository;
import com.beertag.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpTagsRepository implements TagsRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Tag> mJsonParser;


    public HttpTagsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Tag> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public Tag getTagById(int tagId) throws IOException {
        String itemJson = mHttpRequester.get(mServerUrl + Constants.TAGS_ROOT_MAPPING +"/"+ tagId);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<Tag> getAllTags() throws IOException {
        String itemsJson = mHttpRequester.get(mServerUrl+Constants.TAGS_ROOT_MAPPING);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public Tag addNewTag(Tag newTag) throws IOException {
        String requestBody = mJsonParser.toJson(newTag);
        String responseBody=mHttpRequester.post(mServerUrl+Constants.TAGS_ROOT_MAPPING, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public void deleteTag(int tagId) throws IOException {
        String deleteServerUrl = mServerUrl + Constants.TAGS_ROOT_MAPPING +"/"+tagId;
        mHttpRequester.delete(deleteServerUrl, tagId);
    }
}
