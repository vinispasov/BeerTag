package com.beertag.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Beer implements Serializable{
    private int beerId;
    private String beerName;
    private double beerAbv;
    private String beerStyle;
    private String beerDescription;
    private String beerPicture;
    private String brewery;
    private String originCountry;





    public Beer(){

    }

    public Beer(String beerName, double beerAbv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry) {
        setBeerName(beerName);
       setAbv(beerAbv);
       setBeerStyle(beerStyle);
       setBeerDescription(beerDescription);
       setBeerPicture(beerPicture);
       setBrewery(brewery);
       setOriginCountry(originCountry);
    }
    public Beer(int beerId,String beerName, double beerAbv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry) {
        setBeerId(beerId);
        setBeerName(beerName);
        setAbv(beerAbv);
        setBeerStyle(beerStyle);
        setBeerDescription(beerDescription);
        setBeerPicture(beerPicture);
        setBrewery(brewery);
        setOriginCountry(originCountry);
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


}
