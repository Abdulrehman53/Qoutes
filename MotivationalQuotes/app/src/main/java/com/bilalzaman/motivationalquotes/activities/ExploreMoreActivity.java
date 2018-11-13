package com.bilalzaman.motivationalquotes.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.models.HomeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExploreMoreActivity extends AppCompatActivity {

    private ArrayList<HomeModel> data = new ArrayList<>();
    private ArrayList<String> dataString = new ArrayList<>();
    private HomeAdapter adapter;
    private LayoutInflater inflater;
    private Context context;
    private RecyclerView recyclerView;
    private View backgroundView, backgroundViewGrey, backgroundViewBlack;
    private TextView txtTitle;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private HomeModel dataValue = new HomeModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_more);

        recyclerView = findViewById(R.id.recyclerView);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Quotes");
        txtTitle.setVisibility(View.VISIBLE);

        setRecyclerView();

    }

    private void setRecyclerView() {
        adapter = new HomeAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
