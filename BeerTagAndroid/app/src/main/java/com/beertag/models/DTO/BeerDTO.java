package com.beertag.models.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import com.beertag.models.Beer;
import com.beertag.models.Tag;
import com.beertag.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BeerDTO implements Serializable,Parcelable{
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BeerDTO createFromParcel(Parcel in) {
            return new BeerDTO(in);
        }

        public BeerDTO[] newArray(int size) {
            return new BeerDTO[size];
        }
    };
    private int beerId;
    private String beerName;
    private double beerAbv;
    private String beerStyle;
    private String beerDescription;
    private String beerPicture;
    private String brewery;
    private String originCountry;
    private double rating;
    private ArrayList<Tag>tags;



    public BeerDTO(){

    }

    public BeerDTO(int beerId,String beerName, double beerAbv, String beerStyle, String beerDescription, String beerPicture, String brewery, String originCountry,double rating,ArrayList<Tag> tags) {
        setBeerId(beerId);
        setBeerName(beerName);
        setAbv(beerAbv);
        setBeerStyle(beerStyle);
        setBeerDescription(beerDescription);
        setBeerPicture(beerPicture);
        setBrewery(brewery);
        setOriginCountry(originCountry);
        setRating(rating);
        setTags(tags);
    }


    public String getBeerName() {
        return beerName;
    }

    private void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getAbv() {
        StringBuilder sb=new StringBuilder();
        sb.append(beerAbv);
        return sb.toString();
    }

    private void setAbv(double abv) {
        this.beerAbv = abv;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    private void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    private void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerPicture() {
        return beerPicture;
    }

    private void setBeerPicture(String beerPicture) {
        this.beerPicture = beerPicture;
    }

    public String getBrewery() {
        return brewery;
    }

    private void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    private void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public double getAbvDouble(){
        return beerAbv;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingString() {
        String ratingRepresentation = String.format(Locale.UK, "%.1f", rating) + Constants.RATING_REPRESENTATION;
        return ratingRepresentation;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
    public List<String> getTagsAsString(){
        List<String> tagsAsStrings=new ArrayList<>();
        for (Tag tag:tags
             ) {
            tagsAsStrings.add(tag.getTag());
        }
        return tagsAsStrings;
    }
    public BeerDTO(Parcel in){
        this.beerId = in.readInt();
        this.beerName = in.readString();
        this.beerAbv =  in.readDouble();
        this.beerStyle=in.readString();
        this.beerDescription=in.readString();
        this.beerPicture=in.readString();
        this.brewery=in.readString();
        this.originCountry=in.readString();
        this.rating=in.readDouble();
        this.tags=in.readArrayList(Tag.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.beerId);
        dest.writeString(this.beerName);
        dest.writeDouble(this.beerAbv);
        dest.writeString(this.beerStyle);
        dest.writeString(this.beerDescription);
        dest.writeString(this.beerPicture);
        dest.writeString(this.brewery);
        dest.writeString(this.originCountry);
        dest.writeDouble(this.rating);
        dest.writeList((List)this.tags);
    }
}
