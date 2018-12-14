package com.beertag.models.DTO;

import com.beertag.models.Tag;
import com.beertag.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BeerDTO implements Serializable{
    private int beerId;
    private String beerName;
    private double beerAbv;
    private String beerStyle;
    private String beerDescription;
    private String beerPicture;
    private String brewery;
    private String originCountry;
    private double rating;
    private List<Tag> tags;



    public BeerDTO(){

    }

    public BeerDTO(int beerId,String beerName, double beerAbv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry,double rating,List<Tag> tags) {
        setBeerId(beerId);
        setBeerName(beerName);
        setAbv(beerAbv);
        setBeerStyle(beerStyle);
        setBeerDescription(beerDescription);
        setBeerPicture(beerPicture);
        setBrewery(brewery);
        setOriginCountry(originCountry);
        setRating(rating);
        setTags(tags);
    }


    public String getBeerName() {
        return beerName;
    }

    private void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getAbv() {
        StringBuilder sb=new StringBuilder();
        sb.append(beerAbv);
        return sb.toString();
    }

    private void setAbv(double abv) {
        this.beerAbv = abv;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    private void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    private void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerPicture() {
        return beerPicture;
    }

    private void setBeerPicture(String beerPicture) {
        this.beerPicture = beerPicture;
    }

    public String getBrewery() {
        return brewery;
    }

    private void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    private void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public double getAbvDouble(){
        return beerAbv;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingString() {
        String ratingRepresentation = String.format(Locale.UK, "%.1f", rating) + Constants.RATING_REPRESENTATION;
        return ratingRepresentation;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public List<String> getTagsAsString(){
        List<String> tagsAsStrings=new ArrayList<>();
        for (Tag tag:tags
             ) {
            tagsAsStrings.add(tag.getTag());
        }
        return tagsAsStrings;
    }
}
