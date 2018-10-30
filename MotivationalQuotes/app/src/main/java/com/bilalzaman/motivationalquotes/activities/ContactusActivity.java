package com.bilalzaman.motivationalquotes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

public class ContactusActivity extends AppCompatActivity {

    private TextView txtTitle, btnSubmit;
    private ImageButton imgBack;
    private EditText edtName, edtEmail, edtSubject, edtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        init();
    }

    private void init() {
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Contact Us");
        txtTitle.setVisibility(View.VISIBLE);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setVisibility(View.VISIBLE);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtSubject = findViewById(R.id.edtSubject);
        edtComment = findViewById(R.id.edtComment);
        btnSubmit = findViewById(R.id.btnSubmit);

        submitMail();

    }

    private void getMail() {

        String[] To = {"billalzaman8@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, To);
        emailIntent.putExtra(Intent.EXTRA_TEXT, edtName.getText().toString());
        //emailIntent.putExtra(Intent.EXTRA_EMAIL,edtEmail.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, edtSubject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, edtComment.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void submitMail() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMail();
                UIHelper.showLongToastInCenter(ContactusActivity.this, "Sending");
            }
        });

    }
}
