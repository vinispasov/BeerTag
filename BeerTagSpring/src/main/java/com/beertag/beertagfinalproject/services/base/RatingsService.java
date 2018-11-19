package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.Rating;

import java.util.List;

public interface RatingsService {

    Rating addRating(Rating newRating);

    List<Rating> getRatingsByBeerId(int id);

    Rating isAlreadyRated(Rating rating);
}
