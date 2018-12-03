package com.beertag.beertagfinalproject.models;

import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.BEERSTAGS_TABLE_NAME)
public class BeerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.BEERSTAGS_TABLE_ID_FIELD)
    private int beertagId;

    @NotNull
    @Column(name = Constants.BEERSTAGS_TABLE_BEER_ID_FIELD)
    private int beerId;

    @NotNull
    @Column(name = Constants.BEERSTAGS_TABLE_TAG_ID_FIELD)
    private int tagId;

    @ManyToOne
    @JoinColumn(name = Constants.BEERSTAGS_TABLE_BEER_ID_FIELD, referencedColumnName = Constants.BEERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private Beer beer;

    @ManyToOne
    @JoinColumn(name = Constants.BEERS_TABLE_TAG_ID_FIELD, referencedColumnName = Constants.TAGS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private Tag tag;

    public BeerTag(){

    }
    public BeerTag(int beerId,int tagId){
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
