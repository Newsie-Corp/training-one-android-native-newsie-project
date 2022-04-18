package com.akv.newsie.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private NewsAPIEndPoint API;

    public RetrofitInstance(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API = retrofit.create(NewsAPIEndPoint.class);
    }

    public NewsAPIEndPoint getAPI() {
        return API;
    }

    public void setAPI(NewsAPIEndPoint API) {
        this.API = API;
    }
}
