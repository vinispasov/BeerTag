package com.beertag.diconfig;

import com.beertag.parsers.GsonJsonParser;
import com.beertag.parsers.base.JsonParser;
import com.beertag.models.Beer;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Beer> beersJsonParser() {
        return new GsonJsonParser<>(Beer.class, Beer[].class);
    }
}
