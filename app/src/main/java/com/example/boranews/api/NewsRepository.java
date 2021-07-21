package com.example.boranews.api;

import androidx.lifecycle.MutableLiveData;

import com.example.boranews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.cteateService(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getIndonesiaNews(String country, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsIndonesiaList(country, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
    public MutableLiveData<NewsResponse> getSearchNews(String katakunci, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getSearchList(katakunci, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    public MutableLiveData<NewsResponse> getInternationalNews(String country, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsInternationalList(country, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
