package com.beertag.beertagfinalproject.models.dto_models;

import com.beertag.beertagfinalproject.models.Beer;

import java.util.List;

public class TagDTO {

    private int tagId;

    private String tag;

    private List<Beer> beers;

    public TagDTO(){

    }

    public TagDTO(int tagId, String tag, List<Beer> beers) {
        this.tagId = tagId;
        this.tag = tag;
        this.beers = beers;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
