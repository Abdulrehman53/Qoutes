package com.bilalzaman.motivationalquotes.views.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 19/10/2018.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<QuotesListModel> data;

    public HomeListAdapter(Context context, ArrayList<QuotesListModel> data) {
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
        QuotesListModel model = data.get(position);
        holder.txtQuote.setText(model.getQuote());

        holder.imgCopy.setOnClickListener(v -> {

            if (context != null) {
                String text = holder.txtQuote.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context,  "Quote is copied to clipboard", Toast.LENGTH_SHORT).show();
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

//        holder.btnBack.setOnClickListener(v ->{
//            ((Activity)context).finish();
//        });

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuote;
        private ImageView imgCopy, imgShare;

        public ViewHolder(View itemView) {
            super(itemView);
            txtQuote = itemView.findViewById(R.id.txtQuote);
            imgShare = itemView.findViewById(R.id.imgShare);
            imgCopy = itemView.findViewById(R.id.imgCopy);

        }
    }
}
