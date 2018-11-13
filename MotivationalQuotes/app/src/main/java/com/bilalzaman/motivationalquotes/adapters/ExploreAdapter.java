package com.bilalzaman.motivationalquotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.activities.ExploreMoreActivity;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.models.HomeModel;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ExploreModel> data = new ArrayList<>();


    public ExploreAdapter(Context context, ArrayList<ExploreModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_explore,parent,false);
        context = parent.getContext();
        return new ExploreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ExploreModel model = data.get(position);
       // holder.catImage.setImageResource(model.getBackgroundImage());
        holder.catTitle.setText(model.getCatTitle());

        holder.nonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.nonFav.setVisibility(View.GONE);
                Toast.makeText(context, holder.catTitle.getText().toString() + "is added to favourite list", Toast.LENGTH_SHORT).show();
                holder.fav.setVisibility(View.VISIBLE);
            }
        });

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fav.setVisibility(View.GONE);
                Toast.makeText(context, holder.catTitle.getText().toString() + "is removed from favourite list", Toast.LENGTH_SHORT).show();
                holder.nonFav.setVisibility(View.VISIBLE);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ExploreMoreActivity.class);
                intent.putExtra("position", (Parcelable) data.get(position));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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

