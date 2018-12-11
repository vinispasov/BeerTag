package com.beertag.views.profile;

import com.beertag.models.User;

public interface ProfileContracts {

    interface View {
        void showUser(User user);

        void setPresenter(Presenter presenter);

        void showError(Throwable error);

        void showLoading();

        void hideLoading();

        void showMessage(String message);


    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();

        void loadUser();

        void setUserId(int id);

    }
}
