package com.beertag.beertagfinalproject.models.dto_models;

public class RatingDTO {
    private int voterId;
    private int votedForId;

    public RatingDTO() {

    }

    public RatingDTO(int voterId, int votedForId) {
        setVoterId(voterId);
        setVotedForId(votedForId);
    }

    public int getVoterId() {
        return voterId;
    }

    public int getVotedForId() {
        return votedForId;
    }

    private void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    private void setVotedForId(int votedForId) {
        this.votedForId = votedForId;
    }
}
