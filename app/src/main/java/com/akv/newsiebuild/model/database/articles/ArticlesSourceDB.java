package com.akv.newsiebuild.model.database.articles;

import com.google.gson.GsonBuilder;

//@Entity
public class ArticlesSourceDB {

    private String name;
    private String id;
    private Integer articleId;

//    @PrimaryKey(autoGenerate = true)
//    private Integer articleSourceId;

    public ArticlesSourceDB() {
    }

//    public ArticlesSourceDB(String name, String id) {
//        this.name = name;
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesSourceDB.class);
    }
}