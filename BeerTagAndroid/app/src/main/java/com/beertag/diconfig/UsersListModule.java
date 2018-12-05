package com.beertag.diconfig;

import com.beertag.views.userslist.UsersListContracts;
import com.beertag.views.userslist.UsersListFragment;
import com.beertag.views.userslist.UsersListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UsersListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract UsersListFragment usersListFragment();

    @ActivityScoped
    @Binds
    abstract UsersListContracts.Presenter presenter(UsersListPresenter presenter);
}
