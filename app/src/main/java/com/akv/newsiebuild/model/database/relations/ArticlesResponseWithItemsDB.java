package com.akv.newsiebuild.model.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.database.articles.ArticlesResponseDB;

import java.util.ArrayList;

public class ArticlesResponseWithItemsDB {
    @Embedded
    public ArticlesResponseDB articlesResponse;
    @Relation(
            parentColumn = "articleResponseId",
            entityColumn = "articleId"
    )
    public ArrayList<ArticlesItemDB> articlesItemsList;
}