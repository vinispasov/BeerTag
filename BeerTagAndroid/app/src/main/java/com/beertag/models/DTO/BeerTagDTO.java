package com.beertag.models.DTO;

public class BeerTagDTO {
    private int beerId;
    private int tagId;

    public BeerTagDTO(){

    }

    public BeerTagDTO(int beerId, int tagId){
        setBeerId(beerId);
        setTagId(tagId);
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
