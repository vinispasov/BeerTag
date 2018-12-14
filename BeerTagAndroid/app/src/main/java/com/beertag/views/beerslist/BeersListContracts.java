package com.beertag.views.beerslist;

import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.Tag;
import com.beertag.utils.mappers.base.BeersMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BeersListContracts {
    interface View {

        void setPresenter(BeersListContracts.Presenter presenter);

        void showBeerDetails(BeerDTO beer,List<Integer> beerIds,List<BeerDTO> beerDtos);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllBeers(List<BeerDTO> allBeers);

        void showFilteredBeers(List<BeerDTO> beersResult);

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

        List<BeerDTO>generateBeerDtos(List<Beer> allBeers) throws IOException;

        void sortBeers(String selectedOption);

        void beerForDeletionIsSelected(BeerDTO beerToDelete);

        void getActionOnCancelledDeletion();

       // void presentSortedBeersToView(List<BeerDTO> sortedBeers);

        void getActionOnConfirmedDeletion(BeerDTO beerToDelete);

        void sortBeersWithOption(String selectedOption);

        Map<Integer,Double> loadBeerRating(List<Beer>allBeers) throws IOException;

        void setBeerId(int id);

        BeersMapper getMapper();

        void setMapper(BeersMapper mapper);

        Map<Integer,List<Tag>> getTagsByBeerId(List<Beer>allBeers) throws IOException;

        void filterBeersWith(String searchQuery);
    }

    interface Navigator {
        void navigateToBeerDetailsWith(BeerDTO beer,List<Integer> beerIds,List<BeerDTO> beerDtos);
    }
}
