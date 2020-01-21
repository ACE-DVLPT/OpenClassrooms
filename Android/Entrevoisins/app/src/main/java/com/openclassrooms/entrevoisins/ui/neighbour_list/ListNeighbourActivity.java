package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    ArrayList<Neighbour> mGeneralList;
    ArrayList<Neighbour> mFavoriteList;

    ListNeighbourFragment mFragmentGeneral;
    ListNeighbourFragment mFragmentFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());

        mGeneralList = new ArrayList<>(DI.getNeighbourApiService().getNeighbours());
        mFavoriteList = new ArrayList<>();

        mFragmentGeneral = ListNeighbourFragment.newInstance(mGeneralList);
        mFragmentFavorites = ListNeighbourFragment.newInstance(mFavoriteList);

        mPagerAdapter.AddFragment(mFragmentGeneral,"my neighbours");
        mPagerAdapter.AddFragment(mFragmentFavorites, "favorites");

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * allows to check if the tested neighbor exists in the list of favorites. If it exists, nothing should happen. If it does not exist, the method should add it to the favorites list.
     * @param neighbour to check
     * @param generalList
     * @param favoriteList
     */
    public void manageFavoriteList (Neighbour neighbour, ArrayList<Neighbour> generalList, ArrayList<Neighbour> favoriteList) {

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

    private void sortNeighbours(ArrayList<Neighbour> list){
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
                manageFavoriteList(result,mGeneralList,mFavoriteList);
                Log.e("DEBUG","RESULT");

                mFragmentGeneral.setNeighbours(mGeneralList);
                mFragmentFavorites.setNeighbours(mFavoriteList);
                mPagerAdapter.notifyDataSetChanged();
            }
        }
    }
}
