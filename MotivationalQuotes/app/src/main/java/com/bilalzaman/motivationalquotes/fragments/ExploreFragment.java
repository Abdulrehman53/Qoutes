package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.adapters.ExploreAdapter;
import com.bilalzaman.motivationalquotes.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
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
public class ExploreFragment extends Fragment {

    private ArrayList<ExploreModel> data = new ArrayList<>();
    private ExploreAdapter adapter;
    private LayoutInflater inflater;
    private Context context;
    private RecyclerView recyclerView;
    private TextView txtTitle;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText("Explore More");
        txtTitle.setVisibility(View.VISIBLE);

        setRecyclerView();

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("Categories");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (data != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        String value = dataSnapshot1.getValue(String.class);
                        data.add(new ExploreModel("",value));
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

    private void setRecyclerView(){
        adapter = new ExploreAdapter(context, data);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

    }
}
