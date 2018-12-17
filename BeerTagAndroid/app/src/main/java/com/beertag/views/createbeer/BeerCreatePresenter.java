package com.beertag.views.createbeer;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.Drink;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.utils.Constants;
import com.beertag.utils.validators.base.Validator;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class BeerCreatePresenter implements BeerCreateContracts.Presenter {

    private final BeersService mBeersService;
    private final DrinksService mDrinksService;
    private final SchedulerProvider mSchedulerProvider;
    private BeerCreateContracts.View mView;
    private Validator<Beer> mValidator;

    @Inject
    public BeerCreatePresenter(BeersService beersService,DrinksService drinksService, SchedulerProvider schedulerProvider,Validator<Beer> validator) {
        mBeersService = beersService;
        mDrinksService=drinksService;
        mSchedulerProvider = schedulerProvider;
        mValidator=validator;
    }

    @Override
    public void subscribe(BeerCreateContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView=null;
    }

    @Override
    public void save(Beer newBeer,int userId,double rating) {

        mView.showLoading();
        final String[] message = {""};
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Drink>) emitter -> {
                    Boolean isValid=mValidator.isItemValid(newBeer);
                    Drink drink=null;
                         if (isValid){
                             Beer beer=createBeer(newBeer);
                              drink=new Drink(beer.getBeerId(),userId,rating);
                             mDrinksService.addDrink(drink);
                             message[0] =Constants.ADD_OF_BEER_SUCCESS_MESSAGE;
                         }
                         else{
                             message[0] =Constants.ADD_OF_BEER_FAIL_MESSAGE;
                         }


                    emitter.onNext(drink);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(s -> {
                    mView.showMessage(message[0]);
                    mView.navigateToBeersList();
                }, error->mView.showError(error));
    }

    public Beer createBeer(Beer newBeer) {
        Beer beer= null;
        try {
            beer = mBeersService.addBeer(newBeer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beer;
    }
}
