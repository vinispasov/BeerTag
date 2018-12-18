package com.beertag.utils;

public class Constants {

    public static final String BASE_SERVER_URL = "http://192.168.0.105:7777/api/";

    public static final String BASE_SERVER_URL_VALUE_NAME = "baseServerUrl";

    public static final float FROM_ALPHA_ANIMATION = 1F;
    public static final float TO_ALPHA_ANIMATION = 0.3F;
    public static final String ERROR_MESSAGE = "Error: ";
    public static final String NO_BEERS_AVAILABLE_MESSAGE = "Your beers list is empty";
    public static final String NO_BEERS_FOUND_ON_SEARCH_MESSAGE = "No beers match your search";
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
    public static final String BEERS_ROOT_MAPPING = "beers";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_RATING = "beers/sort/rating";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_ABV = "beers/sort/abv";
    public static final String BEERS_ROOT_MAPPING_SORT_BY_NAME = "beers/sort/name";

    public static final String BEER_ABV_FIELD = "ABV: ";
    public static final String BEER_STYLE_FIELD = "Style: ";
    public static final String BEER_BREWERY_FIELD = "Brewery: ";
    public static final String BEER_COUNTRY_FIELD = "Country: ";
    public static final String BEER_DESCRIPTION_FIELD = "Description: ";
    public static final String BEER_TAGS_FIELD = "Tags: ";


    public static final String RATE_DIALOG_TITLE_MESSAGE = "RATE BEER";
    public static final String RATE_DIALOG_DESCRIPTION_MESSAGE = "Please select some stars and give your feedback!";
    public static final String RATING_CANCELLED_MESSAGE = "Rating was cancelled!";
    public static final String ALREADY_RATED_MESSAGE = "You have already submitted your rating!";
    public static final String SUCCESSFUL_RATING = "Your feedback was successfully submitted!";

    public static final String RATING_REPRESENTATION = "/5";

    public static final String ONE_STAR_RATING_TEXT = "Very Bad";
    public static final String TWO_STAR_RATING_TEXT = "Not Good";
    public static final String THREE_STAR_RATING_TEXT = "Neutral";
    public static final String FOUR_STAR_RATING_TEXT = "Very Good";
    public static final String FIVE_STAR_RATING_TEXT = "Excellent!";

    public static final String SUBMIT_OPTION = "Submit";
    public static final String CANCEL_OPTION = "Cancel";

    public static final String UNEXPECTED_ERROR = "Something went wrong. Please try again!";
    public static final String USER_NAME_FIELD = "Username";
    public static final String FIRST_NAME_FIELD = "First name";
    public static final String LAST_NAME_FIELD = "Last name";
    public static final String DRINKS_ROOT_MAPPING = "drinks";
    public static final int MY_USER_ID = 1;
    public static final String ADD_OF_BEER_SUCCESS_MESSAGE = "Beer was added successfully";
    public static final String CHEERS = "Cheers";
    public static final String ALREADY_DRINKED_MESSAGE = "The beer is already drinked";
    public static final String DRANK = "DRANK";
    public static final String INVALID_PARAMETERS = "Invalid parameters entered.Try again.";
    public static final String SHOULD_FILL_ALL_FIELDS = "You should fill all fields correctly.";
    public static final String SHOULD_FILL_THE_FIELD = "You should fill the field correctly";
    public static final String ADD_OF_TAG_SUCCESS_MESSAGE = "Tag was successfully added!";
    public static final String BEERSTAGS_ROOT_MAPPING = "beerstags";
    public static String TAGS_ROOT_MAPPING = "tags";
}
