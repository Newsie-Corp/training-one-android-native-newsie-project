package com.akv.newsiebuild.api;

import com.akv.newsiebuild.model.json.articles.ArticlesResponseJSON;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface UserAPIEndPoint {

    public static final String API_BASE_URL = "https://talentpool.oneindonesia.id";
    public static final String API_KEY = "454041184B0240FBA3AACD15A1F7A8BB";

    @GET("/api/user/login")
    Call<ArticlesResponseJSON> tryLogin(@Field("q") String keyword, @Field("apiKey") String API_KEY);

}
