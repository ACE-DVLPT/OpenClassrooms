package fr.ace.tabbedactivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ace.tabbedactivity.ui.main.PlaceholderFragment;
import fr.ace.tabbedactivity.ui.main.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    private ViewPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sectionsPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),-1);

//        String position;
//        sectionsPagerAdapter.getItem();

        PlaceholderFragment fragmentP1 = PlaceholderFragment.newInstance(1);
        PlaceholderFragment fragmentP2 = PlaceholderFragment.newInstance(2);
        PlaceholderFragment fragmentP3 = PlaceholderFragment.newInstance(3);

        sectionsPagerAdapter.AddFragment(fragmentP1,getString(R.string.tab_text)+ " " + fragmentP1.getArguments());
        sectionsPagerAdapter.AddFragment(fragmentP2,getString(R.string.tab_text)+ " " + fragmentP2.getArguments());
        sectionsPagerAdapter.AddFragment(fragmentP3,getString(R.string.tab_text)+ " " + fragmentP3.getArguments());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}




