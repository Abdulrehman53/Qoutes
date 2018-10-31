package com.bilalzaman.motivationalquotes.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.constants.Constants;
import com.bilalzaman.motivationalquotes.fragments.ExploreFragment;
import com.bilalzaman.motivationalquotes.fragments.FavouriteFragment;
import com.bilalzaman.motivationalquotes.fragments.HomeFragment;
import com.bilalzaman.motivationalquotes.fragments.SettingFragment;
import com.bilalzaman.motivationalquotes.helpers.BottomNavigationViewHelper;
import com.bilalzaman.motivationalquotes.helpers.PreferenceHelper;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_PERMISSION = 1;
    private static int mLastNavigationSelectedItem;
    private BottomNavigationView mBottomNavigationView;
    private AnimationDrawable animationDrawable;

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
        PreferenceHelper.getInstance().init(this);

        init();

    }

    private void bottomMenuCalling() {
        HomeFragment homeFragment = new HomeFragment();
        replaceFragment(homeFragment, R.id.navigation_home);

        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
    }

    private void init() {

        if (PreferenceHelper.getInstance().getString(Constants.ISVARIFIED, "").equalsIgnoreCase(Constants.ISVARIFIED)) {
            bottomMenuCalling();
        } else {
            permissionProceed();

        }

    }

    public void permissionProceed() {
        if (ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            bottomMenuCalling();

        } else {
            requestStorePermission();
        }
    }

    private void requestStorePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Denied")
                    .setMessage("Media & Storage" + "Allowing Motivation Quotes to access your gallery and files permission you to store images")
                    .setPositiveButton("Give Permission", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UIHelper.showLongToastInCenter(HomeActivity.this, "The Permission was denied");
                            finish();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                bottomMenuCalling();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
