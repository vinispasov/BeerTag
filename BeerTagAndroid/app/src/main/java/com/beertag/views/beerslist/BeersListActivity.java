package com.beertag.views.beerslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.mappers.base.BeersMapper;
import com.beertag.views.BaseDrawerActivity;
import com.beertag.views.beerdetails.BeerDetailsActivity;

import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BeersListActivity extends BaseDrawerActivity implements BeersListContracts.Navigator{

    public static final int DRAWER_IDENTIFIER = 112;
    @Inject
    BeersListFragment mBeersListFragment;

    @Inject
    BeersListPresenter mBeersListPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_beers_list);
        ButterKnife.bind(this);

        mBeersListFragment.setNavigator(this);
        mBeersListFragment.setPresenter(mBeersListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_beers_list_fragment, mBeersListFragment)
                .commit();
    }


    @Override
    public void navigateToBeerDetailsWith(BeerDTO beer, BeersMapper mapper) {

        Intent intent = new Intent(this, BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_EXTRA_KEY, beer);
        intent.putExtra(BeerDetailsActivity.BEERS_DTO_EXTRA_KEY,mapper);

        startActivity(intent);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
