package com.example.mytabbedactivity;

import android.os.Bundle;

import com.example.mytabbedactivity.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mytabbedactivity.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager viewPager;

    PlaceholderFragment fragmentGeneral;
    PlaceholderFragment fragmentFavoris;

    List<PlaceholderFragment> listGeneral = new ArrayList<>();
    List<PlaceholderFragment> listFavoris = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    /**
     * Setup the view pager with the fragments and the title.
     */
    private void setupViewPager() {
        fragmentGeneral = PlaceholderFragment.newInstance(listGeneral);
        fragmentFavoris = PlaceholderFragment.newInstance(listFavoris);
        SectionsPagerAdapter viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentGeneral, "All neighbors");
        viewPagerAdapter.addFragment(fragmentFavoris, "Favorite neighbors");
        viewPager.setAdapter(viewPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}