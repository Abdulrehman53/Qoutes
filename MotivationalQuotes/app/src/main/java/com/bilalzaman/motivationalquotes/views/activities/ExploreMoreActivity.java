package com.bilalzaman.motivationalquotes.views.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;
import com.bilalzaman.motivationalquotes.views.adapters.ExploreListAdapter;
import com.bilalzaman.motivationalquotes.views.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.views.adapters.HomeListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExploreMoreActivity extends AppCompatActivity {

    private ArrayList<QuotesListModel> data = new ArrayList<>();
    private ExploreListAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;
    private DatabaseAccess databaseAccess;
    String Id = "";
    int catID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_more);
        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        Id = intent.getStringExtra("catId");
        catID = Integer.parseInt(Id);

        setRecyclerView();
    }

    private void setRecyclerView() {
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        data = databaseAccess.getCatDetail(catID);
        adapter = new ExploreListAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        databaseAccess.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
