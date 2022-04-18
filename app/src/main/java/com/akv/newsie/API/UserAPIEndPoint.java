package com.akv.newsie.API;

import com.akv.newsie.Model.JSON.Articles.ArticlesResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAPIEndPoint {

    public static final String API_BASE_URL = "https://talentpool.oneindonesia.id";
    public static final String API_KEY = "454041184B0240FBA3AACD15A1F7A8BB";

    @GET("/api/user/login")
    Call<ArticlesResponseJSON> tryLogin(@Query("q") String keyword, @Query("apiKey") String API_KEY);

}
