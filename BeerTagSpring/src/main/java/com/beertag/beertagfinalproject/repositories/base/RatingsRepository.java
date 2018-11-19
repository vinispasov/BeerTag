package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.Rating;

import java.util.List;

public interface RatingsRepository {

    Rating addRating(Rating newRating);

    List<Rating> getRatingsByBeerId(int id);

    Rating isAlreadyRated(Rating rating);
}
