package com.beertag.views.beerdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beertag.R;

public class BeerDetailsActivity extends AppCompatActivity {

    public static final String BEER_EXTRA_KEY = "BEER_EXTRA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
    }
}
