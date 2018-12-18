package com.beertag.views.beerslist;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beertag.R;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;
import com.beertag.utils.mappers.base.BeersMapper;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;


public class BeersListFragment extends Fragment implements BeersListContracts.View,AdapterView.OnItemSelectedListener{

    @BindView(R.id.et_search_beers)
    EditText mSearchBar;


    @BindView(R.id.lv_beers_list_view)
    ListView mBeersListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.sp_search_options)
    NiceSpinner mFilterOptionsSpinner;

    @BindView(R.id.rv_beers_recycler_view)
    RecyclerView mBeersRecyclerView;

    @BindView(R.id.tv_no_beers)
    TextView mNoBeersTextView;

    @Inject
    BeersArrayAdapter mBeersArrayAdapter;




    private String[] mFilterOptions;
    private String mSelectedFilterOption;
    private SharedPreferences mPreferences;
    private LinearLayoutManager mBeersGridLayoutManager;

    private DeletionDialog mDeletionDialog;
    private AlphaAnimation mButtonClickAnimation;
    private BeersListContracts.Presenter mPresenter;
    private BeersListContracts.Navigator mNavigator;
    private BeersMapper mMapper;


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


        mBeersGridLayoutManager = new LinearLayoutManager(getContext());
        mBeersRecyclerView.setLayoutManager(mBeersGridLayoutManager);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        mFilterOptions = getResources().getStringArray(R.array.filter_options);
        mFilterOptionsSpinner.attachDataSource(Arrays.asList(mFilterOptions));
        mSelectedFilterOption = mFilterOptions[0];


        mFilterOptionsSpinner.setOnItemSelectedListener(this);

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
    public void showAllBeers(List<BeerDTO> allBeers) {
        if (allBeers.isEmpty()){
            this.showMessage(Constants.NO_BEERS_AVAILABLE_MESSAGE);
        }
        else {
            mBeersArrayAdapter.clear();
            mBeersArrayAdapter.addAll(allBeers);
            mBeersArrayAdapter.notifyDataSetChanged();
            mFilterOptionsSpinner.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showDialogForDeletion(BeerDTO beerToDelete) {

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
        BeerDTO selectedBeer = mBeersArrayAdapter.getItem(position);
        mPresenter.beerIsSelected(selectedBeer);
    }

    @OnItemLongClick(R.id.lv_beers_list_view)
    public boolean onItemLongClick(int position) {
        BeerDTO beerToDelete = mBeersArrayAdapter.getItem(position);
        mPresenter.beerForDeletionIsSelected(beerToDelete);
        return true;
    }


    @Override
    public void showBeerDetails(BeerDTO beer) {
        mNavigator.navigateToBeerDetailsWith(beer);
    }

    @Override
    public void setPresenter(BeersListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(BeersListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void showFilteredBeers(List<BeerDTO> beersResult) {
       if(beersResult.isEmpty()){
           this.showMessage(Constants.NO_BEERS_FOUND_ON_SEARCH_MESSAGE);
       }
        mBeersArrayAdapter.clear();
        mBeersArrayAdapter.addAll(beersResult);
        mBeersArrayAdapter.notifyDataSetChanged();
        mFilterOptionsSpinner.setVisibility(View.VISIBLE);
    }


    @OnTextChanged(R.id.et_search_beers)
    public void onTextChanged() {
        String searchQuery = mSearchBar
                .getText()
                .toString();
        mPresenter.filterBeersWith(searchQuery);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        mSelectedFilterOption = mFilterOptions[position];
        mPresenter.sortBeersWithOption(mSelectedFilterOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mSelectedFilterOption = mFilterOptions[0];
    }


    public BeersMapper getmMapper() {
        return mMapper;
    }

    public void setmMapper(BeersMapper mMapper) {
        this.mMapper = mMapper;
    }
}
