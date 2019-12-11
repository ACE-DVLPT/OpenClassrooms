public class MainActivity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    TabLayout tabLayout;

    MyFragment fragmentGeneral;
    MyFragment fragmentFavoris;

    /**
     * Setup the view pager with the fragments and the title.
     */
    private void setupViewPager() {
        fragmentGeneral = MyFragment.newInstance(listGeneral);
        fragmentFavoris = MyFragment.newInstance(listFavoris);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(fragmentGeneral, "All neighbors");
        viewPagerAdapter.addFragment(fragmentFavoris, "Favorite neighbors");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Neighbor neighbor = (User) data.getSerializableExtra(USER_INVITED_KEY);
        boolean favoris = data.getBooleanExtra("favoris", false);
        algo();
        fragmentFavoris.updateList(listFavoris);

    }

//Mettre à jour la liste de neighbors favorits
    private void algo() {
        //MAJ listFavoris
    }
}
