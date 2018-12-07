package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.BeerTag;
import com.beertag.beertagfinalproject.models.dto_models.BeerTagDTO;

import java.util.List;

public interface BeersTagsRepository {
    BeerTag createBeerTag(BeerTag newBeerTag);

    BeerTag getBeerTagByBeerAndTag(BeerTagDTO beerTagDTO);

    BeerTag getBeerTagById(int beerTagId);

    boolean isBeerTagCreated(BeerTagDTO beerTagDTO);

    List<BeerTag> getAllBeersTagsByBeer(int beerId);

    List<BeerTag> getAllBeersTagsByTag(String tag);
}
