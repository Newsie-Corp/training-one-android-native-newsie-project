package com.akv.newsiebuild.model.database.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.database.user.UserDataDB;

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