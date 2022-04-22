package com.akv.newsiebuild.model.converter.articles;

import android.util.Log;

import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.json.articles.ArticlesItemJSON;

public class ArticlesItemConverter {
    private static final String TAG = "ArticlesItemConverter";

    public ArticlesSourceConverter articlesSourceConverter = new ArticlesSourceConverter();
    public ArticlesItemDB articlesItemJSONtoDB (ArticlesItemJSON articlesItemJSON) {
//        ArticlesItemDB articlesItemDB = new ArticlesItemDB(
//                articlesItemJSON.getPublishedAt(),
//                articlesItemJSON.getAuthor(),
//                articlesItemJSON.getUrlToImage(),
//                articlesItemJSON.getDescription(),
//                articlesSourceConverter.articlesSourceJSONtoDB(articlesItemJSON.getSource()),
//                articlesItemJSON.getTitle(),
//                articlesItemJSON.getUrl(),
//                articlesItemJSON.getContent()
//                );
        Log.d(TAG, "in articlesItemJSONtoDB");
        Log.d(TAG, "data " + articlesItemJSON.toString());

        ArticlesItemDB articlesItemDB = new ArticlesItemDB();
        Log.d(TAG, "articlesItemDB " + articlesItemDB.toString());

        articlesItemDB.setPublishedAt(articlesItemJSON.getPublishedAt());
        articlesItemDB.setAuthor(articlesItemJSON.getAuthor());
        articlesItemDB.setUrlToImage(articlesItemJSON.getUrlToImage());
        articlesItemDB.setDescription(articlesItemJSON.getDescription());
        articlesItemDB.setTitle(articlesItemJSON.getTitle());
        articlesItemDB.setUrl(articlesItemJSON.getUrl());
        articlesItemDB.setContent(articlesItemJSON.getContent());

        if(articlesItemJSON.getArticleId() != null) {
            articlesItemDB.setArticleId(articlesItemJSON.getArticleId());
        }

        return articlesItemDB;
    }

    public ArticlesItemJSON articlesItemDBtoJSON (ArticlesItemDB articlesItemDB) {
//        ArticlesItemJSON articlesItemJSON = new ArticlesItemJSON(
//                articlesItemDB.getPublishedAt(),
//                articlesItemDB.getAuthor(),
//                articlesItemDB.getUrlToImage(),
//                articlesItemDB.getDescription(),
//                articlesSourceConverter.articlesSourceDBtoJSON(articlesItemDB.getSource()),
//                articlesItemDB.getTitle(),
//                articlesItemDB.getUrl(),
//                articlesItemDB.getContent()
//        );

        ArticlesItemJSON articlesItemJSON = new ArticlesItemJSON();

        articlesItemJSON.setPublishedAt(articlesItemDB.getPublishedAt());
        articlesItemJSON.setAuthor(articlesItemDB.getAuthor());
        articlesItemJSON.setUrlToImage(articlesItemDB.getUrlToImage());
        articlesItemJSON.setDescription(articlesItemDB.getDescription());
        articlesItemJSON.setTitle(articlesItemDB.getTitle());
        articlesItemJSON.setUrl(articlesItemDB.getUrl());
        articlesItemJSON.setContent(articlesItemDB.getContent());

        articlesItemJSON.setArticleId(articlesItemDB.getArticleId());
        return articlesItemJSON;
    }
}
