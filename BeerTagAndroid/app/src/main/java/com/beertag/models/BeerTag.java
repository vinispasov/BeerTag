package com.beertag.models;

public class BeerTag {
    private int beertagId;
    private int beerId;
    private int tagId;
    private Beer beer;
    private Tag tag;

    public  BeerTag(){

    }

    public BeerTag(int beerId, int tagId) {
        setBeerId(beerId);
        setTagId(tagId);
    }

    public int getBeertagId() {
        return beertagId;
    }

    public void setBeertagId(int beertagId) {
        this.beertagId = beertagId;
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

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
