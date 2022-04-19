package com.akv.newsie.Model.Database.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.Articles.ArticlesResponseDB;

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