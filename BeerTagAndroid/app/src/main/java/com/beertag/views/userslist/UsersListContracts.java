package com.beertag.views.userslist;

import com.beertag.models.User;

import java.util.List;

public interface UsersListContracts {
    interface View {

        void setPresenter(UsersListContracts.Presenter presenter);

        void showUserDetails(User user);

        void showMyProfile(User user);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showAllUsers(List<User> allUsers);

       // void showDialogForDeletion(User userToDelete);

        //void hideDeletionDialog();

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(UsersListContracts.View view);

        void unsubscribe();

        void userIsSelected(User user);

        void showUsersList();

        void presentUsersToView(List<User> allUsers, String message);

        void showMyProfile();

        //void filterUsersWith(String searchQuery);

        //void userForDeletionIsSelected(User userToDelete);

        //void getActionOnCancelledDeletion();

       // void getActionOnConfirmedDeletion(User user);
    }

    interface Navigator {
        void navigateToProfileWith(User user);
        void navigateToProfileWithMyProfile(User user);
    }
}
