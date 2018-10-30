package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.adapters.HomeAdapter;
import com.bilalzaman.motivationalquotes.models.HomeModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<HomeModel> data = new ArrayList<>();
    private HomeAdapter adapter;
    private LayoutInflater inflater;
    private Context context;
    private RecyclerView recyclerView;
    private View backgroundView,backgroundViewGrey,backgroundViewBlack;
    private TextView txtTitle;

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

//        backgroundView = view.findViewById(R.id.viewBackground);
//        backgroundViewBlack = view.findViewById(R.id.viewBackground_black);
//        backgroundViewGrey = view.findViewById(R.id.viewBackground_grey);
        setRecyclerView();
        data();
        return view;
    }

    private void setRecyclerView(){
        adapter = new HomeAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void data(){
        HomeModel model = new HomeModel("Life is never easy for those who dreams.Life is never easy for those who dreams.Life is never easy for those who dreams", "Bilal Zaman");
        data.add(model);

        model = new HomeModel("Life is never easy for those", "Abdul");
        data.add(model);

        model = new HomeModel("Life is never easy who dreams","Bilal Zaman");
        data.add(model);
    }
}