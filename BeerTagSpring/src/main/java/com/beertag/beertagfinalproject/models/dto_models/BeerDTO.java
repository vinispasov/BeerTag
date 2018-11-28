package com.beertag.beertagfinalproject.models.dto_models;

import java.util.List;

public class BeerDTO {

    private int beerId;

    private String beerName;

    private double beerAbv;

    private String beerStyle;

    private String beerDescription;

    private String beerPicture;

    private String brewery;

    private String originCountry;

    private boolean isDrank;

    private int userId;

    private List<TagDTO> tags;

    public BeerDTO(){

    }

    public BeerDTO(int beerId, String beerName, double beerAbv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry, boolean isDrank, int userId, List<TagDTO> tags) {
        setBeerId(beerId);
        setBeerName(beerName);
        setBeerAbv(beerAbv);
        setBeerStyle(beerStyle);
        setBeerDescription(beerDescription);
        setBeerPicture(beerPicture);
        setBrewery(brewery);
        setOriginCountry(originCountry);
        setDrank(isDrank);
        setUserId(userId);
        setTags(tags);
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public double getBeerAbv() {
        return beerAbv;
    }

    public void setBeerAbv(double beerAbv) {
        this.beerAbv = beerAbv;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerPicture() {
        return beerPicture;
    }

    public void setBeerPicture(String beerPicture) {
        this.beerPicture = beerPicture;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public boolean isDrank() {
        return isDrank;
    }

    public void setDrank(boolean drank) {
        isDrank = drank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }
}
