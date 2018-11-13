package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.models.HomeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

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


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText("Quotes");
        txtTitle.setVisibility(View.VISIBLE);

        setRecyclerView();

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("Quotes");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (data != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        String value = dataSnapshot1.getValue(String.class);
                        data.add(new HomeModel(value,""));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Failed to read", databaseError.toString());
            }
        });

        return view;
    }

    private void setRecyclerView() {
        adapter = new HomeAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}