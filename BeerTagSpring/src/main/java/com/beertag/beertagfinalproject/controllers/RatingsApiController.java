package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.Rating;
import com.beertag.beertagfinalproject.models.dto_models.RatingDTO;
import com.beertag.beertagfinalproject.services.base.RatingsService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.RATINGS_ROOT_MAPPING)
public class RatingsApiController {
    private final RatingsService ratingsService;

    @Autowired
    public RatingsApiController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public Rating addRating(@RequestBody @Valid Rating newRating) {
        return ratingsService.addRating(newRating);
    }

    @RequestMapping(value = "/beers/{beerId}", method = RequestMethod.GET)
    public List<Rating> getRatingsByBeerId(@PathVariable int beerId) {
        return ratingsService.getRatingsByBeerId(beerId);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Rating isAlreadyRated(@RequestBody RatingDTO ratingDTO) {
        return ratingsService.isAlreadyRated(ratingDTO);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public List<Rating> getRatingsByUserId(@PathVariable int userId) {
        return ratingsService.getRatingsByUserId(userId);
    }
}
