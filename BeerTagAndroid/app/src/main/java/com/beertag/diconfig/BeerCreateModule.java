package com.beertag.diconfig;

import com.beertag.views.beerdetails.BeerDetailsContracts;
import com.beertag.views.beerdetails.BeerDetailsFragment;
import com.beertag.views.beerdetails.BeerDetailsPresenter;
import com.beertag.views.createbeer.BeerCreateContracts;
import com.beertag.views.createbeer.BeerCreateFragment;
import com.beertag.views.createbeer.BeerCreatePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BeerCreateModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract BeerCreateFragment beerCreateFragment();

    @ActivityScoped
    @Binds
    abstract BeerCreateContracts.Presenter presenter(BeerCreatePresenter presenter);
}
