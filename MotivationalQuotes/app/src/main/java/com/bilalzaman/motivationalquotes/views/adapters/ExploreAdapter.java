package com.bilalzaman.motivationalquotes.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.views.activities.ExploreMoreActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private List<ExploreModel> data;
    private AssetManager assetManager;


    public ExploreAdapter(Context context, List<ExploreModel> data) {
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
        holder.catTitle.setText(model.getCatTitle());
        holder.txtid.setText("" + model.getId());

        Bitmap bitmap = null;

        try {
            assetManager = context.getAssets();
            String[] imgPath = assetManager.list("catimages");
            //InputStream inputStream = assetManager.open("authors/" + String.valueOf(model.getImage())+".jpg");
            InputStream inputStream = assetManager.open("catimages/" + String.valueOf(model.getId() + ".png"));

            Log.d("img", String.valueOf(model.getCatId() + ".png"));
            bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap bitmapResize = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
            holder.bgImage.setImageBitmap(bitmapResize);


        } catch (IOException e) {
            e.printStackTrace();
        }

        int catId = model.getCatId();
        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ExploreMoreActivity.class);
            intent.putExtra("catId", String.valueOf(catId));
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterList(List<ExploreModel> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView catTitle, txtid;
        private ImageView bgImage;
        private ConstraintLayout cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            catTitle = itemView.findViewById(R.id.txtTitleQuote);
            bgImage = itemView.findViewById(R.id.imgExplore);
            cardView = itemView.findViewById(R.id.cardView);
            txtid = itemView.findViewById(R.id.txtid);
        }
    }
}

