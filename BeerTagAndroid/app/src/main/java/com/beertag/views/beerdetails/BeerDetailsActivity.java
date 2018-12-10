package com.beertag.views.beerdetails;

import android.content.Intent;

import android.os.Bundle;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BeerDetailsActivity extends BaseDrawerActivity {

    public static final String BEER_EXTRA_KEY = "BEER_EXTRA_KEY";
    public static final int DRAWER_IDENTIFIER = 120;

    @Inject
    BeerDetailsFragment mBeerDetailsFragment;

    @Inject
    BeerDetailsContracts.Presenter mBeerDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);


        ButterKnife.bind(this);
        Intent intent = getIntent();
        Beer beer = (Beer) intent.getSerializableExtra(BeerDetailsActivity.BEER_EXTRA_KEY);

        mBeerDetailsPresenter.setBeerId(beer.getBeerId());
        mBeerDetailsFragment.setPresenter(mBeerDetailsPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_beer_details, mBeerDetailsFragment)
                .commit();
    }


    //should not return valid identifier
    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
