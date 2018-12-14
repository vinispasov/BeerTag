package com.beertag.views.beerdetails;

import android.content.Intent;

import android.os.Bundle;

import com.beertag.R;
import com.beertag.models.Beer;
import com.beertag.models.DTO.BeerDTO;
import com.beertag.utils.mappers.base.BeersMapper;
import com.beertag.views.BaseDrawerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BeerDetailsActivity extends BaseDrawerActivity {

    public static final String BEER_EXTRA_KEY = "BEER_EXTRA_KEY";
    public static final int DRAWER_IDENTIFIER = 120;
    public static final String BEERS_DTO_EXTRA_KEY = "BEERS_DTO";
    public static final String BEERS_IDS_EXTRA_KEY = "BEER_ID";

    @Inject
    BeerDetailsFragment mBeerDetailsFragment;

    @Inject
    BeerDetailsContracts.Presenter mBeerDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);


        ButterKnife.bind(this);
        Intent intent = getIntent();
        BeerDTO beer = (BeerDTO) intent.getSerializableExtra(BeerDetailsActivity.BEER_EXTRA_KEY);
        List<Integer>beerIds= intent.getExtras().getIntegerArrayList(BEERS_IDS_EXTRA_KEY);
        List<BeerDTO>beerDtos=intent.getExtras().getParcelableArrayList(BEERS_DTO_EXTRA_KEY);

        Map<Integer,BeerDTO> beerDtosByBeerId=createMapBeerDTOS(beerIds,beerDtos);

        mBeerDetailsFragment.setPresenter(mBeerDetailsPresenter);
        mBeerDetailsPresenter.setBeerId(beer.getBeerId());
        mBeerDetailsPresenter.setmBeerDtosByBeerId(beerDtosByBeerId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_beer_details, mBeerDetailsFragment)
                .commit();
    }

    private Map<Integer, BeerDTO> createMapBeerDTOS(List<Integer> beerIds, List<BeerDTO> beerDtos) {
        Map<Integer,BeerDTO> map =new HashMap<>();
        for (int i=0;i<beerIds.size();i++){
            map.put(beerIds.get(i),beerDtos.get(i));
        }
        return map;
    }


    //should not return valid identifier
    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }
}
