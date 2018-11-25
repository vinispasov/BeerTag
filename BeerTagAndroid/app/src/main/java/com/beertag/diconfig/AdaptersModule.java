package com.beertag.diconfig;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.beertag.views.beerslist.BeersArrayAdapter;
import com.beertag.models.Beer;

import dagger.Module;
import dagger.Provides;

@Module
public class AdaptersModule {
    @Provides
    public ArrayAdapter<Beer> getBeersArrayAdapter(Context context) {
        return new BeersArrayAdapter(context);
    }
}
