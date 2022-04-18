package com.akv.newsie.Model.Database.Relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "articleId"})
public class UserArticlesCrossRefDB {
    public long userId;
    public long articleId;

}
