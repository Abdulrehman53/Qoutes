package com.bilalzaman.motivationalquotes.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;
import com.bilalzaman.motivationalquotes.views.adapters.HomeAdapter;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 27/11/2018.
 */
public class AuthorQuoteList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<QuotesListModel> data = new ArrayList<>();
    private HomeAdapter adapter;
    private DatabaseAccess databaseAccess;
    String id = "";
    int authorid;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        if (id != null) {
            Intent intent = getIntent();
            id = intent.getStringExtra("authorId");
            authorid = Integer.parseInt(id);
        }

        edtSearch = findViewById(R.id.edtSearch);
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
    }

    private void setRecyclerView() {

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        data = databaseAccess.getAuthDetailList(authorid);
        adapter = new HomeAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        databaseAccess.close();
    }

    private void filter(String text) {
        ArrayList<QuotesListModel> filteredList = new ArrayList<>();

        for (QuotesListModel item : data) {
            if (item.getAuthorName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
