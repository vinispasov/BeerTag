package com.beertag.diconfig;

import com.beertag.views.beerdetails.BeerDetailsContracts;
import com.beertag.views.beerdetails.BeerDetailsFragment;
import com.beertag.views.beerdetails.BeerDetailsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class BeerDetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract BeerDetailsFragment beerDetailsFragment();

    @ActivityScoped
    @Binds
    abstract BeerDetailsContracts.Presenter presenter(BeerDetailsPresenter presenter);
}
