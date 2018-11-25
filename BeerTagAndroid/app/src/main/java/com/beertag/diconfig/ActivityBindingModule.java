package com.beertag.diconfig;

import com.beertag.views.beerslist.BeersListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BeersListModule.class)
    abstract BeersListActivity BeersListActivity();

}
