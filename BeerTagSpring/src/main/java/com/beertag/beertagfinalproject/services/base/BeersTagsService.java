package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.BeerTag;
import com.beertag.beertagfinalproject.models.dto_models.BeerTagDTO;

import java.util.List;

public interface BeersTagsService {
    BeerTag createBeerTag(BeerTag newBeerTag);

    BeerTag getBeerTagByBeerAndTag(BeerTagDTO beerTagDTO);

    boolean isBeerTagCreated(BeerTagDTO beerTagDTO);

    List<BeerTag> getAllBeersTagsByBeer(int beerId);

    List<BeerTag> getAllBeersTagsByTag(String tag);
}
