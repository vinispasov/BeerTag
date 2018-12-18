package com.beertag.views.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beertag.R;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.User;
import com.beertag.views.BaseDrawerActivity;
import com.beertag.views.beerdetails.BeerDetailsActivity;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ProfileActivity extends BaseDrawerActivity implements ProfileContracts.Navigator{

    public static final int DRAWER_IDENTIFIER = 111;
    public static final String PROFILE_EXTRA_KEY = "PROFILE_EXTRA_KEY";
    public static final String PROFILE_EXTRA_KEY_MY_PROFILE = "PROFILE_EXTRA_KEY_MY_PROFILE";


    @Inject
    ProfileFragment mProfileFragment;

    @Inject
    ProfileContracts.Presenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        mProfileFragment.setNavigator(this);
        mProfileFragment.setPresenter(mProfilePresenter);



        Intent intent = getIntent();
        if (intent.getSerializableExtra(PROFILE_EXTRA_KEY_MY_PROFILE)!=null){
            int userId=(Integer)intent.getSerializableExtra(PROFILE_EXTRA_KEY_MY_PROFILE);
             mProfilePresenter.setUserId(userId);
        }
        else {
             User user = (User) intent.getSerializableExtra(ProfileActivity.PROFILE_EXTRA_KEY);
            mProfilePresenter.setUserId(user.getUserId());
        }




        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_profile_details, mProfileFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToBeerDetailsWith(BeerDTO beer) {
        Intent intent = new Intent(this, BeerDetailsActivity.class);
        intent.putExtra(BeerDetailsActivity.BEER_EXTRA_KEY, (Serializable) beer);
        startActivity(intent);
    }

}
