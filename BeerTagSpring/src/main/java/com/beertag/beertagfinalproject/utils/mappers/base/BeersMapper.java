package com.beertag.beertagfinalproject.utils.mappers.base;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;

public interface BeersMapper {
    BeerDTO mapBeerToDTO(Beer beer);
}
