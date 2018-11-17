package com.beertag.beertagfinalproject.utils;

public class Constants {
    public static final String HIBERNATE_CONFIGURATION_FILE_NAME = "hibernate.cfg.xml";

    public static final String USERS_TABLE_NAME ="users";
    public static final String USERS_TABLE_ID_COLUMN_NAME = "user_id";
    public static final String USERS_TABLE_USER_NAME_COLUMN = "user_name";
    public static final String USERS_TABLE_USER_PASSWORD_COLUMN = "user_password";
    public static final String USERS_TABLE_USER_FIRST_NAME_COLUMN = "first_name";
    public static final String USERS_TABLE_USER_LAST_NAME_COLUMN = "last_name";
    public static final String USERS_TABLE_USER_PICTURE_COLUMN = "user_picture";



    public static final String BEERS_TABLE_NAME ="beers";
    public static final String BEERS_TABLE_ID_COLUMN_NAME = "beer_id";
    public static final String BEERS_TABLE_BEER_NAME_COLUMN = "beer_name";
    public static final String BEERS_TABLE_ABV_COLUMN = "beer_abv";
    public static final String BEERS_TABLE_BEER_STYLE_COLUMN = "beer_style";
    public static final String BEERS_TABLE_BEER_DESCRIPTION_COLUMN = "beer_description";
    public static final String BEERS_TABLE_BEER_PICTURE_COLUMN = "beer_picture";
    public static final String BEERS_TABLE_BREWERY_COLUMN ="brewery";
    public static final String BEERS_TABLE_ORIGIN_COUNTRY_COLUMN = "origin_country";
    public static final String BEERS_TABLE_IS_DRANK_COLUMN ="is_drank";
    public static final String BEERS_TABLE_MIN_ABV_VALUE ="0.0";
    public static final String BEERS_TABLE_MAX_ABV_VALUE = "70.0";



    public static final String RATINGS_TABLE_NAME = "ratings";
    public static final String RATINGS_TABLE_ID_COLUMN_NAME = "rating_id";
    public static final String RATINGS_TABLE_VOTER_ID_COLUMN = "voter_id";
    public static final String RATINGS_TABLE_VOTED_FOR_ID_COLUMN = "voted_for_id";
    public static final String RATINGS_TABLE_RATING_COLUMN = "rating";
    public static final String RATINGS_TABLE_MIN_RATING_VALUE = "0.0";
    public static final String RATINGS_TABLE_MAX_RATING_VALUE = "5.0";



    public static final int TEXT_VALIDATION_MIN_VALUE = 3;
    public static final int TEXT_VALIDATION_MAX_VALUE = 45;


    public static final int STRING_VALIDATION_MAX_TEXT = 100;

    public static final int FIRST_LAST_NAME_MIN_LENGTH = 2;
    public static final int BEER_NAME_MIN_LENGTH = 2;
    public static final int COUNTRY_NAME_MIN_LENGTH = 2;



}
