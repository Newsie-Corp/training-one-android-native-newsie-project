package com.akv.newsiebuild.model.database.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.database.user.UserDataDB;

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