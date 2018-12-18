package com.beertag.views.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.UserDTO;
import com.beertag.models.User;
import com.beertag.services.base.UsersService;
import com.beertag.utils.Constants;
import com.beertag.views.beerdetails.BeerDetailsContracts;
import com.beertag.views.beerslist.BeersArrayAdapter;
import com.beertag.views.beerslist.BeersListContracts;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ProfileFragment extends Fragment implements ProfileContracts.View,AdapterView.OnItemSelectedListener {



    private ProfileContracts.Presenter mPresenter;

    @Inject
    TopThreeBeersArrayAdapter mBeersArrayAdapter;

    @BindView(R.id.prb_load_view)
    ProgressBar mProgressLoadView;

    @BindView(R.id.user_image)
    ImageView mUserImageView;


    @BindView(R.id.tv_user_name_field)
    TextView mUserNameFieldTextView;
    @BindView(R.id.tv_user_name)
    TextView mUserNameTextView;

    @BindView(R.id.tv_first_name_field)
    TextView mFirstNameFieldTextView;
    @BindView(R.id.tv_first_name)
    TextView mFirstNameTextView;

    @BindView(R.id.tv_last_name_field)
    TextView mLastNameFieldTextView;
    @BindView(R.id.tv_last_name)
    TextView mLastNameTextView;

    @BindView(R.id.lv_beers_profile_list_view)
    ListView mBeersListView;

    @BindView(R.id.top_rated_field)
    TextView mTopRatedTextView;


    private String[] mFilterOptions;
    private String mSelectedFilterOption;
    private ProfileContracts.Navigator mNavigator;

    @Inject
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        mBeersListView.setAdapter(mBeersArrayAdapter);

        mFilterOptions = getResources().getStringArray(R.array.filter_options);
        mSelectedFilterOption = mFilterOptions[0];

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUser();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showUser(UserDTO user) {
        Picasso
                .get()
                .load(user.getUserPicture())
                .into(mUserImageView);

        mUserNameFieldTextView.setText(Constants.USER_NAME_FIELD);
        mUserNameTextView.setText(user.getUserName());

        mFirstNameFieldTextView.setText(Constants.FIRST_NAME_FIELD);
        mFirstNameTextView.setText(user.getFirstName());

        mLastNameFieldTextView.setText(Constants.LAST_NAME_FIELD);
        mLastNameTextView.setText(user.getLastName());

        if (user.getBeers().isEmpty()){
            this.showMessage(Constants.NO_BEERS_AVAILABLE_MESSAGE);
        }
        else {
            showBeers(user.getBeers());
        }
    }

    @Override
    public void setPresenter(ProfileContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    public void setNavigator(ProfileContracts.Navigator navigator) {
        mNavigator = navigator;
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
        mBeersListView.setVisibility(View.INVISIBLE);
        mProgressLoadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressLoadView.setVisibility(View.INVISIBLE);
        mBeersListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showBeers(List<BeerDTO> topThreeBeers) {
        mBeersArrayAdapter.clear();
        mBeersArrayAdapter.addAll(topThreeBeers);
        mBeersArrayAdapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.lv_beers_profile_list_view)
    public void onItemClick(int position) {

        BeerDTO selectedBeer = mBeersArrayAdapter.getItem(position);
        mPresenter.beerIsSelected(selectedBeer);

    }

    @Override
    public void showBeerDetails(BeerDTO beer) {
        mNavigator.navigateToBeerDetailsWith(beer);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSelectedFilterOption = mFilterOptions[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSelectedFilterOption=mFilterOptions[0];
    }
}
