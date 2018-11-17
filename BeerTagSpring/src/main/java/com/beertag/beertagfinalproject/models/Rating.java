package com.beertag.beertagfinalproject.models;

import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.RATINGS_TABLE_NAME)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.RATINGS_TABLE_ID_COLUMN_NAME)
    private int ratingId;

    @NotNull
    @Column(name = Constants.RATINGS_TABLE_VOTER_ID_COLUMN)
    private int voterId;

    @NotNull
    @Column(name = Constants.RATINGS_TABLE_VOTED_FOR_ID_COLUMN)
    private int votedForId;

    @NotNull
    @DecimalMin(value = Constants.RATINGS_TABLE_MIN_RATING_VALUE)
    @DecimalMax(value = Constants.RATINGS_TABLE_MAX_RATING_VALUE)
    @Column(name = Constants.RATINGS_TABLE_RATING_COLUMN)
    private double rating;

    public Rating() {

    }

    public Rating(int voterId, int votedForId, double rating) {
        setVoterId(voterId);
        setVotedForId(votedForId);
        setRating(rating);
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public int getVotedForId() {
        return votedForId;
    }

    public void setVotedForId(int votedForId) {
        this.votedForId = votedForId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
