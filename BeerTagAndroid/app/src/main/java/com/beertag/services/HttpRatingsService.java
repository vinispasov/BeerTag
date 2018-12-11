package com.beertag.services;

import com.beertag.models.Rating;
import com.beertag.repositories.base.RatingsRepository;
import com.beertag.services.base.RatingsService;

import java.io.IOException;
import java.util.List;

public class HttpRatingsService implements RatingsService{


    private final RatingsRepository mRatingsRepository;

    public HttpRatingsService(RatingsRepository ratingsRepository) {
        mRatingsRepository = ratingsRepository;
    }

    @Override
    public double getBeerRatingById(int id) throws IOException {
        List<Rating> beerRatings = mRatingsRepository.getBeerRatingById(id);

        double rating = 0;
        int size = beerRatings.size();
        if (size == 0) {
            return rating;
        } else {
            for (int i = 0; i < beerRatings.size(); i++) {
                rating += beerRatings.get(i).getRating();
            }
        }
        return rating / size;
    }

    @Override
    public Rating checkIfBeerAlreadyRatedByVoter(Rating rating) throws IOException {

        return mRatingsRepository.checkIfBeerAlreadyRatedByVoter(rating);

    }

    @Override
    public Rating submitRating(Rating newRating) throws IOException {
        return mRatingsRepository.submitRating(newRating);
    }
}
