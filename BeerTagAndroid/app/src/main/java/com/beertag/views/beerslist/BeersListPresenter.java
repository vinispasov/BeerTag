package com.beertag.views.beerslist;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Drink;
import com.beertag.models.Tag;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.BeersService;
import com.beertag.services.base.DrinksService;
import com.beertag.utils.Constants;
import com.beertag.models.Beer;
import com.beertag.utils.mappers.BeersMapperImpl;
import com.beertag.utils.mappers.base.BeersMapper;

import org.w3c.dom.ls.LSException;

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
                    List<BeerTag>beersFilteredByTags= mBeerTagsService.getAllBeersTagsByTag(searchQuery);

                    beersFilteredByTags
                            .stream()
                            .map(beerTag->beerTag.getBeer())
                            .forEach(beersResultSet::add);


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
        Map<Integer,Double> averageRatingsByBeerId=loadBeerRating(allBeers);
        Map<Integer,List<Tag>> allTagsByBeerIds=getTagsByBeerId(allBeers);
        BeersMapper mapper=new BeersMapperImpl();
        this.setMapper(mapper);

        List<BeerDTO>beersToShow=new ArrayList<>();

        for (Beer beer:allBeers) {
            int beerId=beer.getBeerId();

           if (averageRatingsByBeerId.containsKey(beerId) && allTagsByBeerIds.containsKey(beerId)){
              BeerDTO beerToShow= mapper.mapBeerToDTO(beer,
                      averageRatingsByBeerId.get(beerId),
                      allTagsByBeerIds.get(beerId));
               beersToShow.add(beerToShow);
               continue;
           }
           BeerDTO beerToShow=mapper.mapBeerToDTO(beer,0,allTagsByBeerIds.get(beerId));
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
    public void filterBeersWithOption(String selectedOption) {
        if (selectedOption.equals(mCurrentSelectedOption)) {
            return;
        }
        filterBeers(selectedOption);
        mCurrentSelectedOption = selectedOption;
    }

    @Override
    public Map<Integer,Double> loadBeerRating(List<Beer>allBeers) throws IOException {


                    Map<Integer,Double> averageRatingsByBeerId=new HashMap<>();

                    for (Beer beer:allBeers) {
                        List<Drink> drinksByBeerId = mDrinksService.getAllDrinksByBeerId(beer.getBeerId());
                        double sum = 0;
                        int len = 0;
                        for (Drink drink : drinksByBeerId) {
                            if (drink.getRating() != 0) {
                                sum += drink.getRating();
                                ++len;
                            }
                        }

                        double avgRatingByBeerId = sum / len;
                        averageRatingsByBeerId.put(beer.getBeerId(), avgRatingByBeerId);

                    }

                    //this.setAverageRatingsByBeerId(averageRatingsByBeerId);

                    return averageRatingsByBeerId;

    }


    @Override
    public void filterBeers(String selectedOption) {
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
                    List<BeerDTO> sortedBeerDtos=getSortedBeerDtos(beers);

                    emitter.onNext(sortedBeerDtos);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(beersResult -> mView.showSortedBeers(beersResult),
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

    @Override
    public Map<Integer, List<Tag>> getTagsByBeerId(List<Beer> allBeers) throws IOException {

        Map<Integer,List<Tag>> allTagsByBeerIds=new HashMap<>();
        for (Beer beer:allBeers
             ) {
            List<BeerTag> allBeerTagsByBeerId=mBeerTagsService.getAllBeersTagsByBeer(beer.getBeerId());
            List<Tag>tagsByBeerId=new ArrayList<>();
            for (BeerTag beerTag:allBeerTagsByBeerId
                 ) {
               tagsByBeerId.add(beerTag.getTag());
            }
            allTagsByBeerIds.put(beer.getBeerId(),tagsByBeerId);
        }

        return allTagsByBeerIds;

    }

   /* public Map<Integer, Double> getAverageRatingsByBeerId() {
        return mAverageRatingsByBeerId;
    }

    public void setAverageRatingsByBeerId(Map<Integer, Double> averageRatingsByBeerId) {
        this.mAverageRatingsByBeerId = averageRatingsByBeerId;
    }*/

   public List<BeerDTO>getSortedBeerDtos(List<Beer>beers){
       List<BeerDTO> sortedBeerDtos=new ArrayList<>();
       for (Beer beer:beers
            ) {
          BeerDTO beerDTO= getMapper().getBeerDtosByBeerId().get(beer.getBeerId());
          sortedBeerDtos.add(beerDTO);
       }
       return sortedBeerDtos;
   }
}
