package com.beertag.views.profile;

import android.os.UserManager;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.Beer;
import com.beertag.models.BeerTag;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.UserDTO;
import com.beertag.models.Drink;
import com.beertag.models.Tag;
import com.beertag.models.User;
import com.beertag.services.base.BeerTagsService;
import com.beertag.services.base.DrinksService;
import com.beertag.services.base.UsersService;
import com.beertag.utils.mappers.BeersMapperImpl;
import com.beertag.utils.mappers.UsersMapperImpl;
import com.beertag.utils.mappers.base.BeersMapper;
import com.beertag.utils.mappers.base.UsersMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ProfilePresenter implements ProfileContracts.Presenter {

    private final UsersService mUsersService;
    private final DrinksService mDrinksService;
    private final SchedulerProvider mSchedulerProvider;
    private ProfileContracts.View mView;
    private int mUserId;
    private UserDTO mSelectedUser;
    private BeerTagsService mBeerTagsService;

    @Inject
    public ProfilePresenter(UsersService usersService, DrinksService drinksService,BeerTagsService beerTagsService,SchedulerProvider schedulerProvider) {
        mDrinksService=drinksService;
        mUsersService = usersService;
        mBeerTagsService=beerTagsService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(ProfileContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadUser() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<UserDTO>) emitter -> {

                    User user= mUsersService.getUserById(mUserId);
                    List<Drink> topThreeDrinks=mDrinksService.getTopThreeRatedDrinksByUser(user.getUserId());
                    Map<Integer,ArrayList<Tag>>tagsByBeerId=new HashMap<>();

                    for (Drink drink:topThreeDrinks
                         ) {
                        List<BeerTag> beerTags=mBeerTagsService.getAllBeersTagsByBeer(drink.getBeerId());
                        ArrayList<Tag>tags=new ArrayList<>();
                        for (BeerTag beerTag:beerTags
                             ) {
                            tags.add(beerTag.getTag());
                        }
                        tagsByBeerId.put(drink.getBeerId(),tags);
                    }

                    List<BeerDTO> beerDtos=new ArrayList<>();
                    BeersMapper beersMapper=new BeersMapperImpl();

                    for (Drink drink:topThreeDrinks
                         ) {
                        beerDtos.add(beersMapper.mapBeerToDTO(drink.getBeer(),drink.getRating(),tagsByBeerId.get(drink.getBeerId())));
                    }

                    UsersMapper usersMapper=new UsersMapperImpl();
                    UserDTO userToShow=usersMapper.mapUserToDTO(user,beerDtos);

                    emitter.onNext(userToShow);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(userToShow -> {
                            mView.showUser(userToShow);
                            mSelectedUser = userToShow;
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void beerIsSelected(BeerDTO beer) {
        mView.showBeerDetails(beer);
    }
    @Override
    public void setUserId(int id) {
        mUserId=id;
    }


}
