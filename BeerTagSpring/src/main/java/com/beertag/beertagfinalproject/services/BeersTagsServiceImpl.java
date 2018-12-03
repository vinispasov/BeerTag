package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.BeerTag;
import com.beertag.beertagfinalproject.models.dto_models.BeerTagDTO;
import com.beertag.beertagfinalproject.repositories.base.BeersTagsRepository;
import com.beertag.beertagfinalproject.services.base.BeersTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeersTagsServiceImpl implements BeersTagsService {
    private  final BeersTagsRepository beersTagsRepository;

    @Autowired
    public BeersTagsServiceImpl(BeersTagsRepository beersTagsRepository){
        this.beersTagsRepository=beersTagsRepository;
    }


    @Override
    public BeerTag createBeerTag(BeerTag newBeerTag) {
        return beersTagsRepository.createBeerTag(newBeerTag);
    }

    @Override
    public BeerTag getBeerTagByBeerAndTag(BeerTagDTO beerTagDTO) {
        return beersTagsRepository.getBeerTagByBeerAndTag(beerTagDTO);
    }

    @Override
    public boolean isBeerTagCreated(BeerTagDTO beerTagDTO) {
        return beersTagsRepository.isBeerTagCreated(beerTagDTO);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByBeer(int beerId) {
        return beersTagsRepository.getAllBeersTagsByBeer(beerId);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByTag(int tagId) {
        return beersTagsRepository.getAllBeersTagsByTag(tagId);
    }
}
