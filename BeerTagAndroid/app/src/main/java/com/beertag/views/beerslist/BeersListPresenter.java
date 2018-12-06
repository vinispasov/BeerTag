package com.beertag.views.beerslist;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.services.base.BeersService;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class BeersListPresenter implements BeersListContracts.Presenter {

    private BeersListContracts.View mView;
    private final BeersService mBeersService;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    BeersListPresenter(BeersService beersService, SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
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
}
