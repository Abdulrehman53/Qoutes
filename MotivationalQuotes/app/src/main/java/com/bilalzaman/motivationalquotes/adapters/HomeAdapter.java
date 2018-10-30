package com.bilalzaman.motivationalquotes.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.models.HomeModel;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 19/10/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<HomeModel> data;

    private ImageButton imgCopy;
    private ImageButton imgShare;

    public HomeAdapter(Context context, ArrayList<HomeModel> data) {
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
        HomeModel model = data.get(position);
        holder.txtQuote.setText(model.getCatTitle());
        holder.txtAuthor.setText(model.getCatAuthor());

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


        imgCopy.setOnClickListener(new View.OnClickListener() {
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

        imgShare.setOnClickListener(new View.OnClickListener() {
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

        private TextView txtQuote, txtAuthor;
        private View backgroundView, backgroundViewGrey, backgroundViewBlack;

        public ViewHolder(View itemView) {
            super(itemView);
            txtQuote = itemView.findViewById(R.id.txtQuote);
            backgroundView = itemView.findViewById(R.id.viewBackground);
            backgroundViewBlack = itemView.findViewById(R.id.viewBackground_black);
            backgroundViewGrey = itemView.findViewById(R.id.viewBackground_grey);
            imgCopy = itemView.findViewById(R.id.imgCopy);
            imgShare = itemView.findViewById(R.id.imgShare);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
        }
    }
}
