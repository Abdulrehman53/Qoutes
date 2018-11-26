package com.bilalzaman.motivationalquotes.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.models.FavouriteModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BilalZaman on 23/11/2018.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private Context context;
    private List<FavouriteModel> data = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    public FavouriteAdapter(Context context, List<FavouriteModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_explore, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FavouriteModel model = data.get(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView catTitle;
        private ImageView catImage, nonFav, fav;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.imgExplore);
            catTitle = itemView.findViewById(R.id.txtTitleQuote);
            nonFav = itemView.findViewById(R.id.imgFav);
            fav = itemView.findViewById(R.id.imgFavRED);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}


