package com.beertag.utils.validators;

import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;
import com.beertag.models.Beer;

import java.util.Objects;

public class BeerValidator implements Validator<Beer> {

    @Override
    public boolean isItemValid(Beer beer) {
        return (!Objects.equals(beer, null)) &&
                isAbvValid(beer)
                && isOriginCountryValid(beer)
                && isImageUrlValid(beer);
    }

    private boolean isAbvValid(Beer beer) {
        return beer.getAbvDouble() >= Constants.MIN_BEER_ABV &&
                beer.getAbvDouble()<= Constants.MAX_BEER_ABV;
    }

    private boolean isOriginCountryValid(Beer beer) {
        return beer.getOriginCountry().length()>= Constants.MIN_COUNTRY_FIELD_LENGTH &&
                beer.getOriginCountry().length() <= Constants.MAX_COUNTRY_FIELD_LENGTH;
    }

    private boolean isImageUrlValid(Beer beer) {
        return beer.getBeerPicture().length() >= Constants.MIN_BEER_IMAGE_URL_LENGTH &&
                beer.getBeerPicture().length() <= Constants.MAX_BEER_IMAGE_URL_LENGTH;
    }

}
