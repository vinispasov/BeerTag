package com.beertag.utils.mappers.base;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Tag;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BeersMapper extends Serializable{
    BeerDTO mapBeerToDTO(Beer beer, double rating, List<Tag> tags);
    Map<Integer,BeerDTO> getBeerDtosByBeerId();
}
