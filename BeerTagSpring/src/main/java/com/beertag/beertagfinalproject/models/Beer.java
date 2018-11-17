package com.beertag.beertagfinalproject.models;


import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = Constants.BEERS_TABLE_NAME)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.BEERS_TABLE_ID_COLUMN_NAME)
    private int beerId;

    @NotNull
    @Size(min = Constants.BEER_NAME_MIN_LENGTH, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.BEERS_TABLE_BEER_NAME_COLUMN)
    private String beerName;

    @NotNull
    @DecimalMin(value = Constants.BEERS_TABLE_MIN_ABV_VALUE)
    @DecimalMax(value = Constants.BEERS_TABLE_MAX_ABV_VALUE)
    @Column(name = Constants.BEERS_TABLE_ABV_COLUMN)
    private double beerAbv;

    @Column(name=Constants.BEERS_TABLE_BEER_STYLE_COLUMN)
    private String beerStyle;

    @Column(name=Constants.BEERS_TABLE_BEER_DESCRIPTION_COLUMN)
    private String beerDescription;

    @Column(name=Constants.BEERS_TABLE_BEER_PICTURE_COLUMN)
    private String beerPicture;

    @Column(name=Constants.BEERS_TABLE_BREWERY_COLUMN)
    private String brewery;

    @NotNull
    @Size(min = Constants.COUNTRY_NAME_MIN_LENGTH, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name=Constants.BEERS_TABLE_ORIGIN_COUNTRY_COLUMN)
    private String originCountry;

    @NotNull
    @Column(name=Constants.BEERS_TABLE_IS_DRANK_COLUMN)
    private boolean isDrank;

    public Beer(){

    }

    public Beer(String beerName,double beerAbv,String beerStyle,String beerDescription, String beerPicture,String brewery,String originCountry,boolean isDrank){
        setBeerName(beerName);
        setBeerAbv(beerAbv);
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


}