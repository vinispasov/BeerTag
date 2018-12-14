package com.beertag.diconfig;

import com.beertag.models.BeerTag;
import com.beertag.models.Drink;
import com.beertag.models.User;
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

    @Provides
    public JsonParser<User> usersJsonParser() {
        return new GsonJsonParser<>(User.class, User[].class);
    }
    @Provides
    public JsonParser<BeerTag> beerTagsJsonParser() {
        return new GsonJsonParser<>(BeerTag.class, BeerTag[].class);
    }
    @Provides
    public JsonParser<Drink> drinksJsonParser() {
        return new GsonJsonParser<>(Drink.class, Drink[].class);
    }
    @Provides
    public JsonParser<Integer> integersJsonParser() {
        return new GsonJsonParser<>(Integer.class, Integer[].class);
    }

}
