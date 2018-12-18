package com.beertag.models;

import java.io.Serializable;
import java.util.List;

public class Tag implements Serializable{
    private int tagId;

    private String tag;


    public Tag(){

    }


    public Tag(String tag) {
        this.tag = tag;
    }
    public Tag(int tagId, String tag) {
        this.tagId = tagId;
        this.tag = tag;
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

}
