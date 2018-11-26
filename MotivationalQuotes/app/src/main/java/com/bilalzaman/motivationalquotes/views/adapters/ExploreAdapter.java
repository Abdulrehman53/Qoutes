package com.bilalzaman.motivationalquotes.views.adapters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.FavDatabase;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.models.FavouriteModel;
import com.bilalzaman.motivationalquotes.views.activities.ExploreMoreActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ExploreModel> data = new ArrayList<>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    public ExploreAdapter(Context context, ArrayList<ExploreModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_explore, parent, false);
        context = parent.getContext();
        return new ExploreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ExploreModel model = data.get(position);
        // holder.catImage.setImageResource(model.getBackgroundImage());
        holder.catTitle.setText(model.getCatTitle());

        holder.nonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.savingFavCategories();
                FavouriteModel favouriteModel = new FavouriteModel();
                favouriteModel.setId(model.getId());
                favouriteModel.setBackgroundImage(model.getBackgroundImage());
                favouriteModel.setBackgroundImage(model.getCatTitle());
                holder.favDatabase.getInstance(context).favCatDao().insertAll(favouriteModel);

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
                mFirebaseInstance = FirebaseDatabase.getInstance();
                mFirebaseDatabase = mFirebaseInstance.getReference().child("list1");

                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (data != null) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                String value = dataSnapshot1.getValue(String.class);
                                data.add(new ExploreModel(model.getId(), value));
                                Intent intent = new Intent(context, ExploreMoreActivity.class);
                                intent.putExtra("name",model.getId());
                                context.startActivity(intent);
                                //adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("Failed to read", databaseError.toString());
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView catTitle;
        private ImageView catImage, nonFav, fav;
        private CardView cardView;
        private FavDatabase favDatabase;

        public ViewHolder(View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.imgExplore);
            catTitle = itemView.findViewById(R.id.txtTitleQuote);
            nonFav = itemView.findViewById(R.id.imgFav);
            fav = itemView.findViewById(R.id.imgFavRED);
            cardView = itemView.findViewById(R.id.cardView);
            savingFavCategories();
        }

        private void savingFavCategories(){
            favDatabase = Room.databaseBuilder(context, FavDatabase.class,"favdatabase").allowMainThreadQueries().build();
        }
    }
}

