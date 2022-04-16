package com.akv.newsie.API;

import com.akv.newsie.Model.ArticlesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEndPoint {
    public static final String BASE_URL = "https://newsapi.org/v2/everything?q=Apple&from=2022-04-16&sortBy=popularity&apiKey=84be338cee6b43179b387c234571a2bf";

    @GET("/articles")
    Call<List<ArticlesItem>> getArticles();
}
