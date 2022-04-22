package com.akv.newsiebuild.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private NewsAPIEndPoint API;

    public RetrofitInstance(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = null;
        if (baseUrl == NewsAPIEndPoint.API_BASE_URL) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        } else if (baseUrl == UserAPIEndPoint.API_BASE_URL) {
            OkHttpClient client = new UserTokenInterceptor().getClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }

        API = retrofit.create(NewsAPIEndPoint.class);
    }

    public NewsAPIEndPoint getAPI() {
        return API;
    }

    public void setAPI(NewsAPIEndPoint API) {
        this.API = API;
    }
}
