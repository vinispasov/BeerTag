package com.beertag.diconfig;

import com.beertag.http.OkHttpHttpRequester;
import com.beertag.http.base.HttpRequester;
import com.beertag.utils.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
