package com.akv.newsie.Model.Database.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;
import com.akv.newsie.Model.JSON.Articles.ArticlesResponseJSON;

import java.util.ArrayList;

public class ArticlesResponseWithItemsDB {
    @Embedded
    public ArticlesResponseJSON articlesResponse;
    @Relation(
            parentColumn = "articleResponseId",
            entityColumn = "articleId"
    )
    public ArrayList<ArticlesItemJSON> articlesItemsList;
}