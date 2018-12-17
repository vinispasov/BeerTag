package com.beertag.views.beerdetails;


import android.media.Rating;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Drink;
import com.beertag.models.Tag;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.utils.Constants;
import com.beertag.utils.mappers.BeersMapperImpl;
import com.beertag.utils.mappers.base.BeersMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class BeerDetailsPresenter implements BeerDetailsContracts.Presenter{

    private final BeersService mBeersService;
    private final DrinksService mDrinksService;
    private final BeerTagsService mBeerTagsService;
    private final SchedulerProvider mSchedulerProvider;
    private BeerDetailsContracts.View mView;
    private int mBeerId;
    private int mUserId;
    private BeerDTO mSelectedBeer;
    private Map<Integer,BeerDTO> mBeerDtosByBeerId;
    private BeersMapper mMapper;
    private BeerDTO mBeerToShow;
    private Drink mDrink;
    private Map<Integer,Boolean> isDrankBeersByDrinkId;

    @Inject
    public BeerDetailsPresenter(BeersService beersService, DrinksService drinksService,BeerTagsService beerTagsService,SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mDrinksService=drinksService;
        mBeerTagsService=beerTagsService;
        mSchedulerProvider = schedulerProvider;
        setUserId(1);
        mMapper=new BeersMapperImpl();
        isDrankBeersByDrinkId=new HashMap<>();
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
                    //Beer beer = mBeersService.getBeerById(mBeerId);
                   // BeerDTO beerToShow= getmBeerDtosByBeerId()
                      //      .get(beer.getBeerId());
                    List<Drink>drinksByBeerId=mDrinksService.getAllDrinksByBeerId(mBeerToShow.getBeerId());
                    for (Drink drink:drinksByBeerId
                         ) {
                        if (drink.getUserId()==Constants.MY_USER_ID){
                            setDrink(drink);
                            break;
                        }
                    }

                    if (mDrink==null){
                        Drink drink=new Drink(mBeerToShow.getBeerId(),Constants.MY_USER_ID);
                                mDrinksService.addDrink(drink);
                                setDrink(drink);
                    }

                    emitter.onNext(mBeerToShow);
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
    public void drinkButtonIsClicked() {
        mView.showLoading();
        final String[] message = {""};
        Disposable observable = Observable
                .create((ObservableOnSubscribe<BeerDTO>) emitter -> {
                  //  List<Tag> tagsByBeerId=new ArrayList<>();
                    List<Drink> drinksByBeerId=mDrinksService.getAllDrinksByBeerId(mBeerId);
                    Drink drink=null;
                    BeerDTO beerDTO= null;

                    for (Drink drinkByBeerId:drinksByBeerId
                         ) {
                        if (drinkByBeerId.getUserId()==Constants.MY_USER_ID){
                            drink=drink;
                            break;
                        }
                    }

                    if (drink==null){
                        drink=mDrinksService.addDrink(new Drink(mBeerId,Constants.MY_USER_ID));
                    }

                    if(mDrink==null){
                        setDrink(drink);
                    }

                   /* List<BeerTag> beerTags=mBeerTagsService.getAllBeersTagsByBeer(drink.getBeerId());
                    for (BeerTag beerTag:beerTags
                            ) {
                        tagsByBeerId.add(beerTag.getTag());
                    }*/


                    if (mDrink.isDrank()){
                       message[0] =Constants.ALREADY_DRINKED_MESSAGE;
                       beerDTO=mMapper.mapBeerToDTO(drink.getBeer(),drink.getRating(), (ArrayList<Tag>) mBeerToShow.getTags());
                    }
                    else {
                        drink.setDrank(true);
                        setDrink(drink);
                        getDrankBeersByDrinkId().put(drink.getDrinkId(),true);
                        mDrinksService.setDrankBeer(drink.getDrinkId(),drink);
                        message[0] =Constants.CHEERS;
                        beerDTO=mMapper.mapBeerToDTO(drink.getBeer(), drink.getRating(), (ArrayList<Tag>) mBeerToShow.getTags());
                    }

                    emitter.onNext(beerDTO);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(beerDTO-> {
                            mView.showMessage(message[0].toString());
                            mView.showBeer(beerDTO);
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void ratingWasCancelled() {
        mView.showMessage(Constants.RATING_CANCELLED_MESSAGE);
    }

    @Override
    public void beerIsRated(double ratingValue) {

        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<BeerDTO>) emitter -> {
                 Drink drink = mDrinksService.checkIfBeerIsRated(mBeerId,mUserId);

                    BeerDTO ratedBeer=submitBeerRating(drink,ratingValue);
                    emitter.onNext(ratedBeer);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(ratedBeer-> {
                    mView.showBeer(ratedBeer);
                        },
                        error -> mView.showError(error));
    }


    private BeerDTO submitBeerRating(Drink drink,double rating) throws IOException {

                  drink.setRating(rating);
                  Drink updatedDrink= mDrinksService.rateBeer(mBeerId,mUserId,drink);
                  List<Tag> tagsByBeerId=new ArrayList<>();
                  List<BeerTag>beerTags=mBeerTagsService.getAllBeersTagsByBeer(updatedDrink.getBeerId());
        for (BeerTag beerTag:beerTags
             ) {
            tagsByBeerId.add(beerTag.getTag());
        }

                  BeerDTO beerDTO=mMapper.mapBeerToDTO(updatedDrink.getBeer(),updatedDrink.getRating(), (ArrayList<Tag>) tagsByBeerId);
                  return beerDTO;
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

    /*@Override
    public void setMapper(BeersMapper mapper) {
        mMapper=mapper;
    }*/

    @Override
    public void setUserId(int userId) {
        mUserId=userId;
    }

    @Override
    public int getUserId() {
        return mUserId;
    }

   /* @Override
    public BeersMapper getMapper() {
        return mMapper;
    }*/

    public Map<Integer, BeerDTO> getmBeerDtosByBeerId() {
        return mBeerDtosByBeerId;
    }

    public void setmBeerDtosByBeerId(Map<Integer, BeerDTO> mBeerDtosByBeerId) {
        this.mBeerDtosByBeerId = mBeerDtosByBeerId;
    }

    public BeerDTO getmBeerToShow() {
        return mBeerToShow;
    }

    @Override
    public void setBeerToShow(BeerDTO mBeerToShow) {
        this.mBeerToShow = mBeerToShow;
    }

    @Override
    public Drink getDrink() {
        return mDrink;
    }

    public void setDrink(Drink drink) {
        this.mDrink = drink;
    }

    @Override
    public Map<Integer, Boolean> getDrankBeersByDrinkId() {
        return isDrankBeersByDrinkId;
    }

    public void setIsDrankBeersByDrinkId(Map<Integer, Boolean> isDrankBeersByDrinkId) {
        this.isDrankBeersByDrinkId = isDrankBeersByDrinkId;
    }
}
