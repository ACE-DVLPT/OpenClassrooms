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

    NeighbourFragment mFragmentGeneral;
    NeighbourFragment mFragmentFavoris;

    List<Neighbour> listGeneral = new ArrayList<>();
    List<Neighbour> listFavoris = new ArrayList<>();

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void setupViewPager() {
        mFragmentGeneral = NeighbourFragment.newInstance(listGeneral);
        mFragmentFavoris = NeighbourFragment.newInstance(listFavoris);
        ListNeighbourPagerAdapter viewPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragment(mFragmentGeneral, "All neighbors");
        viewPagerAdapter.AddFragment(mFragmentFavoris, "Favorite neighbors");
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
