package com.beertag.views.beerdetails;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Drink;
import com.beertag.utils.mappers.base.BeersMapper;

import java.util.Map;

public interface BeerDetailsContracts {

    interface View {
        void showBeer(BeerDTO beer);

        void setPresenter(Presenter presenter);

        void showError(Throwable error);

        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showRatingDialog();

        void showDialogForAddingTag();

        void hideAddTagDialog();
    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        void loadBeer();

        void setBeerId(int id);

        void rateButtonIsClicked();

        void drinkButtonIsClicked();

        void ratingWasCancelled();

        void beerIsRated(double ratingValue);

        void setmBeerDtosByBeerId(Map<Integer, BeerDTO> beerDtosByBeerId);

        void setUserId(int userId);

        int getUserId();

        void setBeerToShow(BeerDTO beer);

        Drink getDrink();

        Map<Integer,Boolean>getDrankBeersByDrinkId();

        void getActionOnAddingTag(String tag);

        void getActionOnCancelledAddTag();

        void addTagButtonIsClicked();
    }
}
