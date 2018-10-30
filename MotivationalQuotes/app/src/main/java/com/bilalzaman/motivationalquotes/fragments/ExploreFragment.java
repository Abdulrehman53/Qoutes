package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.adapters.ExploreAdapter;
import com.bilalzaman.motivationalquotes.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.models.ExploreModel;

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

        return view;
    }

    private void setRecyclerView(){
        adapter = new ExploreAdapter(context, data);
        //recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
        setData();
    }

    private void setData() {
        ExploreModel model = new ExploreModel(R.drawable.images,"Life Quotes");
        data.add(model);

        model = new ExploreModel(R.drawable.images,"Love Quotes");
        data.add(model);

        model = new ExploreModel(R.drawable.images,"Sad Quotes");
        data.add(model);

        model = new ExploreModel(R.drawable.images,"Birthday Quotes");
        data.add(model);

        model = new ExploreModel(R.drawable.images,"Science Quotes");
        data.add(model);
    }
}
