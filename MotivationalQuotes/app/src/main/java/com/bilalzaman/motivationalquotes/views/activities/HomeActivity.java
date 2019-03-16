package com.bilalzaman.motivationalquotes.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.databinding.ActivityHomeBinding;
import com.bilalzaman.motivationalquotes.fragments.AuthorFragment;
import com.bilalzaman.motivationalquotes.fragments.ExploreFragment;
import com.bilalzaman.motivationalquotes.fragments.FavouriteFragment;
import com.bilalzaman.motivationalquotes.fragments.HomeFragment;
import com.bilalzaman.motivationalquotes.fragments.SettingFragment;
import com.bilalzaman.motivationalquotes.helpers.AdsManager;
import com.bilalzaman.motivationalquotes.helpers.PreferenceHelper;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends AppCompatActivity {

    private static int mLastNavigationSelectedItem;
    private BottomNavigationView mBottomNavigationView;
    private ActivityHomeBinding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
               // AdsManager.getInstance(this).showInterstitial();
                HomeFragment homeFragment = new HomeFragment();
                replaceFragment(homeFragment, R.id.navigation_home);
                return true;
            case R.id.navigation_author:
               // AdsManager.getInstance(this).showInterstitial();
                AuthorFragment authorFragment = new AuthorFragment();
                replaceFragment(authorFragment, R.id.navigation_author);
                return true;
            case R.id.navigation_explore:
                //AdsManager.getInstance(this).showInterstitial();
                ExploreFragment exploreFragment = new ExploreFragment();
                replaceFragment(exploreFragment, R.id.navigation_explore);
                return true;
            case R.id.navigation_favourites:
              //  AdsManager.getInstance(this).showInterstitial();
                FavouriteFragment favouriteFragment = new FavouriteFragment();
                replaceFragment(favouriteFragment, R.id.navigation_favourites);
                return true;
            case R.id.navigation_setting:
               // AdsManager.getInstance(this).showInterstitial();
                SettingFragment settingFragment = new SettingFragment();
                replaceFragment(settingFragment, R.id.navigation_setting);
                return true;
        }
        return false;
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        PreferenceHelper.getInstance().init(this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        AdsManager.getInstance(this).createAndShowBanner(binding.adView);
//
//        consentInformation = ConsentInformation.getInstance(this);
//        if (!SharedPreferenceHelper.getInstance().getBooleanValue(getString(R.string.is_consent_first_time_requested), false)) {
//            requestGoogleConsentForm();
//        }

        pushNotification();
        bottomMenuCalling();

    }

    private void bottomMenuCalling() {
//        AdsManager.getInstance(this).showInterstitial();
        HomeFragment homeFragment = new HomeFragment();
        replaceFragment(homeFragment, R.id.navigation_home);

        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        //BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
    }

    private void pushNotification() {
        FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void onBackPressed() {
        exitAlert();
    }


    private void exitAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You want to exit");
        alertDialogBuilder.setPositiveButton("yes",
                (arg0, arg1) -> {
                    moveTaskToBack(true);
                });

        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> bottomMenuCalling());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
