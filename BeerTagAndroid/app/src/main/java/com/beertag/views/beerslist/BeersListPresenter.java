package com.beertag.views.beerslist;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Drink;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;
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

import static com.beertag.utils.Constants.FILTER_OPTION_ALL;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ABV;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ALPHABET;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_RATING;

public class BeersListPresenter implements BeersListContracts.Presenter {

    private BeersListContracts.View mView;
    private final BeersService mBeersService;
    private final BeerTagsService mBeerTagsService;
    private final DrinksService mDrinksService;

    private int mBeerId;
    private final SchedulerProvider mSchedulerProvider;
    private String mCurrentSelectedOption;
    private BeersMapper mMapper;
    //private Map<Integer,Double>mAverageRatingsByBeerId;

    @Inject
    BeersListPresenter(BeersService beersService,BeerTagsService beerTagsService,DrinksService drinksService, SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mBeerTagsService=beerTagsService;
        mDrinksService=drinksService;
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
    public void presentBeersToView(List<Beer> allBeers, String message) throws IOException {
        Map<Integer,Double> averageRatingsByBeerId=loadBeerRating();
        BeersMapper mapper=new BeersMapperImpl();
        this.setMapper(mapper);

        List<BeerDTO>beersToShow=new ArrayList<>();

        for (Beer beer:allBeers) {
           if (averageRatingsByBeerId.containsKey(beer.getBeerId())){
              BeerDTO beerToShow= mapper.mapBeerToDTO(beer,averageRatingsByBeerId.get(beer.getBeerId()));
               beersToShow.add(beerToShow);
               continue;
           }
           BeerDTO beerToShow=mapper.mapBeerToDTO(beer,0);
            beersToShow.add(beerToShow);
        }

        if (beersToShow.isEmpty()) {
            mView.showMessage(message);
        } else {
            mView.showAllBeers(beersToShow);
        }
    }

    @Override
    public void beerForDeletionIsSelected(BeerDTO beerToDelete) {
        mView.showDialogForDeletion(beerToDelete);
    }

    @Override
    public void getActionOnConfirmedDeletion(BeerDTO beerToDelete) {

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
    public void beerIsSelected(BeerDTO beer) {
        mView.showBeerDetails(beer,getMapper());
    }

    @Override
    public void filterBeersWithOption(String preference, String selectedOption) {
        if (selectedOption.equals(mCurrentSelectedOption)) {
            return;
        }
        filterBeers(preference, selectedOption);
        mCurrentSelectedOption = selectedOption;
    }

    @Override
    public Map<Integer,Double> loadBeerRating() throws IOException {

                    List<Integer> allBeerIds=mDrinksService.getAllBeerIds();
                    Map<Integer,Double> averageRatingsByBeerId=new HashMap<>();

                    for (int beerId:allBeerIds) {
                        List<Drink> drinksByBeerId = mDrinksService.getAllDrinksByBeerId(beerId);
                        double sum = 0;
                        int len = 0;
                        for (Drink drink : drinksByBeerId) {
                            if (drink.getRating() != 0) {
                                sum += drink.getRating();
                                ++len;
                            }
                        }

                        double avgRatingByBeerId = sum / len;
                        averageRatingsByBeerId.put(beerId, avgRatingByBeerId);

                    }

                    //this.setAverageRatingsByBeerId(averageRatingsByBeerId);

                    return averageRatingsByBeerId;

    }

    private void presentBeersToViewAccordingToPreference(List<BeerDTO> beersResult, String preference) {

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
                .create((ObservableOnSubscribe<List<BeerDTO>>) emitter -> {
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

    @Override
    public void setBeerId(int id) {
        mBeerId=id;
    }

    @Override
    public BeersMapper getMapper() {
        return mMapper;
    }
    @Override
    public void setMapper(BeersMapper mapper) {
        this.mMapper = mapper;
    }

   /* public Map<Integer, Double> getAverageRatingsByBeerId() {
        return mAverageRatingsByBeerId;
    }

    public void setAverageRatingsByBeerId(Map<Integer, Double> averageRatingsByBeerId) {
        this.mAverageRatingsByBeerId = averageRatingsByBeerId;
    }*/
}
