package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.Rating;
import com.beertag.beertagfinalproject.models.dto_models.RatingDTO;

import java.util.List;

public interface RatingsService {

    Rating addRating(Rating newRating);

    List<Rating> getRatingsByBeerId(int id);

    Rating isAlreadyRated(RatingDTO ratingDTO);
}
