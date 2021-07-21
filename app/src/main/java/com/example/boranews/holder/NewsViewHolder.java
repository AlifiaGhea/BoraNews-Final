package com.example.boranews.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boranews.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public TextView publishedAt;
    public TextView source;
    public CardView cvNews;
    public View view;

    public NewsViewHolder(View view) {
        super(view);

        cvNews = view.findViewById(R.id.cvNews);
        image = view.findViewById(R.id.imageList);
        title = view.findViewById(R.id.title);
        source = view.findViewById(R.id.source);
        publishedAt = view.findViewById(R.id.publishedAt);
        this.view = view;
    }

}
