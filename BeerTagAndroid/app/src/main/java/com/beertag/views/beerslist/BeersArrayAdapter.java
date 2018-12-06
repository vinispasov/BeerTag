package com.beertag.views.beerslist;

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
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeersArrayAdapter extends ArrayAdapter<Beer> {
    private static final String BEER_RATING_FIELD = "Rating";
    private static final String BEER_ABV_FIELD = "ABV";
    private static final String BEER_STYLE_FIELD = "Style";
    private static final String BEER_COUNTRY_FIELD = "Country";

    @BindView(R.id.iv_beer_item_image)
    ImageView mBeerImageView;

    @BindView(R.id.tv_beer_item_name)
    TextView mBeerNameTextView;

    @BindView(R.id.tv_beer_rating_item_field)
    TextView mBeerRatingFieldTextView;

    @BindView(R.id.tv_beer_rating_item)
    TextView mBeerRatingTextView;

    @BindView(R.id.tv_beer_abv_item_field)
    TextView mBeerAbvFieldTextView;

    @BindView(R.id.tv_beer_abv_item)
    TextView mBeerAbvTextView;

    @BindView(R.id.tv_beer_style_item_field)
    TextView mBeerStyleFieldTextView;

    @BindView(R.id.tv_beer_style_item)
    TextView mBeerStyleTextView;

    @BindView(R.id.tv_beer_country_item_field)
    TextView mBeerCountryFieldTextView;

    @BindView(R.id.tv_beer_country_item)
    TextView mBeerCountryTextView;

    @Inject
    public BeersArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (Objects.equals(view, null)) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.beer_item_arrayadapter_layout, parent, false);
        }

        ButterKnife.bind(this, view);
        Beer beer = getItem(position);

        Picasso.get()
                .load(beer.getBeerPicture())
                .resize(150, 150)
                .into(mBeerImageView);

        mBeerNameTextView
                .setText(beer.getBeerName());
       mBeerAbvFieldTextView
                .setText(BEER_ABV_FIELD);
        mBeerAbvTextView
                .setText(beer.getAbv());
        mBeerRatingFieldTextView
                .setText(BEER_RATING_FIELD);
       /* mBeerRatingTextView
                .setText(beer.getRating());*/
        mBeerStyleFieldTextView
                .setText(BEER_STYLE_FIELD);
        mBeerStyleTextView
                .setText(beer.getBeerStyle());
        mBeerCountryFieldTextView
                .setText(BEER_COUNTRY_FIELD);
        mBeerCountryTextView
                .setText(beer.getOriginCountry());

        return view;
    }
}
