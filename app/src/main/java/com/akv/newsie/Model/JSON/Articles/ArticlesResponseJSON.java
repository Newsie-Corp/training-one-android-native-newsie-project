package com.akv.newsie.Model.JSON.Articles;

import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponseJSON {
    private static final String TAG = "ArticlesResponseJSON";

    @PrimaryKey(autoGenerate = true)
    private Integer articleResponseId;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesItemJSON> articles;

    @SerializedName("status")
    private String status;

    public ArticlesResponseJSON() {
    }

//    public ArticlesResponseJSON(Integer articleResponseId, int totalResults, List<ArticlesItemJSON> articles, String status) {
//        this.articleResponseId = articleResponseId;
//        this.totalResults = totalResults;
//        this.articles = articles;
//        this.status = status;
//    }

    public Integer getArticleResponseId() {
        return articleResponseId;
    }

    public void setArticleResponseId(Integer articleResponseId) {
        this.articleResponseId = articleResponseId;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setArticles(List<ArticlesItemJSON> articles) {
        this.articles = articles;
    }

    public List<ArticlesItemJSON> getArticles() {
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