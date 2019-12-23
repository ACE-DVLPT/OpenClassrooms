package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
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

    ArrayList<Neighbour> generalList = new ArrayList<>();
    ArrayList<Neighbour> favoriteList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());

        NeighbourFragment fragmentGeneral = NeighbourFragment.newInstance(generalList);
        NeighbourFragment fragmentFavorites = NeighbourFragment.newInstance(favoriteList);

        mPagerAdapter.AddFragment(fragmentGeneral,"my neighbours");
        mPagerAdapter.AddFragment(fragmentFavorites, "favorites");

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

//        setSupportActionBar(mToolbar);
//        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
