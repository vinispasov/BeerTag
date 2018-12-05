package com.beertag.views.profile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beertag.R;
import com.beertag.views.BaseDrawerActivity;

public class ProfileActivity extends BaseDrawerActivity {

    public static final int DRAWER_IDENTIFIER = 111;
    public static final String PROFILE_EXTRA_KEY = "PROFILE_EXTRA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
