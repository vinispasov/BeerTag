package com.beertag.views.createbeer;

import com.beertag.models.Beer;
import com.beertag.models.Drink;

public interface BeerCreateContracts {
    interface View {

        void setPresenter(Presenter presenter);

        void navigateToBeersList();

        void showError(Throwable error);

        void hideLoading();

        void showLoading();

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void save(Beer beer,int userId,double rating);
    }

    interface Navigator {

        void navigateToBeersList();
    }
}
