package com.bilalzaman.motivationalquotes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;

public class LoginActivity extends AppCompatActivity {

    private TextView txtTitle;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Login");
        txtTitle.setVisibility(View.VISIBLE);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setVisibility(View.VISIBLE);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
