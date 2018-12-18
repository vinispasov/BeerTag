package com.beertag.utils.mappers.base;

import android.os.Parcelable;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BeersMapper extends Serializable{
    BeerDTO mapBeerToDTO(Beer beer, double rating, ArrayList<Tag> tags);
    Beer mapBeerDTOToBeer(BeerDTO beerDTO);
    Map<Integer,BeerDTO> getBeerDtosByBeerId();
    List<Beer> getBeersFromBeerDTO(List<BeerDTO> beerDTOS);
    List<Integer> getBeerIds();
    List<BeerDTO> getBeerDtos();
}
