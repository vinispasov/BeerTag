package com.beertag.services;

import com.beertag.models.BeerTag;
import com.beertag.repositories.base.BeerTagsRepository;
import com.beertag.services.base.BeerTagsService;

import java.io.IOException;
import java.util.List;

public class HttpBeerTagsService implements BeerTagsService {
    private final BeerTagsRepository mBeerTagsRepository;


    public HttpBeerTagsService(BeerTagsRepository beerTagsRepository) {
       mBeerTagsRepository=beerTagsRepository;
    }


    @Override
    public BeerTag createBeerTag(BeerTag newBeerTag) throws IOException {
        return mBeerTagsRepository.createBeerTag(newBeerTag);
    }

    @Override
    public BeerTag getBeerTagByBeerAndTag(BeerTag beerTag) throws IOException {
        return mBeerTagsRepository.getBeerTagByBeerAndTag(beerTag);
    }

    @Override
    public boolean isBeerTagCreated(BeerTag beerTag) throws IOException {
       return mBeerTagsRepository.isBeerTagCreated(beerTag);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByBeer(int beerId) throws IOException {
        return mBeerTagsRepository.getAllBeersTagsByBeer(beerId);
    }

    @Override
    public List<BeerTag> getAllBeersTagsByTag(String tag) throws IOException {
        return mBeerTagsRepository.getAllBeersTagsByTag(tag);
    }
}
