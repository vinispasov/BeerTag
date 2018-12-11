package com.beertag.views.beerdetails;

import com.beertag.models.Beer;

public interface BeerDetailsContracts {

    interface View {
        void showBeer(Beer beer);

        void setPresenter(Presenter presenter);

        void showError(Throwable error);

        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showRatingDialog();

        void showBeerRating(double rating);

    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        void loadBeer();

        void setBeerId(int id);

        void rateButtonIsClicked();

        void ratingWasCancelled();

        void beerIsRated(int ratingValue);

        void loadBeerRating();


    }
}
