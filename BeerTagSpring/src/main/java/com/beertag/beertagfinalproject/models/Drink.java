package com.beertag.beertagfinalproject.models;

import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.DRINKS_TABLE_NAME)
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.DRINKS_TABLE_ID_FIELD)
    private int drinkId;

    @NotNull
    @Column(name = Constants.DRINKS_TABLE_BEER_ID_FIELD)
    private int beerId;

    @NotNull
    @Column(name = Constants.DRINKS_TABLE_USER_ID_FIELD)
    private int userId;

    @ManyToOne
    @JoinColumn(name = Constants.DRINKS_TABLE_BEER_ID_FIELD, referencedColumnName = Constants.BEERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private Beer beer;

    @ManyToOne
    @JoinColumn(name = Constants.DRINKS_TABLE_USER_ID_FIELD, referencedColumnName = Constants.USERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private User user;

    @Column(name = Constants.DRINKS_TABLE_RATING_FIELD)
    private double rating;

    @Column(name = Constants.DRINKS_TABLE_IS_DRANK_FIELD)
    private boolean isDrank;





    public Drink(){

    }

    public Drink(@NotNull int beerId, @NotNull int userId) {
        setBeerId(beerId);
        setUserId(userId);
    }

    public Drink(@NotNull int beerId, @NotNull int userId, Beer beer, User user, double rating, boolean isDrank) {
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
