package com.beertag.diconfig;




import com.beertag.views.profile.ProfileContracts;
import com.beertag.views.profile.ProfileFragment;
import com.beertag.views.profile.ProfilePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ProfileModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ActivityScoped
    @Binds
    abstract ProfileContracts.Presenter presenter(ProfilePresenter presenter);
}
