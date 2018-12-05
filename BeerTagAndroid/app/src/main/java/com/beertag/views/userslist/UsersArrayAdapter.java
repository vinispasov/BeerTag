package com.beertag.views.userslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.User;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersArrayAdapter extends ArrayAdapter<User> {

    private static final String USER_USERNAME_FIELD = "Username";

    @BindView(R.id.iv_user_item_image)
    ImageView mUserImageView;

    @BindView(R.id.tv_user_username_item_field)
    TextView mUserUsernameFieldTextView;

    @BindView(R.id.tv_user_username_item)
    TextView mUserUsernameTextView;


    @Inject
    public UsersArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (Objects.equals(view, null)) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.user_item_arrayadapter_layout, parent, false);
        }

        ButterKnife.bind(this, view);
        User user = getItem(position);

        Picasso.get()
                .load(user.getUserPicture())
                .resize(150, 150)
                .into(mUserImageView);


        mUserUsernameFieldTextView
                .setText(USER_USERNAME_FIELD);
        mUserUsernameTextView
                .setText(user.getUserName());

        return view;
    }



}
