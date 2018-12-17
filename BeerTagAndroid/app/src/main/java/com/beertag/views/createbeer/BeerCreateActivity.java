package com.beertag.views.createbeer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.beertag.R;
import com.beertag.views.BaseDrawerActivity;
import com.beertag.views.beerslist.BeersListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BeerCreateActivity extends BaseDrawerActivity implements BeerCreateContracts.Navigator{
    public static final long DRAWER_IDENTIFIER = 123;
    @Inject
    BeerCreateFragment mBeerCreateFragment;

    @Inject
    BeerCreateContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_create);
        ButterKnife.bind(this);

        mBeerCreateFragment.setPresenter(mPresenter);
        mBeerCreateFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_beer_create, mBeerCreateFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToBeersList() {
        Intent intent = new Intent(this, BeersListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
