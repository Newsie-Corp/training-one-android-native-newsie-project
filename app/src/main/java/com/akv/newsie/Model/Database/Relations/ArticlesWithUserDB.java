package com.akv.newsie.Model.Database.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.User.UserDataDB;

import java.util.ArrayList;

public class ArticlesWithUserDB {
    @Embedded
    public ArticlesItemDB articlesItem;
    @Relation(
            parentColumn = "articleId",
            entityColumn = "userId",
            associateBy = @Junction(UserArticlesCrossRefDB.class)
    )
    public ArrayList<UserDataDB> userDatas;
}