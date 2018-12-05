package com.beertag.views.userslist;

import android.content.Intent;
import android.os.Bundle;

import com.beertag.R;
import com.beertag.models.User;
import com.beertag.views.BaseDrawerActivity;
import com.beertag.views.profile.ProfileActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class UsersListActivity extends BaseDrawerActivity implements UsersListContracts.Navigator {

    public static final int DRAWER_IDENTIFIER = 114;

    @Inject
    UsersListFragment mUsersListFragment;

    @Inject
    UsersListPresenter mUsersListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_users_list);

        ButterKnife.bind(this);

        mUsersListFragment.setNavigator(this);
        mUsersListFragment.setPresenter(mUsersListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_users_list_fragment, mUsersListFragment)
                .commit();
    }




    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToProfileWith(User user) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.PROFILE_EXTRA_KEY, user);
        startActivity(intent);
    }
}
