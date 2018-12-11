package com.beertag.views.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.User;
import com.beertag.utils.Constants;
import com.beertag.views.beerdetails.BeerDetailsContracts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements ProfileContracts.View {

    private ProfileContracts.Presenter mPresenter;

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





    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
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
    public void showUser(User user) {
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
    }

    @Override
    public void setPresenter(ProfileContracts.Presenter presenter) {
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
        Toast
                .makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

}
