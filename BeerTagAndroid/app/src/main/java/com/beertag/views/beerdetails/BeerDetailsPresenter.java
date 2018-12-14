package com.beertag.views.beerdetails;


import android.media.Rating;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Drink;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.utils.Constants;
import com.beertag.utils.mappers.BeersMapperImpl;
import com.beertag.utils.mappers.base.BeersMapper;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class BeerDetailsPresenter implements BeerDetailsContracts.Presenter{

    private final BeersService mBeersService;
    private final DrinksService mDrinksService;
    private final SchedulerProvider mSchedulerProvider;
    private BeerDetailsContracts.View mView;
    private int mBeerId;
    private int mUserId;
    private BeerDTO mSelectedBeer;
    private BeersMapper mMapper;


    @Inject
    public BeerDetailsPresenter(BeersService beersService, DrinksService drinksService,SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mDrinksService=drinksService;
        mSchedulerProvider = schedulerProvider;
        setUserId(1);
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
                .create((ObservableOnSubscribe<BeerDTO>) emitter -> {
                    Beer beer = mBeersService.getBeerById(mBeerId);
                    BeerDTO beerToShow=mMapper
                            .getBeerDtosByBeerId()
                            .get(beer.getBeerId());

                    emitter.onNext(beerToShow);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(beerToShow -> {
                            mView.showBeer(beerToShow);
                            mSelectedBeer = beerToShow;
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
    public void beerIsRated(double ratingValue) {

        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Drink>) emitter -> {
                 Drink drink = mDrinksService.checkIfBeerIsRated(mBeerId,mUserId);
                    emitter.onNext(drink);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(drink-> {
                            if(drink.getRating()!=0){
                                mView.showMessage(Constants.ALREADY_RATED_MESSAGE);
                            }
                            else{
                                submitBeerRating(drink,ratingValue);
                            }
                        },
                        error -> mView.showError(error));
    }


    private void submitBeerRating(Drink drink,double rating) {

        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Drink>) emitter -> {

                    //Map<Integer,BeerDTO> beersDtosByBeerId= getMapper().getBeerDtosByBeerId();
                    drink.setRating(rating);
                  Drink updatedDrink= mDrinksService.rateBeer(mBeerId,mUserId,drink);

                    emitter.onNext(updatedDrink);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(updatedDrink -> {
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

   /* @Override
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
    }*/

    @Override
    public void setMapper(BeersMapper mapper) {
        mMapper=mapper;
    }

    @Override
    public void setUserId(int userId) {
        mUserId=userId;
    }

    @Override
    public int getUserId() {
        return mUserId;
    }

    @Override
    public BeersMapper getMapper() {
        return mMapper;
    }
}
