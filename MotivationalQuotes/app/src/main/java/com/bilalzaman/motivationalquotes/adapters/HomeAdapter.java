package com.bilalzaman.motivationalquotes.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.constants.Constants;
import com.bilalzaman.motivationalquotes.helpers.PreferenceHelper;
import com.bilalzaman.motivationalquotes.models.HomeModel;
import com.google.firebase.FirebaseApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by BilalZaman on 19/10/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<HomeModel> data;
    Activity activity = (Activity) context;
    ImageView imageView;
    Bitmap bitmap ;

    public HomeAdapter(Context context, ArrayList<HomeModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_quote, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HomeModel model = data.get(position);
        holder.txtQuote.setText(model.getCatTitle());
//        holder.txtAuthor.setText(model.getCatAuthor());

        holder.backgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.backgroundView.setVisibility(View.GONE);
                holder.backgroundViewBlack.setVisibility(View.VISIBLE);
            }
        });

        holder.backgroundViewBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.backgroundViewBlack.setVisibility(View.GONE);
                holder.backgroundViewGrey.setVisibility(View.VISIBLE);
            }
        });


        holder.backgroundViewGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.backgroundViewGrey.setVisibility(View.GONE);
                holder.backgroundView.setVisibility(View.VISIBLE);
            }
        });

        holder.txtLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int likeInt = 0;
                likeInt = likeInt + 1;
                holder.displayLike(likeInt);

            }
        });

        holder.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);


                } else {
                    //bitmap = imageView.getDrawingCache();
                    if (bitmap!=null){
                        bitmap = BitmapFactory.decodeResource(context.getResources(), R.id.viewBackground_grey);
                        File path = Environment.getExternalStorageDirectory();

                        File dir = new File(path + "/save/");
                        dir.mkdir();

                        File file = new File(dir, "bilal.png");

                        OutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {

                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("error",e.getMessage());
                        }
                    } else {

                    }

                }
            }
        });

        holder.txtCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context != null) {
                    String text = holder.txtQuote.getText().toString();
                    ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("'text", text);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context, "Text is copy", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Context is null", Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = holder.txtQuote.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.share_using)));
            }
        });


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuote, txtLikeIncrease;
        private View backgroundView, backgroundViewGrey, backgroundViewBlack;
        private TextView txtCopy, txtShare, txtLike, txtSave;

        public ViewHolder(View itemView) {
            super(itemView);
            txtQuote = itemView.findViewById(R.id.txtQuote);
            backgroundView = itemView.findViewById(R.id.viewBackground);
            backgroundViewBlack = itemView.findViewById(R.id.viewBackground_black);
            backgroundViewGrey = itemView.findViewById(R.id.viewBackground_grey);
            txtLike = itemView.findViewById(R.id.txtLike);
            txtSave = itemView.findViewById(R.id.txtSave);
            txtCopy = itemView.findViewById(R.id.txtCopy);
            txtShare = itemView.findViewById(R.id.txtShare);
            txtLikeIncrease = itemView.findViewById(R.id.txtLikeIncrease);

        }

        public void displayLike(int number){
            txtLikeIncrease.setText(""+number);
        }
    }
}
