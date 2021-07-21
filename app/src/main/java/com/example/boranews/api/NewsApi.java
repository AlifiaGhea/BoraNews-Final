package com.example.boranews.api;

import com.example.boranews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsResponse> getNewsIndonesiaList(@Query("country") String newsSource,
                                   @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<NewsResponse> getNewsInternationalList(@Query("country") String newsSource,
                                                @Query("apiKey") String apiKey);

    @GET("everything")
    Call<NewsResponse> getSearchList(@Query("qInTitle") String katakunci,
                                     @Query("apiKey") String apiKey);
}
