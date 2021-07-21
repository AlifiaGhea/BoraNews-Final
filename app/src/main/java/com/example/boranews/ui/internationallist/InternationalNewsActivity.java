package com.example.boranews.ui.internationallist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.boranews.R;
import com.example.boranews.SearchActivity;
import com.example.boranews.adapter.NewsAdapter;
import com.example.boranews.model.NewsArticle;
import com.example.boranews.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class InternationalNewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView rvInternasionalNews;
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;
    ArrayList<NewsArticle> newsArticlesArrayList = new ArrayList<>();
    ImageView searchLogo;
    String kodeNegara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_news);

        kodeNegara = getIntent().getStringExtra("country");

        rvInternasionalNews = findViewById(R.id.recyclerViewInternasional);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.initInternationalNews(kodeNegara);
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            newsArticlesArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();

        searchLogo = findViewById(R.id.search2);
        searchLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InternationalNewsActivity.this, SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }

    private void setupRecyclerView(){
        if(newsAdapter == null)
        {
            newsAdapter = new NewsAdapter(InternationalNewsActivity.this, newsArticlesArrayList);
            rvInternasionalNews.setLayoutManager(new LinearLayoutManager(this));
            rvInternasionalNews.setAdapter(newsAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("UnitedStates".equals(parent.getItemAtPosition(position))) {
            kodeNegara = "us";
        }
        else if("Korea".equals(parent.getItemAtPosition(position))){
            kodeNegara = "kr";
        }
        else if("Jepang".equals(parent.getItemAtPosition(position))) {
            kodeNegara = "jp";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}