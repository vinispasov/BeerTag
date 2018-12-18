package com.beertag.models;

public class Drink {
    private int drinkId;
    private int beerId;
    private int userId;
    private Beer beer;
    private User user;
    private double rating;
    private boolean isDrank;


    public Drink(){

    }

    public Drink(int beerId,int userId){
        setBeerId(beerId);
        setUserId(userId);
    }
    public Drink(int beerId,int userId,double rating){
        setBeerId(beerId);
        setUserId(userId);
        setRating(rating);
    }
    public Drink(int beerId,int userId,double rating,boolean isDrank){
        setBeerId(beerId);
        setUserId(userId);
        setRating(rating);
        setDrank(isDrank);
    }


    public Drink(int beerId, int userId, Beer beer, User user, double rating, boolean isDrank) {
        this.beerId = beerId;
        this.userId = userId;
        this.beer = beer;
        this.user = user;
        this.rating = rating;
        this.isDrank = isDrank;
    }

    public Drink(int drinkId, int beerId, int userId, Beer beer, User user, double rating, boolean isDrank) {
        this.drinkId = drinkId;
        this.beerId = beerId;
        this.userId = userId;
        this.beer = beer;
        this.user = user;
        this.rating = rating;
        this.isDrank = isDrank;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
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

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
