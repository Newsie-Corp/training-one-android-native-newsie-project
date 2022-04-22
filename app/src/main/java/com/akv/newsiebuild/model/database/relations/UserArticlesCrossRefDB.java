package com.akv.newsiebuild.model.database.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.GsonBuilder;

@Entity(primaryKeys = {"userId", "articleId"})
public class UserArticlesCrossRefDB {
    @NonNull
    private String userId;

    @NonNull
    private int articleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, UserArticlesCrossRefDB.class);
    }
}
