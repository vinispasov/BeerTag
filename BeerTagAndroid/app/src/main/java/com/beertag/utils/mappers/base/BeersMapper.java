package com.beertag.utils.mappers.base;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;

import java.io.Serializable;
import java.util.Map;

public interface BeersMapper extends Serializable{
    BeerDTO mapBeerToDTO(Beer beer,double rating);
    Map<Integer,BeerDTO> getBeerDtosByBeerId();
}
