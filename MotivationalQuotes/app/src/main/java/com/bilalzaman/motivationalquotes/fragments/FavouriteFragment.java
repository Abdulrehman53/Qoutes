package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;
import com.bilalzaman.motivationalquotes.views.adapters.ExploreListAdapter;
import com.bilalzaman.motivationalquotes.views.adapters.HomeAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    private TextView txtTitle, txt2, authorList, exploreList;
    private Context context;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private DatabaseAccess databaseAccess;
    private EditText edtSearch;
    private ArrayList<QuotesListModel> data = new ArrayList<>();
    private ExploreListAdapter exploreAdapter;
    private ConstraintLayout filterLayout;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        context = container.getContext();
        txt2 = view.findViewById(R.id.textView2);
        recyclerView = view.findViewById(R.id.recyclerView);
        edtSearch = view.findViewById(R.id.edtSearch);
        filterLayout = view.findViewById(R.id.filterLayout);
        authorList = view.findViewById(R.id.txtByAuthor);
        exploreList = view.findViewById(R.id.txtByExplore);

        init();
        return view;
    }

    private void init() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        authorList.setOnClickListener(v -> {
            setAuthorRecyclerView();
            if (data.size() == 0) {
                txt2.setVisibility(View.VISIBLE);
                filterLayout.setVisibility(View.GONE);

            } else {
                filterLayout.setVisibility(View.GONE);
                txt2.setVisibility(View.GONE);
                setAuthorRecyclerView();
            }

        });

        exploreList.setOnClickListener(v -> {
            if (data != null ) {
                filterLayout.setVisibility(View.GONE);
                setExploreRecyclerView();
            } else {
                filterLayout.setVisibility(View.GONE);
                txt2.setVisibility(View.VISIBLE);
            }
        });
    }

    private void filter(String text) {
        ArrayList<QuotesListModel> filteredList = new ArrayList<>();

        for (QuotesListModel item : data) {
            if (item.getAuthorName().toLowerCase().contains(text.toLowerCase()) || item.getQuote().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    private void setAuthorRecyclerView() {
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        data = databaseAccess.getUpdatedData();
        adapter = new HomeAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        databaseAccess.close();
    }

    private void setExploreRecyclerView() {
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        data = databaseAccess.getExploreUpdateData();
        exploreAdapter = new ExploreListAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(exploreAdapter);
        databaseAccess.close();
    }
}
