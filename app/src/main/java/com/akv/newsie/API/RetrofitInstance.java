package com.akv.newsie.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private APIEndPoint API;

    public RetrofitInstance() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIEndPoint.NEWSAPI_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API = retrofit.create(APIEndPoint.class);
    }

    public APIEndPoint getAPI() {
        return API;
    }

    public void setAPI(APIEndPoint API) {
        this.API = API;
    }
}
