package com.beertag.views.createbeer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.Drink;
import com.beertag.utils.Constants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerCreateFragment extends Fragment implements BeerCreateContracts.View{


    private BeerCreateContracts.Presenter mPresenter;

    @BindView(R.id.et_new_beer_title)
    EditText mNewBeerTitle;

    @BindView(R.id.et_new_beer_abv)
    EditText mNewBeerAbv;

    @BindView(R.id.et_new_beer_style)
    EditText mNewBeerStyle;

    @BindView(R.id.et_new_beer_description)
    EditText mNewBeerDescription;

    @BindView(R.id.et_new_beer_brewery)
    EditText mNewBeerBrewery;

    @BindView(R.id.et_new_beer_country)
    EditText mNewBeerCountry;

    @BindView(R.id.et_new_beer_image)
    EditText mNewBeerImage;

    @BindView(R.id.et_new_beer_rating)
    EditText mNewBeerRating;

    @BindView(R.id.et_new_beer_tag)
    EditText mNewBeerTag;

    @BindView(R.id.prb_load_bar_on_add_beer)
    ProgressBar mProgressBarView;

    private BeerCreateContracts.Navigator mNavigator;

    @Inject
    public BeerCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_beer_create, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_save)
    public void onBeerAddButtonClick(View view)  {
        String newTitle = mNewBeerTitle.getText().toString();
        double newBeerAbv = Double.parseDouble(mNewBeerAbv.getText().toString());
        String newBeerStyle = mNewBeerStyle.getText().toString();
        String newBeerDescription = mNewBeerDescription.getText().toString();
        String newBeerBrewery = mNewBeerBrewery.getText().toString();
        String newBeerCountry = mNewBeerCountry.getText().toString();
        double newBeerRating = Double.parseDouble(mNewBeerRating.getText().toString());
        String newBeerTag = mNewBeerTag.getText().toString();
        String newImageUrl = mNewBeerImage.getText().toString();


        Beer newBeer=new Beer(newTitle,newBeerAbv,newBeerStyle,newBeerDescription,newImageUrl,newBeerBrewery,newBeerCountry);

       // view.startAnimation(new AlphaAnimation(Constants.FROM_ALPHA_ANIMATION, Constants.TO_ALPHA_ANIMATION));
        mPresenter.save(newBeer,Constants.MY_USER_ID,newBeerRating);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(BeerCreateContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void navigateToBeersList() {
        mNavigator.navigateToBeersList();
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void hideLoading() {
        mProgressBarView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    public void setNavigator(BeerCreateContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
