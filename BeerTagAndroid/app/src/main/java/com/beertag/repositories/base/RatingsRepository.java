package com.beertag.repositories.base;

import com.beertag.models.Rating;

import java.io.IOException;
import java.util.List;

public interface RatingsRepository {
    List<Rating> getBeerRatingById(int id) throws IOException;

    Rating checkIfBeerAlreadyRatedByVoter(Rating rating) throws IOException;

    Rating submitRating(Rating newRating) throws IOException;

}
