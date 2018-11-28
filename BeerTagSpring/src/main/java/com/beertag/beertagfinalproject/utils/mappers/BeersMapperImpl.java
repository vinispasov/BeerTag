package com.beertag.beertagfinalproject.utils.mappers;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.models.dto_models.TagDTO;
import com.beertag.beertagfinalproject.utils.mappers.base.BeersMapper;
import com.beertag.beertagfinalproject.utils.mappers.base.TagsMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BeersMapperImpl implements BeersMapper {
    @Override
    public BeerDTO mapBeerToDTO(Beer beer) {
        BeerDTO beerDTO = new BeerDTO();

        if (!Objects.equals(beerDTO, null)) {
            TagsMapper mapper=new TagsMapperImpl();
            List<TagDTO> tagsDTOS= beer.getTags().stream().map(tag -> mapper.mapTagToDTO(tag)).collect(Collectors.toList());

            beerDTO = new BeerDTO(
                    beer.getBeerId(),
                    beer.getBeerName(),
                    beer.getBeerAbv(),
                    beer.getBeerStyle(),
                    beer.getBeerDescription(),
                    beer.getBeerPicture(),
                    beer.getBrewery(),
                    beer.getOriginCountry(),
                    beer.isDrank(),
                    beer.getUserId(),
                    tagsDTOS);
        }
        return beerDTO;
    }
}
