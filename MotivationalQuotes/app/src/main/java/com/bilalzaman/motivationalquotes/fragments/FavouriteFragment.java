package com.bilalzaman.motivationalquotes.fragments;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.FavDatabase;
import com.bilalzaman.motivationalquotes.models.FavouriteModel;
import com.bilalzaman.motivationalquotes.views.adapters.FavouriteAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    private TextView txtTitle;
    private FavouriteAdapter adapter;
    private Context context;
    private ArrayList<FavouriteModel> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavDatabase favDatabase;
    private List<FavouriteModel> dataDB = new ArrayList<>();

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        context = container.getContext();
        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText("Favourite");
        txtTitle.setVisibility(View.VISIBLE);
        favDatabase = Room.databaseBuilder(context, FavDatabase.class, "favdatabase").allowMainThreadQueries().build();
        recyclerView = view.findViewById(R.id.recyclerView);

        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        dataDB = favDatabase.favCatDao().showAllUsers();
        adapter = new FavouriteAdapter(context, dataDB);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

}
