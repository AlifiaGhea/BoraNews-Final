package com.example.boranews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.boranews.adapter.NewsAdapter;
import com.example.boranews.model.NewsArticle;
import com.example.boranews.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    List<NewsArticle> newsArticles;
    NewsAdapter newsAdapter;
    RecyclerView rvSearch;
    NewsViewModel newsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);
        rvSearch = findViewById(R.id.recyclerViewSearch);
        articleArrayList.clear();
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsAdapter = new NewsAdapter(SearchActivity.this, articleArrayList);

        newsViewModel.initSearchNews("");
        newsViewModel.getNewsRepository().observe(this, reports -> {
            if (reports == null){
                return;
            }
            newsArticles = reports.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(newsAdapter);
        rvSearch.setItemAnimator(new DefaultItemAnimator());
        rvSearch.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        articleArrayList.clear();
        newsArticles.clear();
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);

        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    search("");
                } else {
                    search(newText);
                }
                return true;
            }
        });
        return true;
    }


    public void search(String charText) {
        articleArrayList.clear();
        if (charText.length() == 0) {
            articleArrayList.addAll(newsArticles);
        } else {
            newsViewModel.initSearchNews(charText);
            newsViewModel.getNewsRepository().observe(this, reports -> {
                if (reports == null){
                    return;
                }
                newsArticles = reports.getArticles();
                articleArrayList.addAll(newsArticles);
            });
        }
        newsAdapter.notifyDataSetChanged();
    }
}