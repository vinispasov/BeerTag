package com.beertag.views.beerslist;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;


public class BeersListFragment extends Fragment implements BeersListContracts.View{


    @BindView(R.id.lv_beers_list_view)
    ListView mBeersListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @Inject
    BeersArrayAdapter mBeersArrayAdapter;

    private DeletionDialog mDeletionDialog;
    private AlphaAnimation mButtonClickAnimation;
    private BeersListContracts.Presenter mPresenter;
    private BeersListContracts.Navigator mNavigator;


    @Inject
    public BeersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beers_list, container, false);
        ButterKnife.bind(this, view);

        mBeersListView.setAdapter(mBeersArrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.showBeersList();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showAllBeers(List<Beer> allBeers) {
        mBeersArrayAdapter.clear();
        mBeersArrayAdapter.addAll(allBeers);
        mBeersArrayAdapter.notifyDataSetChanged();
    }


    @Override
    public void showDialogForDeletion(Beer beerToDelete) {

        setupDeletionDialog();
        mDeletionDialog.show();

        mDeletionDialog.mPositiveDialogButton
                .setOnClickListener(view -> {
                    view.startAnimation(mButtonClickAnimation);
                    mPresenter.getActionOnConfirmedDeletion(beerToDelete);
                });
        mDeletionDialog.mNegativeDialogButton
                .setOnClickListener(view -> {
                    view.startAnimation(mButtonClickAnimation);
                    mPresenter.getActionOnCancelledDeletion();
                });
    }

    private void setupDeletionDialog() {
        mDeletionDialog = new DeletionDialog(this.getContext());
        mButtonClickAnimation = new AlphaAnimation(Constants.FROM_ALPHA_ANIMATION, Constants.TO_ALPHA_ANIMATION);
    }

    @Override
    public void hideDeletionDialog() {
        mDeletionDialog.cancel();
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }


    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showProgressBarLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
        mBeersListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarLoading() {

        mProgressBarView.setVisibility(View.GONE);
        mBeersListView.setVisibility(View.VISIBLE);
    }

    @OnItemClick(R.id.lv_beers_list_view)
    public void onItemClick(int position) {

        Beer selectedBeer = mBeersArrayAdapter.getItem(position);
        mPresenter.beerIsSelected(selectedBeer);
    }

    @OnItemLongClick(R.id.lv_beers_list_view)
    public boolean onItemLongClick(int position) {
        Beer beerToDelete = mBeersArrayAdapter.getItem(position);
        mPresenter.beerForDeletionIsSelected(beerToDelete);
        return true;
    }


    @Override
    public void showBeerDetails(Beer beer) {
        mNavigator.navigateToBeerDetailsWith(beer);
    }

    @Override
    public void setPresenter(BeersListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(BeersListContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
