package com.beertag.beertagfinalproject.models.dto_models;

public class DrinkDTO {
    private int drinkId;
    private int beerId;
    private int userId;
    private double rating;
    private boolean isDrank;
    private double avgRating;

    public DrinkDTO(int beerId, int userId) {
        this.beerId = beerId;
        this.userId = userId;
    }

    public DrinkDTO(int beerId, int userId, double rating, boolean isDrank, double avgRating) {
        this.beerId = beerId;
        this.userId = userId;
        this.rating = rating;
        this.isDrank = isDrank;
        this.avgRating = avgRating;
    }
    public DrinkDTO(int drinkId,int beerId, int userId, double rating, boolean isDrank, double avgRating) {
        this.drinkId=drinkId;
        this.beerId = beerId;
        this.userId = userId;
        this.rating = rating;
        this.isDrank = isDrank;
        this.avgRating = avgRating;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isDrank() {
        return isDrank;
    }

    public void setDrank(boolean drank) {
        isDrank = drank;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }
}
