package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.Rating;
import com.beertag.beertagfinalproject.repositories.base.RatingsRepository;
import com.beertag.beertagfinalproject.services.base.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {
    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public Rating addRating(Rating newRating) {
        return ratingsRepository.addRating(newRating);
    }

    @Override
    public List<Rating> getRatingsByBeerId(int id) {
        return ratingsRepository.getRatingsByBeerId(id);
    }

    @Override
    public Rating isAlreadyRated(Rating rating) {
        return ratingsRepository.isAlreadyRated(rating);
    }
}
