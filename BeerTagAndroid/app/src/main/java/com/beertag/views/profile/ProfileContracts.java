package com.beertag.views.profile;

import com.beertag.models.DTO.BeerDTO;
import com.beertag.models.DTO.UserDTO;
import com.beertag.models.User;
import com.beertag.services.base.UsersService;

import java.util.List;

public interface ProfileContracts {

    interface View {
        void showUser(UserDTO user);

        void setPresenter(Presenter presenter);

        void showError(Throwable error);

        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showBeers(List<BeerDTO> topThreeBeers);

        void showBeerDetails(BeerDTO beer);
    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        void loadUser();

        void beerIsSelected(BeerDTO beer);

        void setUserId(int id);
    }
    interface Navigator{
        void navigateToBeerDetailsWith(BeerDTO beer);
    }
}
