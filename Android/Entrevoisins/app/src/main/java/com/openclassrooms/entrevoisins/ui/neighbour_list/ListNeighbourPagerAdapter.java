package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    List<NeighbourFragment> mFragmentList = new ArrayList<>();
    List<String> mFragmentTitles = new ArrayList<>();

    public ListNeighbourPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }


    public void AddFragment(NeighbourFragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitles.add(title);
    }
}




