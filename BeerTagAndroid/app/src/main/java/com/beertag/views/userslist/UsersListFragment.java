package com.beertag.views.userslist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.User;
import com.beertag.utils.Constants;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class UsersListFragment extends Fragment implements UsersListContracts.View{



    @BindView(R.id.lv_users_list_view)
    ListView mUsersListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @Inject
    UsersArrayAdapter mUsersArrayAdapter;


    private UsersListContracts.Presenter mPresenter;
    private UsersListContracts.Navigator mNavigator;


    @Inject
    public UsersListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);
        ButterKnife.bind(this, view);

        mUsersListView.setAdapter(mUsersArrayAdapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.showUsersList();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(UsersListContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showUserDetails(User user) {
        mNavigator.navigateToProfileWith(user);
    }

    @Override
    public void showMyProfile(User user) {
        mNavigator.navigateToProfileWithMyProfile(user);
    }

    @Override
    public void showProgressBarLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
        mUsersListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarLoading() {
        mProgressBarView.setVisibility(View.GONE);
        mUsersListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showAllUsers(List<User> allUsers) {
        mUsersArrayAdapter.clear();
        mUsersArrayAdapter.addAll(allUsers);
        mUsersArrayAdapter.notifyDataSetChanged();
    }


    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    @OnItemClick(R.id.lv_users_list_view)
    public void onItemClick(int position) {

        User selectedUser = mUsersArrayAdapter.getItem(position);
        mPresenter.userIsSelected(selectedUser);
    }

    public void setNavigator(UsersListContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
