package com.beertag.views.userslist;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.User;
import com.beertag.services.base.UsersService;
import com.beertag.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class UsersListPresenter implements UsersListContracts.Presenter {
    private UsersListContracts.View mView;
    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    UsersListPresenter(UsersService usersService, SchedulerProvider schedulerProvider) {

        mUsersService =usersService;
        mSchedulerProvider = schedulerProvider;

    }

    @Override
    public void subscribe(UsersListContracts.View view) {
        mView=view;
    }

    @Override
    public void unsubscribe() {
        mView=null;
    }

    @Override
    public void userIsSelected(User user) {
        mView.showUserDetails(user);
    }

    @Override
    public void showUsersList() {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<User>>) emitter -> {
                    List<User> users = mUsersService.getUsers();
                    emitter.onNext(users);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(users -> presentUsersToView(users, Constants.NO_USERS_AVAILABLE_MESSAGE), mView::showError);
    }

    @Override
    public void presentUsersToView(List<User> allUsers, String message) {

        if (allUsers.isEmpty()) {
            mView.showMessage(message);
        } else {
            mView.showAllUsers(allUsers);
        }
    }

    @Override
    public void showMyProfile() {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user= mUsersService.getUserById(1);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(user ->mView.showMyProfile(user), mView::showError);
    }


}
