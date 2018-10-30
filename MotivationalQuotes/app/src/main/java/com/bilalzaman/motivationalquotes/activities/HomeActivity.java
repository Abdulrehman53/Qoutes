package com.bilalzaman.motivationalquotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.fragments.ExploreFragment;
import com.bilalzaman.motivationalquotes.fragments.FavouriteFragment;
import com.bilalzaman.motivationalquotes.fragments.HomeFragment;
import com.bilalzaman.motivationalquotes.fragments.SettingFragment;
import com.bilalzaman.motivationalquotes.helpers.BottomNavigationViewHelper;

public class HomeActivity extends AppCompatActivity {

    private static int mLastNavigationSelectedItem;
    private BottomNavigationView mBottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    replaceFragment(homeFragment, R.id.navigation_home);
                    return true;
                case R.id.navigation_explore:
                    ExploreFragment exploreFragment = new ExploreFragment();
                    replaceFragment(exploreFragment, R.id.navigation_explore);
                    return true;
                case R.id.navigation_favourites:
                    FavouriteFragment favouriteFragment = new FavouriteFragment();
                    replaceFragment(favouriteFragment, R.id.navigation_favourites);
                    return true;
                case R.id.navigation_setting:
                    SettingFragment settingFragment = new SettingFragment();
                    replaceFragment(settingFragment, R.id.navigation_setting);
                    return true;
            }
            return false;
        }
    };

    private void replaceFragment(Fragment fragment, int lastNavigationSelectedItem) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        mLastNavigationSelectedItem = lastNavigationSelectedItem;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homeFragment = new HomeFragment();
        replaceFragment(homeFragment, R.id.navigation_home);

        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
    }
}
