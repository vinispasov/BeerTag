package com.beertag.beertagfinalproject.models;

import com.beertag.beertagfinalproject.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name =  Constants.TAGS_TABLE_NAME)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.TAGS_TABLE_ID_COLUMN_NAME)
    private int tagId;

    @NotNull
    @Column(name = Constants.TAGS_TABLE_TAG_NAME_COLUMN_NAME)
    private String tag;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = Constants.BEERSTAGS_TABLE_NAME,
            joinColumns = @JoinColumn(name = Constants.TAGS_TABLE_ID_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = Constants.BEERS_TABLE_ID_COLUMN_NAME)
    )
    private List<Beer> beers;

    public Tag(){

    }

    public Tag(String tag){
        setTag(tag);
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
