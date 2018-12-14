package com.beertag.utils.mappers;

import android.os.Parcel;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Tag;
import com.beertag.utils.mappers.base.BeersMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BeersMapperImpl implements BeersMapper{

    private Map<Integer,BeerDTO> beerDtosByBeerId=new HashMap<>();
    private List<Integer> beerIds=new ArrayList<>();
    private List<BeerDTO> beerDtos=new ArrayList<>();



    @Override
    public BeerDTO mapBeerToDTO(Beer beer, double rating, ArrayList<Tag> tags) {
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
                    rating,
                    tags
            );

            beerDtosByBeerId.put(beer.getBeerId(),beerDTO);
            beerIds.add(beer.getBeerId());
            beerDtos.add(beerDTO);

        }
        return beerDTO;
    }

    @Override
    public Map<Integer, BeerDTO> getBeerDtosByBeerId() {
        return beerDtosByBeerId;
    }

    @Override
    public List<Beer> getBeersFromBeerDTO(List<BeerDTO> beerDTOS) {
        List<Beer> beers=new ArrayList<>();
        for (BeerDTO beerDto :beerDTOS
                ) {
            beers.add(
                    new Beer(
                            beerDto.getBeerId(),
                    beerDto.getBeerName(),
                    beerDto.getAbvDouble(),
            beerDto.getBeerStyle(),
            beerDto.getBeerDescription(),
            beerDto.getBeerPicture(),
            beerDto.getBrewery(),
            beerDto.getOriginCountry()
            ));
        }
        return beers;
    }

    public List<Integer> getBeerIds() {
        return beerIds;
    }

    public List<BeerDTO> getBeerDtos() {
        return beerDtos;
    }

}
