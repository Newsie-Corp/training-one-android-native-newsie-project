package com.akv.newsie.Model.Converter.Articles;

import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.Articles.ArticlesResponseDB;
import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;
import com.akv.newsie.Model.JSON.Articles.ArticlesResponseJSON;

import java.util.ArrayList;
import java.util.List;

public class ArticlesResponseConverter {

    public ArticlesItemConverter articlesItemConverter = new ArticlesItemConverter();
    public ArticlesResponseDB articlesResponseJSONtoDB(ArticlesResponseJSON articlesResponseJSON) {

        List<ArticlesItemDB> articlesItemDBList = new ArrayList<ArticlesItemDB>();

        for(int i = 0; i < articlesResponseJSON.getArticles().size(); i++) {
            articlesItemDBList.add(articlesItemConverter.articlesItemJSONtoDB(articlesResponseJSON.getArticles().get(i)));
        }

//        ArticlesResponseDB articlesResponseDB = new ArticlesResponseDB(
//                articlesResponseJSON.getArticleResponseId(),
//                articlesResponseJSON.getTotalResults(),
//                articlesItemDBList,
//                articlesResponseJSON.getStatus()
//        );

        ArticlesResponseDB articlesResponseDB = new ArticlesResponseDB();

        articlesResponseDB.setArticleResponseId(articlesResponseJSON.getArticleResponseId());
        articlesResponseDB.setTotalResults(articlesResponseJSON.getTotalResults());
        articlesResponseDB.setStatus(articlesResponseJSON.getStatus());

        if(articlesResponseJSON.getArticleResponseId() != null) {
            articlesResponseDB.setArticleResponseId(articlesResponseJSON.getArticleResponseId());
        }

        return articlesResponseDB;
    }

    public ArticlesResponseJSON articlesResponseDBtoJSON(ArticlesResponseDB articlesResponseDB) {
        List<ArticlesItemJSON> articlesItemJSONList = new ArrayList<ArticlesItemJSON>();

//        for(int i = 0; i < articlesResponseDB.getArticles().size(); i++) {
//            articlesItemJSONList.add(articlesItemConverter.articlesItemDBtoJSON(articlesResponseDB.getArticles().get(i)));
//        }
//        ArticlesResponseJSON articlesResponseJSON = new ArticlesResponseJSON(
//                articlesResponseDB.getArticleResponseId(),
//                articlesResponseDB.getTotalResults(),
//                articlesItemJSONList,
//                articlesResponseDB.getStatus()
//        );

        ArticlesResponseJSON articlesResponseJSON = new ArticlesResponseJSON();

        articlesResponseJSON.setArticleResponseId(articlesResponseDB.getArticleResponseId());
        articlesResponseJSON.setTotalResults(articlesResponseDB.getTotalResults());
        articlesResponseJSON.setStatus(articlesResponseDB.getStatus());

        articlesResponseJSON.setArticleResponseId(articlesResponseDB.getArticleResponseId());

        return articlesResponseJSON;
    }
}
