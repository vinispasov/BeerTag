package com.beertag.views.beerslist;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.mappers.base.BeersMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BeersListContracts {
    interface View {

        void setPresenter(BeersListContracts.Presenter presenter);

        void showBeerDetails(BeerDTO beer,BeersMapper mapper);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllBeers(List<BeerDTO> allBeers);

        void showCompactBeersView(List<BeerDTO> beersResult);

        //void showDetailedBeersView(List<Beer> beersResult);

        void showDialogForDeletion(BeerDTO beerToDelete);

        void hideDeletionDialog();

        void showMessage(String message);



    }

    interface Presenter {

        void subscribe(BeersListContracts.View view);

        void unsubscribe();

        void beerIsSelected(BeerDTO beer);

        void showBeersList();

        void presentBeersToView(List<Beer> allBeers, String message) throws IOException;

        void filterBeersWith(String searchQuery);

        void beerForDeletionIsSelected(BeerDTO beerToDelete);

        void getActionOnCancelledDeletion();

        void getActionOnConfirmedDeletion(BeerDTO beerToDelete);

        void filterBeersWithOption(String preference, String selectedOption);

        Map<Integer,Double> loadBeerRating() throws IOException;

        void setBeerId(int id);

        BeersMapper getMapper();

        void setMapper(BeersMapper mapper);
    }

    interface Navigator {
        void navigateToBeerDetailsWith(BeerDTO beer,BeersMapper mapper);
    }
}
