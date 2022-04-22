package com.akv.newsiebuild.model.converter.articles;

import com.akv.newsiebuild.model.database.articles.ArticlesSourceDB;
import com.akv.newsiebuild.model.json.articles.ArticlesSourceJSON;

public class ArticlesSourceConverter {

    public ArticlesSourceDB articlesSourceJSONtoDB(ArticlesSourceJSON articlesSourceJSON) {
//        ArticlesSourceDB articlesSourceDB = new ArticlesSourceDB(
//                articlesSourceJSON.getName(),
//                articlesSourceJSON.getId()
//        );

        ArticlesSourceDB articlesSourceDB = new ArticlesSourceDB();
        articlesSourceDB.setName(articlesSourceJSON.getName());
        articlesSourceDB.setId(articlesSourceJSON.getId());
        articlesSourceDB.setArticleId(articlesSourceJSON.getArticleId());

        return articlesSourceDB;
    }

    public ArticlesSourceJSON articlesSourceDBtoJSON(ArticlesSourceDB articlesSourceDB) {
//        ArticlesSourceJSON articlesSourceJSON = new ArticlesSourceJSON(
//                articlesSourceDB.getName(),
//                articlesSourceDB.getId()
//        );

        ArticlesSourceJSON articlesSourceJSON = new ArticlesSourceJSON();
        articlesSourceJSON.setName(articlesSourceDB.getName());
        articlesSourceJSON.setId(articlesSourceDB.getId());
        articlesSourceJSON.setArticleId(articlesSourceDB.getArticleId());

        return articlesSourceJSON;
    }
}