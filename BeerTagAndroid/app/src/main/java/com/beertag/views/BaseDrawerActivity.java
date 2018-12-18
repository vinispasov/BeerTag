package com.beertag.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.beertag.R;
import com.beertag.models.User;
import com.beertag.services.HttpUsersService;
import com.beertag.services.base.UsersService;
import com.beertag.utils.Constants;
import com.beertag.views.beerslist.BeersListActivity;
import com.beertag.views.createbeer.BeerCreateActivity;
import com.beertag.views.profile.ProfileActivity;
import com.beertag.views.userslist.UsersListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import java.io.IOException;

import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity{
    private static final String MY_PROFILE_DRAWER_NAME = "My profile";
    private static final String BEERS_LIST_DRAWER_NAME = "Beers";
    private static final String USERS_LIST_DRAWER_NAME = "Users";
    private static final String ADD_BEER_DRAWER_NAME = "Add new beer";

    @BindView(R.id.tb_drawer_toolbar)
    Toolbar mToolbar;

    private Drawer mDrawer;


    public BaseDrawerActivity() {
        //empty constructor required
    }


    public void setupDrawer() {


        PrimaryDrawerItem myProfileItem = new PrimaryDrawerItem()
                .withIdentifier(ProfileActivity.DRAWER_IDENTIFIER)
                .withName(MY_PROFILE_DRAWER_NAME);

        SecondaryDrawerItem beersListItem = new SecondaryDrawerItem()
                .withIdentifier(BeersListActivity.DRAWER_IDENTIFIER)
                .withName(BEERS_LIST_DRAWER_NAME);

        SecondaryDrawerItem usersListItem = new SecondaryDrawerItem()
                .withIdentifier(UsersListActivity.DRAWER_IDENTIFIER)
                .withName(USERS_LIST_DRAWER_NAME);

        SecondaryDrawerItem addBeerItem = new SecondaryDrawerItem()
                .withIdentifier(BeerCreateActivity.DRAWER_IDENTIFIER)
                .withName(ADD_BEER_DRAWER_NAME);


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withCloseOnClick(true)
                .addDrawerItems(
                       myProfileItem
                                .withIcon(R.drawable.myprofileicon),
                        new DividerDrawerItem(),
                        beersListItem
                                .withIcon(R.drawable.beericon),
                        new DividerDrawerItem(),
                        usersListItem
                                .withIcon(R.drawable.usersicon),
                        new DividerDrawerItem(),
                        addBeerItem
                                .withIcon(R.drawable.addicon),
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();

                    if (getIdentifier() == identifier) {
                        return false;
                    }

                    Intent intent = null;
                    try {
                        intent = getNextIntent(identifier);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (intent == null) {
                        return false;
                    }
                    startActivity(intent);
                    return true;
                })
                .build();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected abstract long getIdentifier();


    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawer.closeDrawer();
    }

    private Intent getNextIntent(long identifier) throws IOException {

        Intent nextIntent;

        if (identifier == ProfileActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, ProfileActivity.class);
            nextIntent.putExtra(ProfileActivity.PROFILE_EXTRA_KEY_MY_PROFILE, Constants.MY_USER_ID);
            return nextIntent;
        } else if (identifier == UsersListActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, UsersListActivity.class);
            return nextIntent;
        } else if (identifier == BeersListActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, BeersListActivity.class);
            return nextIntent;
        }else if (identifier == BeerCreateActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, BeerCreateActivity.class);
            return nextIntent;
        } else {
            return null;
        }
    }



}
