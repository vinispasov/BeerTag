package com.beertag;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import com.beertag.diconfig.DaggerAppComponent;

public class AndroidApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }
}
