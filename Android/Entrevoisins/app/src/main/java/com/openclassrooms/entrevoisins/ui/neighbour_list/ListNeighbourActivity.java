package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    ArrayList<Neighbour> generalList;
    ArrayList<Neighbour> favoriteList;

    ListNeighbourFragment fragmentGeneral;
    ListNeighbourFragment fragmentFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());

        generalList = new ArrayList<>(DI.getNeighbourApiService().getNeighbours());
        favoriteList = new ArrayList<>();

        fragmentGeneral = ListNeighbourFragment.newInstance(generalList);
        fragmentFavorites = ListNeighbourFragment.newInstance(favoriteList);

        mPagerAdapter.AddFragment(fragmentGeneral,"my neighbours");
        mPagerAdapter.AddFragment(fragmentFavorites, "favorites");

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void manageFavoriteList (Neighbour neighbour) {

        int neighbourPosition = generalList.indexOf(neighbour);

        if (neighbour.getFavorite()){
            if (favoriteList.contains(neighbour)){
                // Do nothing
            } else {
                favoriteList.add(neighbour);
                generalList.set(neighbourPosition,neighbour);
                sortNeighbours(favoriteList);
            }
        } else {
            if (favoriteList.contains(neighbour)){
                favoriteList.remove(neighbour);
                generalList.set(neighbourPosition,neighbour);
                sortNeighbours(favoriteList);
            } else {
                // Do nothing
            }
        }
    }

    private void sortNeighbours(ArrayList list){
        Collections.sort(list, new Comparator<Neighbour>() {
            @Override
            public int compare(Neighbour neighbour01, Neighbour neighbour02) {
                return neighbour01.getId().compareTo(neighbour02.getId());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == NeighbourDetailActivity.RESULT_OK){
                Neighbour result = (Neighbour) data.getSerializableExtra("RESULT");
                manageFavoriteList(result);
                Log.e("DEBUG","RESULT");

                fragmentGeneral.setNeighbours(generalList);
                fragmentFavorites.setNeighbours(favoriteList);
                mPagerAdapter.notifyDataSetChanged();
            }
        }
    }
}
