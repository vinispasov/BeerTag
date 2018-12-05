package com.beertag.diconfig;

import android.app.Application;

import com.beertag.AndroidApplication;
import com.beertag.views.BaseDrawerActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        AsyncModule.class,
        HttpModule.class,
        ParsersModule.class,
        ServicesModule.class,
        RepositoriesModule.class,
        AdaptersModule.class,
        ValidatorsModule.class,
        BeersListModule.class,
        UsersListModule.class,
})
public interface AppComponent extends AndroidInjector<AndroidApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
