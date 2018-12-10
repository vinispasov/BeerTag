package com.beertag.services.base;

import com.beertag.models.Rating;

import java.io.IOException;

public interface RatingsService {

    double getBeerRatingById(int id) throws IOException;

    Rating checkIfBeerAlreadyRatedByVoter(Rating rating) throws IOException;

    Rating submitRating(Rating newRating) throws IOException;
}
