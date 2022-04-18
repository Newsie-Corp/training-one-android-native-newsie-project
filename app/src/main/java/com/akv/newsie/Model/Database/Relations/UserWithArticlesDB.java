package com.akv.newsie.Model.Database.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.User.UserDataDB;

import java.util.ArrayList;

public class UserWithArticlesDB {
    @Embedded
    public UserDataDB userData;
    @Relation(
            parentColumn = "userId",
            entityColumn = "articleId",
            associateBy = @Junction(UserArticlesCrossRefDB.class)
    )
    public ArrayList<ArticlesItemDB> articlesItemsList;
}