package com.beertag.views.beerdetails;

import android.content.Intent;

import android.os.Bundle;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.mappers.base.BeersMapper;
import com.beertag.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BeerDetailsActivity extends BaseDrawerActivity {

    public static final String BEER_EXTRA_KEY = "BEER_EXTRA_KEY";
    public static final int DRAWER_IDENTIFIER = 120;
    public static final String BEERS_DTO_EXTRA_KEY = "BEERS_DTO";

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
        BeerDTO beer = (BeerDTO) intent.getSerializableExtra(BeerDetailsActivity.BEER_EXTRA_KEY);
        BeersMapper mapper = (BeersMapper) intent.getSerializableExtra(BeerDetailsActivity.BEERS_DTO_EXTRA_KEY);


        mBeerDetailsFragment.setPresenter(mBeerDetailsPresenter);
        mBeerDetailsPresenter.setBeerId(beer.getBeerId());
        mBeerDetailsPresenter.setMapper(mapper);

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
