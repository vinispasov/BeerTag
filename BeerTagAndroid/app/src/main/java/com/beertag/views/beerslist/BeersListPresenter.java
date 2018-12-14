package com.beertag.views.beerslist;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.beertag.utils.Constants.FILTER_OPTION_ALL;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ABV;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_ALPHABET;
import static com.beertag.utils.Constants.SORTED_OPTION_BY_RATING;
import static java.util.Arrays.sort;

public class BeersListPresenter implements BeersListContracts.Presenter {

    private BeersListContracts.View mView;
    private final BeersService mBeersService;
    private final BeerTagsService mBeerTagsService;
    private final DrinksService mDrinksService;

    private int mBeerId;
    private final SchedulerProvider mSchedulerProvider;
    private String mCurrentSelectedOption;
    private BeersMapper mMapper;
    private List<BeerDTO> mCurrentBeers;
    //private Map<Integer,Double>mAverageRatingsByBeerId;

    @Inject
    BeersListPresenter(BeersService beersService,BeerTagsService beerTagsService,DrinksService drinksService, SchedulerProvider schedulerProvider) {
        mBeersService = beersService;
        mBeerTagsService=beerTagsService;
        mDrinksService=drinksService;
        mSchedulerProvider = schedulerProvider;
        mCurrentBeers=new ArrayList<>();

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
                .create((ObservableOnSubscribe<List<BeerDTO>>) emitter -> {
                    List<Beer> beers = mBeersService.getAllBeers();
                    List<BeerDTO> beersToShow=generateBeerDtos(beers);
                    mCurrentBeers.clear();
                    mCurrentBeers.addAll(beersToShow);
                    emitter.onNext(beersToShow);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allBeers -> mView.showAllBeers(allBeers),
                        error -> mView.showError(error));
    }

   @Override
    public void filterBeersWith(String searchQuery) {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<BeerDTO>>) emitter -> {
                    List<Beer> beersResultSet=new ArrayList<>();
                    if(searchQuery.equals(" ")||searchQuery.isEmpty()||searchQuery==null){
                        beersResultSet=mBeersService.getAllBeers();
                    }
                    else {
                        List<Beer> beersByStyle = mBeersService.getBeersByStyle(searchQuery);
                        List<Beer> beersByCountry = mBeersService.getBeersByCountry(searchQuery);
                        List<BeerTag> beersFilteredByTags = mBeerTagsService.getAllBeersTagsByTag(searchQuery);

                        beersResultSet.addAll(beersByStyle);
                        beersResultSet.addAll(beersByCountry);

                        beersFilteredByTags
                                .stream()
                                .map(beerTag -> beerTag.getBeer())
                                .forEach(beersResultSet::add);
                    }
                    List<BeerDTO>beersToShow=generateBeerDtos(beersResultSet);

                    mCurrentBeers.clear();
                    mCurrentBeers.addAll(beersToShow);

                    emitter.onNext(beersToShow);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allBeers -> mView.showFilteredBeers(allBeers),
                        error -> mView.showError(error));
    }

    @Override
    public List<BeerDTO> generateBeerDtos(List<Beer> allBeers) throws IOException {
        Map<Integer,Double> averageRatingsByBeerId=loadBeerRating(allBeers);
        Map<Integer,List<Tag>> allTagsByBeerIds=getTagsByBeerId(allBeers);
        BeersMapper mapper=new BeersMapperImpl();
        this.setMapper(mapper);

        ArrayList<BeerDTO>beersToShow=new ArrayList<>();

        for (Beer beer:allBeers) {
            int beerId=beer.getBeerId();

           if (averageRatingsByBeerId.containsKey(beerId) && allTagsByBeerIds.containsKey(beerId)){
              BeerDTO beerToShow= mapper.mapBeerToDTO(beer,
                      averageRatingsByBeerId.get(beerId),
                      (ArrayList<Tag>) allTagsByBeerIds.get(beerId));
               beersToShow.add(beerToShow);
               continue;
           }
           BeerDTO beerToShow=mapper.mapBeerToDTO(beer,0, (ArrayList<Tag>) allTagsByBeerIds.get(beerId));
            beersToShow.add(beerToShow);
        }

      return beersToShow;
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
        mView.showBeerDetails(beer,mMapper.getBeerIds(),mMapper.getBeerDtos());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sortBeersWithOption(String selectedOption) {
        if (selectedOption.equals(mCurrentSelectedOption)) {
            return;
        }
        sortBeers(selectedOption);
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sortBeers(String selectedOption) {
        mView.showProgressBarLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<BeerDTO>>) (ObservableEmitter<List<BeerDTO>> emitter) -> {
                    List<Beer> beers = new ArrayList<>();
                    switch (selectedOption) {
                        case FILTER_OPTION_ALL:
                          beers.addAll(mMapper.getBeersFromBeerDTO(mCurrentBeers));
                            break;
                        case SORTED_OPTION_BY_RATING:

                            mCurrentBeers.sort(Comparator.comparing(BeerDTO::getRating));
                            beers.addAll(mMapper.getBeersFromBeerDTO(mCurrentBeers));
                            Collections.reverse(beers);
                            break;
                        case SORTED_OPTION_BY_ABV:
                            mCurrentBeers.sort(Comparator.comparing(BeerDTO::getAbvDouble));
                            beers.addAll(mMapper.getBeersFromBeerDTO(mCurrentBeers));
                            Collections.reverse(beers);
                            break;
                        case SORTED_OPTION_BY_ALPHABET:
                            mCurrentBeers.sort(Comparator.comparing(BeerDTO::getBeerName));
                            beers.addAll(mMapper.getBeersFromBeerDTO(mCurrentBeers));
                            break;
                    }
                    List<BeerDTO> sortedBeerDtos=getSortedBeerDtos(beers);

                    emitter.onNext(sortedBeerDtos);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(beersResult -> mView.showFilteredBeers(beersResult),
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
    public List<BeerDTO> getmCurrentBeers() {
        return mCurrentBeers;
    }

    public void setmCurrentBeers(List<BeerDTO> mCurrentBeers) {
        this.mCurrentBeers = mCurrentBeers;
    }
}
