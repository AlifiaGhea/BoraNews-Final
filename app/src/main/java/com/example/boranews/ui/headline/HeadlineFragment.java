package com.example.boranews.ui.headline;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boranews.R;
import com.example.boranews.SearchActivity;
import com.example.boranews.adapter.NewsAdapter;
import com.example.boranews.model.NewsArticle;
import com.example.boranews.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class HeadlineFragment extends Fragment implements NewsAdapter.onSelectData {

    RecyclerView rvHeadNews;
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;
    ArrayList<NewsArticle> newsArticleArrayListArticles = new ArrayList<>();
    ImageView searchLogo;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);

        rvHeadNews = view.findViewById(R.id.recyclerViewHeadline);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.initIndonesiaNews();
        newsViewModel.getNewsRepository().observe(getActivity(), newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            newsArticleArrayListArticles.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();

        searchLogo = view.findViewById(R.id.search);
        searchLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });

        return view;
    }

    private void setupRecyclerView(){
        if(newsAdapter == null)
        {
            newsAdapter = new NewsAdapter(getActivity(), newsArticleArrayListArticles);
            rvHeadNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvHeadNews.setAdapter(newsAdapter);
        }
    }

    @Override
    public void onSelected(NewsArticle mdlNews) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
