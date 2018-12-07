package com.beertag.utils;

public class Constants {

    public static final String BASE_SERVER_URL = "http://192.168.0.106:7777/api/";

    public static final String BASE_SERVER_URL_VALUE_NAME = "baseServerUrl";

    public static final float FROM_ALPHA_ANIMATION = 1F;
    public static final float TO_ALPHA_ANIMATION = 0.3F;
    public static final String ERROR_MESSAGE = "Error: ";
    public static final String NO_BEERS_AVAILABLE_MESSAGE = "Your beers list is empty";
    public static final String NO_BEERS_FOUND_ON_SEARCH_MESSAGE = "No songs match your search";
    public static final String SUCCESSFUL_DELETION_OF_BEER = "Beer was successfully deleted!";
    public static final String ADD_OF_BEER_FAIL_MESSAGE = "You must fill in all fields correctly!";




    public static final double MIN_BEER_ABV =0.0;
    public static final double MAX_BEER_ABV =80.0;

    public static final int MIN_COUNTRY_FIELD_LENGTH = 2;
    public static final int MAX_COUNTRY_FIELD_LENGTH = 30;
    public static final int MIN_BEER_IMAGE_URL_LENGTH = 5;
    public static final int MAX_BEER_IMAGE_URL_LENGTH = 350;



    public static final int MIN_USERNAME_CHAR_COUNT = 2;
    public static final int MAX_USERNAME_CHAR_COUNT = 20;
    public static final int MIN_FIRSTNAME_CHAR_COUNT = 2;
    public static final int MAX_FIRSTNAME_CHAR_COUNT = 40;
    public static final int MIN_LASTNAME_CHAR_COUNT = 2;
    public static final int MAX_LASTNAME_CHAR_COUNT = 40;

    public static final String ADD_OF_USER_FAIL_MESSAGE = "You must fill in all fields correctly!";

    public static final String NO_USERS_AVAILABLE_MESSAGE = "Your users list is empty";
    public static final String PREFERENCES_BEERS_LISTING_TYPE_KEY = "listingType";
    public static final String EMPTY_STRING = "";

    public static final String FILTER_OPTION_ALL = "All";
    public static final String FILTER_OPTION_BY_TAG = "Only by tag";
    public static final String FILTER_OPTION_BY_STYLE = "Only by style";
    public static final String FILTER_OPTION_BY_COUNTRY = "Only by country";

    public static final String SORTED_OPTION_BY_RATING = "Sort by rating";
    public static final String SORTED_OPTION_BY_ABV = "Sort by abv";
    public static final String SORTED_OPTION_BY_ALPHABET = "Sort by alphabet";

    public static final String COMPACT_VIEW_STYLE = "Compact";
    public static final String BEERS_ROOT_MAPPING = "beers/";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_RATING = "beers/sort/rating";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_ABV = "beers/sort/abv";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_NAME = "beers/sort/name";

}
