package com.beertag.views.beerslist;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.BeerTag;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.beertag.utils.Constants.FILTER_OPTION_ALL;
import static com.beertag.utils.Constants.FILTER_OPTION_BY_COUNTRY;
import static com.beertag.utils.Constants.FILTER_OPTION_BY_STYLE;
import static com.beertag.utils.Constants.FILTER_OPTION_BY_TAG;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ABV;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ALPHABET;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_RATING;

public class BeersListPresenter implements BeersListContracts.Presenter {

    private BeersListContracts.View mView;
    private final BeersService mBeersService;
    private final BeerTagsService mBeerTagsService;
    private final SchedulerProvider mSchedulerProvider;
    private String mCurrentSelectedOption;

    @Inject
    BeersListPresenter(BeersService beersService,BeerTagsService beerTagsService, SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mBeerTagsService=beerTagsService;
        mSchedulerProvider = schedulerProvider;

    }


    @Override
    public void subscribe(BeersListContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void showBeersList() {

        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Beer>>) emitter -> {
                    List<Beer> beers = mBeersService.getAllBeers();
                    emitter.onNext(beers);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allBeers -> presentBeersToView(allBeers, Constants.NO_BEERS_AVAILABLE_MESSAGE), mView::showError);
    }

   @Override
    public void filterBeersWith(String searchQuery) {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Beer>>) emitter -> {
                    List<Beer> beersResultSet = mBeersService.getFilteredBeers(searchQuery);
                    /*List<BeerTag>beersFilteredByTags= mBeerTagsService.getAllBeersTagsByTag(searchQuery);

                    beersFilteredByTags
                            .stream()
                            .map(beerTag->beerTag.getBeer())
                            .forEach(beersResultSet::add);*/

                    emitter.onNext(beersResultSet);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allBeers -> presentBeersToView(allBeers, Constants.NO_BEERS_FOUND_ON_SEARCH_MESSAGE),
                        error -> mView.showError(error));
    }

    @Override
    public void presentBeersToView(List<Beer> allBeers, String message) {

        if (allBeers.isEmpty()) {
            mView.showMessage(message);
        } else {
            mView.showAllBeers(allBeers);
        }
    }

    @Override
    public void beerForDeletionIsSelected(Beer beerToDelete) {
        mView.showDialogForDeletion(beerToDelete);
    }

    @Override
    public void getActionOnConfirmedDeletion(Beer beerToDelete) {

        int idOfBeerToDelete = beerToDelete.getBeerId();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<Void>) emitter -> {
                    mBeersService.deleteBeer(idOfBeerToDelete);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doOnError(error -> mView.showError(error))
                .doOnComplete(() -> {
                    mView.showMessage(Constants.SUCCESSFUL_DELETION_OF_BEER);
                    this.showBeersList();
                })
                .doFinally(mView::hideDeletionDialog)
                .subscribe();
    }

    @Override
    public void getActionOnCancelledDeletion() {
        mView.hideDeletionDialog();
    }

    @Override
    public void beerIsSelected(Beer beer) {
        mView.showBeerDetails(beer);
    }

    @Override
    public void filterBeersWithOption(String preference, String selectedOption) {
        if (selectedOption.equals(mCurrentSelectedOption)) {
            return;
        }
        filterBeers(preference, selectedOption);
        mCurrentSelectedOption = selectedOption;
    }

    private void presentBeersToViewAccordingToPreference(List<Beer> beersResult, String preference) {

        //if there is no preference chosen already or the preference is compact view
        if (preference.equals(Constants.EMPTY_STRING) || preference.equals(Constants.COMPACT_VIEW_STYLE)) {
            mView.showCompactBeersView(beersResult);
        } else {
            //mView.showDetailedBeersView(beersResult);
        }
    }

    private void filterBeers(String preference, String selectedOption) {
        mView.showProgressBarLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Beer>>) emitter -> {
                    List<Beer> beers = new ArrayList<>();
                    switch (selectedOption) {
                        case FILTER_OPTION_ALL:
                            beers = mBeersService.getAllBeers();
                            break;
                        case SORTED_OPTION_BY_RATING:
                            beers = mBeersService.getAllBeersSortedByRating();
                            break;
                        case SORTED_OPTION_BY_ABV:
                            beers= mBeersService.getAllBeersSortedByAbv();
                            break;
                        case SORTED_OPTION_BY_ALPHABET:
                            beers=mBeersService.getAllBeersSortedByName();
                            break;
                    }
                    emitter.onNext(beers);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(beersResult -> presentBeersToViewAccordingToPreference(beersResult, preference),
                        error -> mView.showError(error));

    }
}
