package com.akv.newsie.Model.Database.Articles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;

@Entity
public class ArticlesResponseDB {
    private static final String TAG = "ArticlesResponseDB";

    @PrimaryKey(autoGenerate = true)
    private Integer articleResponseId;

    private int totalResults;

    private String status;

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Integer getArticleResponseId() {
        return articleResponseId;
    }

    public void setArticleResponseId(Integer articleResponseId) {
        this.articleResponseId = articleResponseId;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesResponseDB.class);
    }
}