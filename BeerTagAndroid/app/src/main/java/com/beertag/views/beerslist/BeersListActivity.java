package com.beertag.views.beerslist;

import android.os.Bundle;

import com.beertag.R;
import com.beertag.views.BaseDrawerActivity;

public class BeersListActivity extends BaseDrawerActivity {

    public static final int DRAWER_IDENTIFIER = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_list);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
