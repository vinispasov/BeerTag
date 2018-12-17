package com.beertag.diconfig;

import com.beertag.views.beerdetails.BeerDetailsActivity;
import com.beertag.views.beerslist.BeersListActivity;
import com.beertag.views.createbeer.BeerCreateActivity;
import com.beertag.views.profile.ProfileActivity;
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

    @ActivityScoped
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity profileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BeerCreateModule.class)
    abstract BeerCreateActivity beerCreateActivity();

}
