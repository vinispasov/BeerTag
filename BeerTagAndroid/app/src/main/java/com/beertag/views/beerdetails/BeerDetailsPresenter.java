package com.beertag.views.beerdetails;


import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.Rating;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.RatingsService;
import com.beertag.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class BeerDetailsPresenter implements BeerDetailsContracts.Presenter{

    private final BeersService mBeersService;
    private final RatingsService mRatingsService;
    private final SchedulerProvider mSchedulerProvider;
    private BeerDetailsContracts.View mView;
    private int mBeerId;
    private int mUserId;
    private Beer mSelectedBeer;


    @Inject
    public BeerDetailsPresenter(BeersService beersService,RatingsService ratingsService, SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mSchedulerProvider = schedulerProvider;
        mRatingsService=ratingsService;
    }


    @Override
    public void subscribe(BeerDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadBeer() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Beer>) emitter -> {
                    Beer beer = mBeersService.getBeerById(mBeerId);
                    emitter.onNext(beer);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(beer -> {
                            mView.showBeer(beer);
                            mSelectedBeer = beer;
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void setBeerId(int id) {
        mBeerId=id;
    }

    @Override
    public void rateButtonIsClicked() {
        mView.showRatingDialog();
    }

    @Override
    public void ratingWasCancelled() {
        mView.showMessage(Constants.RATING_CANCELLED_MESSAGE);
    }

    @Override
    public void beerIsRated(int ratingValue) {

        Rating ratingConnectionToCheck = new Rating(mUserId,mBeerId);
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating ratingCheck = mRatingsService.checkIfBeerAlreadyRatedByVoter(ratingConnectionToCheck);
                    emitter.onNext(ratingCheck);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(userRatingResult -> mView.showMessage(Constants.ALREADY_RATED_MESSAGE),
                        error -> {
                            if (error instanceof NullPointerException) {
                                submitBeerRating(ratingValue, mUserId, mBeerId);
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    private void submitBeerRating(int ratingValue, int voterId, int votedForId) {
        Rating newRating = new Rating(voterId, votedForId, ratingValue);

        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating rating = mRatingsService.submitRating(newRating);
                    emitter.onNext(rating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(rating -> {
                            mView.showMessage(Constants.SUCCESSFUL_RATING);
                            //loadOtherBeersRating();
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                mView.showMessage(Constants.UNEXPECTED_ERROR);
                            } else {
                                mView.showError(error);
                            }
                        });

    }

    @Override
    public void loadBeerRating() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Double>) emitter -> {
                    double beerRating = mRatingsService.getBeerRatingById(mBeerId);
                    emitter.onNext(beerRating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(beerRatingResult -> mView.showBeerRating(beerRatingResult),
                        error -> mView.showError(error));
    }
}
