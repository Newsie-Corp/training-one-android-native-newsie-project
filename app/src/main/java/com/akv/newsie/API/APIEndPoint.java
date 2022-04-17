package com.akv.newsie.API;

import com.akv.newsie.Model.ArticlesItem;
import com.akv.newsie.Model.APIResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndPoint {
    public static final String NEWSAPI_BASE_URL = "https://newsapi.org";
    public static final String NEWSAPI_API_KEY = "84be338cee6b43179b387c234571a2bf";

    @GET("/v2/everything")
    Call<APIResponse> getResponseByKeyword(@Query("q") String keyword, @Query("apiKey") String API_KEY);

    @GET("/v2/everything")
    Call<ArrayList<ArticlesItem>> getArticlesByKeyword(@Query("q") String keyword, @Query("apiKey") String API_KEY);
}
