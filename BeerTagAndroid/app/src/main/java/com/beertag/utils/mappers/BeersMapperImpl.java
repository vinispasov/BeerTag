package com.beertag.utils.mappers;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.mappers.base.BeersMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BeersMapperImpl implements BeersMapper{

    Map<Integer,BeerDTO> beerDtosByBeerId=new HashMap<>();
    @Override
    public BeerDTO mapBeerToDTO(Beer beer,double rating) {
        BeerDTO beerDTO = new BeerDTO();
        if (!Objects.equals(beer,null)) {

            beerDTO = new BeerDTO(
                    beer.getBeerId(),
                    beer.getBeerName(),
                    beer.getAbvDouble(),
                    beer.getBeerStyle(),
                    beer.getBeerDescription(),
                    beer.getBeerPicture(),
                    beer.getBrewery(),
                    beer.getOriginCountry(),
                    rating
            );

            beerDtosByBeerId.put(beer.getBeerId(),beerDTO);

        }
        return beerDTO;
    }

    @Override
    public Map<Integer, BeerDTO> getBeerDtosByBeerId() {
        return beerDtosByBeerId;
    }
}
