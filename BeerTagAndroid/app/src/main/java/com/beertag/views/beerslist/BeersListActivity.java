package com.beertag.views.beerslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.views.BaseDrawerActivity;
import com.beertag.views.beerdetails.BeerDetailsActivity;

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

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_beers_list_fragment, mBeersListFragment)
                .commit();
    }


    @Override
    public void navigateToBeerDetailsWith(Beer beer) {

        Intent intent = new Intent(this, BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_EXTRA_KEY, beer);
        startActivity(intent);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
