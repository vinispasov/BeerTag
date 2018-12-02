package com.beertag.beertagfinalproject.utils.mappers;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.utils.mappers.base.BeersMapper;
import com.beertag.beertagfinalproject.utils.mappers.base.TagsMapper;

import java.util.Objects;

public class BeersMapperImpl implements BeersMapper {


    @Override
    public BeerDTO mapBeerToDTO(Beer beer) {
        BeerDTO beerDTO = new BeerDTO();
        if (!Objects.equals(beer, null)) {

                TagsMapper mapper = new TagsMapperImpl();

                beerDTO = new BeerDTO(
                        beer.getBeerName(),
                        beer.getBeerAbv(),
                        beer.getBeerStyle(),
                        beer.getBeerDescription(),
                        beer.getBeerPicture(),
                        beer.getBrewery(),
                        beer.getOriginCountry(),
                        beer.isDrank(),
                        beer.getUserId(),
                        beer.getTagId()
                );


        }
        return beerDTO;
    }
}
