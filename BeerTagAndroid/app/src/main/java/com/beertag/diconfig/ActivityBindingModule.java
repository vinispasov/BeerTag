package com.beertag.diconfig;

import com.beertag.views.beerdetails.BeerDetailsActivity;
import com.beertag.views.beerslist.BeersListActivity;
import com.beertag.views.userslist.UsersListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BeersListModule.class)
    abstract BeersListActivity beersListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = UsersListModule.class)
    abstract UsersListActivity usersListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BeerDetailsModule.class)
    abstract BeerDetailsActivity beerDetailsActivity();

}
