package com.example.boranews.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boranews.model.NewsResponse;
import com.example.boranews.api.NewsRepository;


public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void initIndonesiaNews(){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getIndonesiaNews("id", "76b1bcba29574f27a995701fe0de3a14");

    }

    public void initInternationalNews(String country){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getInternationalNews(country, "76b1bcba29574f27a995701fe0de3a14");

    }

    public void initSearchNews(String katakunci){
//        if (mutableLiveData != null){
//            return;
//        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getSearchNews(katakunci,  "6d0bf911bc5748d691955ba138ba06fa");

    }

    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }

}
