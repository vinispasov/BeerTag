package com.beertag.views.beerdetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.Constants;
import com.beertag.views.BaseDrawerActivity;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.beertag.utils.Constants.*;


public class BeerDetailsFragment extends Fragment implements BeerDetailsContracts.View, RatingDialogListener {

    private BeerDetailsContracts.Presenter mPresenter;

    @BindView(R.id.prb_load_view)
    ProgressBar mProgressLoadView;

    @BindView(R.id.tv_beer_name_details)
    TextView mBeerNameTextView;

    @BindView(R.id.tv_beer_rating)
    TextView mBeerRatingTextView;

    @BindView(R.id.btn_rate)
    Button mRateBeerButton;

    @BindView(R.id.btn_drink)
    Button mDrinkButton;

    @BindView(R.id.tv_beer_abv_details_field)
    TextView mAbvFieldTextView;

    @BindView(R.id.tv_beer_abv_details)
    TextView mAbvTextView;

    @BindView(R.id.tv_beer_style_details_field)
    TextView mBeerStyleFieldTextView;
    @BindView(R.id.tv_beer_style_details)
    TextView mBeerStyleTextView;

    @BindView(R.id.tv_beer_brewery_details_field)
    TextView mBeerBreweryFieldTextView;
    @BindView(R.id.tv_beer_brewery_details)
    TextView mBeerBreweryTextView;

    @BindView(R.id.tv_beer_country_details_field)
    TextView mBeerCountryFieldTextView;
    @BindView(R.id.tv_beer_country_details)
    TextView mBeerCountryTextView;

    @BindView(R.id.tv_beer_description_details_field)
    TextView mBeerDescriptionFieldTextView;
    @BindView(R.id.tv_beer_description_details)
    TextView mBeerDescriptionTextView;

    @BindView(R.id.tv_beer_tags_details_field)
    TextView mBeerTagsFieldTextView;
    @BindView(R.id.tv_beer_tags_details)
    TextView mBeerTagsTextView;


    @BindView(R.id.beer_image)
    ImageView mBeerImageView;

    @Inject
    public BeerDetailsFragment() {
        // Required empty constructor here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beer_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadBeer();
       // mPresenter.loadBeerRating();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showBeer(BeerDTO beer) {
        Picasso
                .get()
                .load(beer.getBeerPicture())
                .into(mBeerImageView);

        mRateBeerButton.setVisibility(View.VISIBLE);
        mDrinkButton.setVisibility(View.VISIBLE);

        mBeerRatingTextView.setText(beer.getRatingString());

        mBeerNameTextView.setText(beer.getBeerName());

        mAbvFieldTextView.setText(Constants.BEER_ABV_FIELD);
        mAbvTextView.setText(beer.getAbv());

        mBeerStyleFieldTextView.setText(Constants.BEER_STYLE_FIELD);
        mBeerStyleTextView.setText(beer.getBeerStyle());

        mBeerBreweryFieldTextView.setText(Constants.BEER_BREWERY_FIELD);
        mBeerBreweryTextView.setText(beer.getBrewery());

        mBeerCountryFieldTextView.setText(Constants.BEER_COUNTRY_FIELD);
        mBeerCountryTextView.setText(beer.getOriginCountry());

        mBeerDescriptionFieldTextView.setText(Constants.BEER_DESCRIPTION_FIELD);
        mBeerDescriptionTextView.setText(beer.getBeerDescription());

        mBeerTagsFieldTextView.setText(Constants.BEER_TAGS_FIELD);
        mBeerTagsTextView.setText(beer.getTagsAsString().toString());



            if (mPresenter.getDrink().isDrank()){
                mDrinkButton.setText(Constants.DRANK);
            }

    }

    @Override
    public void setPresenter(BeerDetailsContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {
        mProgressLoadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressLoadView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        if (message.equals(Constants.CHEERS)){
            mDrinkButton.setText(Constants.DRANK);
        }
        Toast
                .makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }



  /*  @Override
    public void showBeerRating(double rating) {
        String ratingRepresentation = String.format(Locale.UK, "%.1f", rating) + Constants.RATING_REPRESENTATION;
        mBeerRatingTextView.setText(ratingRepresentation);
    }*/
    @OnClick(R.id.btn_rate)
    public void onRateButtonClick() {
        mPresenter.rateButtonIsClicked();
    }
    @Override
    public void onNegativeButtonClicked() {
        mPresenter.ratingWasCancelled();
    }

    @Override
    public void onNeutralButtonClicked() {
        mPresenter.ratingWasCancelled();
    }

    @OnClick(R.id.btn_drink)
    public void onDrinkButtonClick() {
        mPresenter.drinkButtonIsClicked();
    }

    @Override
    public void onPositiveButtonClicked(int ratingValue, String s) {
        mPresenter.beerIsRated(ratingValue);
    }

    @Override
    public void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText(Constants.SUBMIT_OPTION)
                .setNegativeButtonText(Constants.CANCEL_OPTION)
                .setNoteDescriptions(Arrays.asList(ONE_STAR_RATING_TEXT, TWO_STAR_RATING_TEXT, THREE_STAR_RATING_TEXT, FOUR_STAR_RATING_TEXT, FIVE_STAR_RATING_TEXT))
                .setDefaultRating(1)
                .setTitle(Constants.RATE_DIALOG_TITLE_MESSAGE)
                .setDescription(Constants.RATE_DIALOG_DESCRIPTION_MESSAGE)
                .setCommentInputEnabled(false)
                .setStarColor(R.color.colorAccent)
                .setTitleTextColor(R.color.colorAccent)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .create(getActivity())
                .setTargetFragment(this, 0)
                .show();
    }
}
