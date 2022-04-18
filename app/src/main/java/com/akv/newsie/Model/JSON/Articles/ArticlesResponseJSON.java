package com.akv.newsie.Model.JSON.Articles;

import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticlesResponseJSON {
    private static final String TAG = "ArticlesResponseJSON";

    @PrimaryKey(autoGenerate = true)
    private Integer articleSourceId;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private ArrayList<ArticlesItemJSON> articles;

    @SerializedName("status")
    private String status;

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setArticles(ArrayList<ArticlesItemJSON> articles) {
        this.articles = articles;
    }

    public ArrayList<ArticlesItemJSON> getArticles() {
        return articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesResponseJSON.class);
    }
}