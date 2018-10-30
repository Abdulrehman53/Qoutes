package com.bilalzaman.motivationalquotes.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.dialogs.CustomAlertDialog;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

public class SplashActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int SPLASH_TIME_OUT = 2000;
    private static final int REQUEST_WRITE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadScreen("Motivational Quotes needs access to following " +
                "Media & Storage" +
                "Allowing Motivation Quotes to access your gallery and files permission you to store images " +
                "");
    }

    private void loadScreen(String message) {
        CustomAlertDialog mediaPermissionDialog = new CustomAlertDialog();
        CustomAlertDialog termsDialog = new CustomAlertDialog();
        termsDialog.setTitle("Permission Required");
        termsDialog.setPositiveButtonText("Give Permission");
        termsDialog.setNegativeButtonText("Dismiss");
        termsDialog.setMessage(message);
        termsDialog.setPositiveButtonListner(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        permissionProceed();
                    }
                }, 100);
            }
        });
        termsDialog.setNegativeButtonListner(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        termsDialog.show(getSupportFragmentManager(), "Media Permission");
    }

    public void permissionProceed(){
        if (ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            UIHelper.openActivity(this, HomeActivity.class);

        } else {
            requestStorePermission();
        }
    }

    private void requestStorePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission Denied")
                    .setMessage("Media & Storage" +
                            "Allowing Motivation Quotes to access your gallery and files permission you to store images")
                    .setPositiveButton("Give Permission", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(SplashActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE} , REQUEST_WRITE_PERMISSION);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE} , REQUEST_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Gransted", Toast.LENGTH_SHORT).show();
                UIHelper.openActivity(this, HomeActivity.class);
            } else {
                Toast.makeText(this, "Permission Deied", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
