package com.bilalzaman.motivationalquotes.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.models.AuthorModel;
import com.bilalzaman.motivationalquotes.views.activities.AuthorQuoteList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by BilalZaman on 03/12/2018.
 */
public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AuthorModel> data;
    private AuthorModel model;
    private AssetManager assetManager;

    public AuthorAdapter(Context context, ArrayList<AuthorModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_author, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        model = data.get(position);
        holder.nameAuthor.setText(model.getName());
        holder.txtId.setText("" + model.getImage());

        Bitmap bitmap = null;

        try {
            assetManager = context.getAssets();
            String[] imgPath = assetManager.list("authors");
            InputStream inputStream = assetManager.open("authors/" + String.valueOf(model.getImage())+".jpg");
//            InputStream inputStream = assetManager.open("authors/" + imgPath[position]);

            Log.d("PATH", "" + model.getImage());
            Log.d(" PATHIMAGE", "" + model.getImage());
            bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap bitmapResize = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
            holder.imgAuthor.setImageBitmap(bitmapResize);


        } catch (IOException e) {
            e.printStackTrace();
        }

        int authorid = model.getAuthorId();
        holder.viewClick.setOnClickListener(v -> {
            Intent intent = new Intent(context, AuthorQuoteList.class);
            intent.putExtra("authorId", String.valueOf(authorid));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterList(ArrayList<AuthorModel> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAuthor;
        private TextView nameAuthor, txtId;
        private View viewClick;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAuthor = itemView.findViewById(R.id.imgAuthor);
            nameAuthor = itemView.findViewById(R.id.txtAuthorName);
            viewClick = itemView.findViewById(R.id.viewClick);
            txtId = itemView.findViewById(R.id.txtId);
        }
    }
}