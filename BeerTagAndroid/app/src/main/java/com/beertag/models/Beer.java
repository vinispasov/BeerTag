package com.beertag.models;

import java.io.Serializable;

public class Beer implements Serializable{
    private int beerId;
    private String beerName;
    private double rating;
    private double abv;
    private String beerStyle;
    private String beerDescription;
    private String beerPicture;
    private String brewery;
    private String originCountry;
    private boolean isDrank;

    public Beer(){

    }

    public Beer(String beerName,double rating, double abv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry, boolean isDrank) {
       setBeerName(beerName);
       setRating(rating);
       setAbv(abv);
       setBeerStyle(beerStyle);
       setBeerDescription(beerDescription);
       setBeerPicture(beerPicture);
       setBrewery(brewery);
       setOriginCountry(originCountry);
       setDrank(isDrank);
    }

    public int getBeerId() {
        return beerId;
    }

    private void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getBeerName() {
        return beerName;
    }

    private void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public CharSequence getAbv() {
        StringBuilder sb=new StringBuilder();
        sb.append(abv);
        return sb;
    }

    private void setAbv(double abv) {
        this.abv = abv;
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

    public boolean isDrank() {
        return isDrank;
    }

    private void setDrank(boolean drank) {
        isDrank = drank;
    }

    public CharSequence getRating() {
        StringBuilder sb=new StringBuilder();
        sb.append(rating);
        return sb;
    }
    public double getAbvDouble(){
        return abv;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
