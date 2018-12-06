package com.beertag.repositories.base;

import com.beertag.models.BeerTag;

import java.io.IOException;
import java.util.List;

public interface BeerTagsRepository {
    BeerTag createBeerTag(BeerTag newBeerTag) throws IOException;

    BeerTag getBeerTagByBeerAndTag(BeerTag beerTag) throws IOException;

    //BeerTag getBeerTagById(int beerTagId) throws IOException;

    boolean isBeerTagCreated(BeerTag beerTag) throws IOException;

    List<BeerTag> getAllBeersTagsByBeer(int beerId) throws IOException;

    List<BeerTag> getAllBeersTagsByTag(int tagId) throws IOException;
}
