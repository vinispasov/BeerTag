package com.beertag.views.beerslist;

import com.beertag.views.models.Beer;

import java.util.List;

public interface BeersListContracts {
    interface View {

        void setPresenter(BeersListContracts.Presenter presenter);

        void showBeerDetails(Beer beer);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllBeers(List<Beer> allBeers);

        void showDialogForDeletion(Beer beerToDelete);

        void hideDeletionDialog();

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(BeersListContracts.View view);

        void unsubscribe();

        void beerIsSelected(Beer beer);

        void showBeersList();

        void presentBeersToView(List<Beer> allBeers, String message);

        void filterBeersWith(String searchQuery);

        void beerForDeletionIsSelected(Beer beerToDelete);

        void getActionOnCancelledDeletion();

        void getActionOnConfirmedDeletion(Beer beerToDelete);
    }

    interface Navigator {
        void navigateToBeerDetailsWith(Beer beer);
    }
}
