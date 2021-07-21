package com.example.boranews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.boranews.R;
import com.example.boranews.holder.NewsViewHolder;
import com.example.boranews.model.NewsArticle;
import com.example.boranews.DetailNewsActivity;
import com.example.boranews.utils.TimeUnits;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    Context context;
    ArrayList<NewsArticle> androidList;
    private static NewsAdapter.onSelectData onSelectData;

    public NewsAdapter(Context context, ArrayList<NewsArticle> androidList) {
        this.context = context;
        this.androidList = androidList;
    }

    public interface onSelectData {
        void onSelected(NewsArticle mdlNews);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_berita, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int position) {
        final NewsArticle berita = androidList.get(position);

        Glide.with(context)
                .load(berita.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(viewHolder.image);

        viewHolder.title.setText(berita.getTitle());
        viewHolder.source.setText(berita.getSource().getName());
        viewHolder.publishedAt.setText(TimeUnits.getTimeAgo(berita.getPublishedAt()));
        viewHolder.cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle =new Bundle();
                bundle.putString("judul",androidList.get(position).getTitle());
                bundle.putString("tanggal",androidList.get(position).getPublishedAt());
                bundle.putString("foto",androidList.get(position).getUrlToImage());
                bundle.putString("sumber",androidList.get(position).getSource().getName());
                bundle.putString("author",androidList.get(position).getAuthor());
                bundle.putString("deskripsi",androidList.get(position).getDescription());
                bundle.putString("url",androidList.get(position).getUrl());

                Intent intent = new Intent(v.getContext(), DetailNewsActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

               // onSelectData.onSelected(berita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

    public void setFilter(ArrayList<NewsArticle> filterList){
        androidList = new ArrayList<>();
        androidList.addAll(filterList);
        notifyDataSetChanged();
    }

}


