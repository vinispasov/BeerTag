package com.beertag.views.beerdetails;


import android.media.DrmInitData;
import android.media.Rating;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.BeerTagDTO;
import com.beertag.models.Drink;
import com.beertag.models.Tag;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.services.base.TagsService;
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
    private final TagsService mTagsService;
    private final SchedulerProvider mSchedulerProvider;
    private BeerDetailsContracts.View mView;
    private int mBeerId;
    private int mUserId;
    private BeerDTO mSelectedBeer;
    private Map<Integer,BeerDTO> mBeerDtosByBeerId;
    private BeersMapper mMapper;
    private BeerDTO mBeerToShow;
    private Drink mDrink;
    private  Map<Integer,Boolean> mDrankBeersByDrinkId;

    @Inject
    public BeerDetailsPresenter(BeersService beersService, DrinksService drinksService,BeerTagsService beerTagsService,TagsService tagsService,SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mDrinksService=drinksService;
        mBeerTagsService=beerTagsService;
        mTagsService=tagsService;
        mSchedulerProvider = schedulerProvider;
        setUserId(1);
        mMapper=new BeersMapperImpl();
        mDrankBeersByDrinkId=new HashMap<>();
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
                               Drink newDrink= mDrinksService.addDrink(drink);
                                setDrink(newDrink);
                    }

                    if (getDrankBeersByDrinkId().containsKey(mDrink.getDrinkId())){
                        mDrink.setDrank(getDrankBeersByDrinkId().get(mDrink.getDrinkId()));
                    }
                   // mBeerToShow.setRating(mDrink.getRating());

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
                            drink=drinkByBeerId;
                            break;
                        }
                    }

                    if (drink==null){
                        drink=mDrinksService.addDrink(new Drink(mBeerId,Constants.MY_USER_ID));
                    }

                    if(mDrink==null){
                        setDrink(drink);
                    }

                    if (getDrankBeersByDrinkId().containsKey(mDrink.getDrinkId())){
                        mDrink.setDrank(getDrankBeersByDrinkId().get(mDrink.getDrinkId()));
                    }

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


    @Override
    public void setUserId(int userId) {
        mUserId=userId;
    }

    @Override
    public int getUserId() {
        return mUserId;
    }


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
        return mDrankBeersByDrinkId;
    }

    @Override
    public void getActionOnAddingTag(String tag) {

        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Void>) emitter -> {
                    Tag newTag=new Tag(tag);
                newTag=mTagsService.addNewTag(newTag);
                    Beer beer=mMapper.mapBeerDTOToBeer(getmBeerToShow());
                  BeerTagDTO newBeerTag=new BeerTagDTO(beer.getBeerId(),newTag.getTagId());
                     mBeerTagsService.createBeerTag(newBeerTag);
                     mBeerToShow.getTags().add(newTag);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doOnError(error -> mView.showError(error))
                .doOnComplete(() -> {
                    mView.showMessage(Constants.ADD_OF_TAG_SUCCESS_MESSAGE);
                    mView.hideAddTagDialog();
                    mView.showBeer(mBeerToShow);
                })
                .doFinally(mView::hideLoading)
                        .subscribe();
    }

    public void setIsDrankBeersByDrinkId(Map<Integer, Boolean> isDrankBeersByDrinkId) {
        this.mDrankBeersByDrinkId = isDrankBeersByDrinkId;
    }
    @Override
    public void getActionOnCancelledAddTag() {
        mView.hideAddTagDialog();
    }

    @Override
    public void addTagButtonIsClicked() {
        mView.showDialogForAddingTag();
    }
}
