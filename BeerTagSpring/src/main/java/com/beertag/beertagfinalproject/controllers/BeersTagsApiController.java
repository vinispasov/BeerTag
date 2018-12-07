package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.BeerTag;
import com.beertag.beertagfinalproject.models.dto_models.BeerTagDTO;
import com.beertag.beertagfinalproject.services.base.BeersTagsService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.BEERSTAGS_ROOT_MAPPING)
public class BeersTagsApiController {
    private final BeersTagsService beersTagsService;

    @Autowired
    public BeersTagsApiController(BeersTagsService beersTagsService){
        this.beersTagsService=beersTagsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public BeerTag createBeerTag(@RequestBody @Valid BeerTag newBeerTag) {
        return beersTagsService.createBeerTag(newBeerTag);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public boolean isBeerTagCreated(@RequestBody BeerTagDTO beerTagDTO) {
        return beersTagsService.isBeerTagCreated(beerTagDTO);
    }

    @RequestMapping(value = "/relation", method = RequestMethod.POST)
    public BeerTag getBeerTagByBeerAndTag(@RequestBody BeerTagDTO beerTagDTO) {
        return beersTagsService.getBeerTagByBeerAndTag(beerTagDTO);
    }


    @RequestMapping(value = "/beer/{beerId}", method = RequestMethod.GET)
    public List<BeerTag> getAllBeersTagsByBeer(@PathVariable int beerId) {
        return beersTagsService.getAllBeersTagsByBeer(beerId);
    }

    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public List<BeerTag> getAllBeersTagsByTag(@PathVariable String tag) {
        return beersTagsService.getAllBeersTagsByTag(tag);
    }

}
