package com.bilalzaman.motivationalquotes.views.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.database.DatabaseAccess;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;
import com.bilalzaman.motivationalquotes.views.activities.ExploreMoreActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BilalZaman on 14/12/2018.
 */
public class ExploreListAdapter extends RecyclerView.Adapter<ExploreListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<QuotesListModel> data;
    private DatabaseAccess databaseAccess;
    private QuotesListModel model = new QuotesListModel();
    private Activity activity = (Activity) context;
    private static  int  fCount = 0;

    public ExploreListAdapter(Context context, ArrayList<QuotesListModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        model = data.get(position);
        holder.txtQuote.setText(model.getQuote());
        holder.txtAuthor.setText("- " + model.getAuthorName());
        int id = model.getAuthorId();

        holder.imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();
                databaseAccess.updateCatList(id);
                Toast.makeText(context, "Quotes is saved as Favourite", Toast.LENGTH_SHORT).show();
                databaseAccess.close();
                holder.imgHeart.setVisibility(View.INVISIBLE);
                holder.imgFev.setVisibility(View.VISIBLE);
                if (id == 1) {
                    holder.imgHeart.setImageResource(R.drawable.navigation_fav_red);
                } else {
                    holder.imgHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
                notifyDataSetChanged();
            }
        });

        holder.imgFev.setOnClickListener(v -> {

        });

        if (model.getIsFavourite() == 1) {
            holder.imgHeart.setImageResource(R.drawable.navigation_fav_red);
        } else {
            holder.imgHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        holder.imgCopy.setOnClickListener(v -> {

            if (context != null) {
                String text = holder.txtQuote.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Quote is copied to clipboard", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Please Try Again", Toast.LENGTH_SHORT).show();
            }


        });

        holder.imgShare.setOnClickListener(v -> {
            String shareBody = holder.txtQuote.getText().toString();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.share_using)));
        });


        holder.imgBookmark.setOnClickListener(v -> {
            Toast.makeText(context, "Image saved in Download Folder", Toast.LENGTH_SHORT).show();
            holder.view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(holder.view.getDrawingCache());
            holder.view.setDrawingCacheEnabled(false);

            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "Download/BrilliantQuotes" + String.valueOf(fCount++)+ ".jpg";

            File imageFile = new File(mPath);

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(imageFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterList(ArrayList<QuotesListModel> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtQuote, txtAuthor;
        private ImageView imgCopy, imgShare, imgHeart, imgFev, imgBookmark;
        private ConstraintLayout view;

        public ViewHolder(View itemView) {
            super(itemView);

            txtQuote = itemView.findViewById(R.id.txtQuote);
            txtAuthor = itemView.findViewById(R.id.txtAuthorName);
            imgShare = itemView.findViewById(R.id.imgShare);
            imgCopy = itemView.findViewById(R.id.imgCopy);
            imgHeart = itemView.findViewById(R.id.imgHeart);
            imgBookmark = itemView.findViewById(R.id.imgBookmark);
            view = itemView.findViewById(R.id.inner_layout);
            imgFev = itemView.findViewById(R.id.imgFev);

        }
    }
}

