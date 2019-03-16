package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;
import com.bilalzaman.motivationalquotes.views.adapters.HomeAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<QuotesListModel> data = new ArrayList<>();
    private HomeAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;
    private DatabaseAccess databaseAccess;
    private EditText edtSearch;
    private QuotesListModel model = new QuotesListModel();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = container.getContext();
        recyclerView = view.findViewById(R.id.recyclerView);

        edtSearch = view.findViewById(R.id.edtSearch);
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

        setRecyclerView();

        return view;
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


    private void setRecyclerView() {
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        data = databaseAccess.getData();
        adapter = new HomeAdapter(context, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        databaseAccess.close();

    }

}
