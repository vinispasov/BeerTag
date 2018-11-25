package com.beertag.diconfig;

import com.beertag.views.beerslist.BeersListContracts;
import com.beertag.views.beerslist.BeersListFragment;
import com.beertag.views.beerslist.BeersListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BeersListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract BeersListFragment BeersListFragment();

    @ActivityScoped
    @Binds
    abstract BeersListContracts.Presenter presenter(BeersListPresenter presenter);

}
