package com.akv.newsie.API;

import com.akv.newsie.Model.JSON.Articles.ArticlesResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIEndPoint {
    public static final String API_BASE_URL = "https://newsapi.org";
    public static final String API_KEY = "84be338cee6b43179b387c234571a2bf";

    @GET("/v2/everything")
    Call<ArticlesResponseJSON> getResponseByKeyword(@Query("q") String keyword, @Query("apiKey") String API_KEY);

    @GET("/v2/top-headlines")
    Call<ArticlesResponseJSON> getTopHeadlinesResponseByKeyword(@Query("q") String keyword, @Query("apiKey") String API_KEY);

}
