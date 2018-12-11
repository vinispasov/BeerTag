package com.beertag.views.profile;

import com.beertag.async.base.SchedulerProvider;
import com.beertag.models.User;
import com.beertag.services.base.UsersService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ProfilePresenter implements ProfileContracts.Presenter {

    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private ProfileContracts.View mView;
    private int mUserId;
    private User mSelectedUser;

    @Inject
    public ProfilePresenter(UsersService usersService,SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(ProfileContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadUser() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {

                    User user= mUsersService.getUserById(mUserId);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideLoading)
                .subscribe(user -> {
                            mView.showUser(user);
                            mSelectedUser = user;
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void setUserId(int id) {
        mUserId=id;
    }


}
