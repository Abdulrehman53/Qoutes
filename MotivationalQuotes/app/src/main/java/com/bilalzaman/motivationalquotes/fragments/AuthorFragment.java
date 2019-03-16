package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.AuthorModel;
import com.bilalzaman.motivationalquotes.views.adapters.AuthorAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends Fragment {

    private ArrayList<AuthorModel> data = new ArrayList<>();
    private AuthorAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;
    private AuthorModel authorModel;
    private DatabaseAccess databaseAccess;
    private EditText edtSearch;

    public AuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_author, container, false);
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

    private void setRecyclerView() {
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        data = databaseAccess.getAuthorName();

        adapter = new AuthorAdapter(context, data);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(adapter);

        databaseAccess.close();
    }

    private void filter(String text) {
        ArrayList<AuthorModel> filteredList = new ArrayList<>();

        for (AuthorModel item : data) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}
