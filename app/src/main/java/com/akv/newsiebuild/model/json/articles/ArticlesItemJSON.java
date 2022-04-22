package com.akv.newsiebuild.model.json.articles;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticlesItemJSON {

    private Integer articleId;

    private Integer articleResponseId;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("author")
    private String author;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("description")
    private String description;

    @SerializedName("source")
    private ArticlesSourceJSON source;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("content")
    private String content;

    public ArticlesItemJSON() {

    }

//    public ArticlesItemJSON(String publishedAt, String author, String urlToImage, String description, ArticlesSourceJSON source, String title, String url, String content) {
//        this.publishedAt = publishedAt;
//        this.author = author;
//        this.urlToImage = urlToImage;
//        this.description = description;
//        this.source = source;
//        this.title = title;
//        this.url = url;
//        this.content = content;
//    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleResponseId() {
        return articleResponseId;
    }

    public void setArticleResponseId(Integer articleResponseId) {
        this.articleResponseId = articleResponseId;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSource(ArticlesSourceJSON source) {
        this.source = source;
    }

    public ArticlesSourceJSON getSource() {
        return source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesItemJSON.class);
    }

    public static ArrayList<ArticlesItemJSON> generateArticleList() {
        ArrayList<ArticlesItemJSON> articlesList = new ArrayList<>();

//        articlesList.add(new ArticlesItemJSON("2022-04-17T14:03:06Z",
//                "Mary Ann Azevedo",
//                "https://techcrunch.com/wp-content/uploads/2021/04/boxing_gloves_suit-e1650068012177.jpg?w=600",
//                "If it feels like we’ve been over-indexing on expense/spend management news, it’s because there has just been so darn much of it.",
//                new ArticlesSourceJSON("techcrunch", "TechCrunch"),
//                "Fintech Roundup: The gloves are off in the spend management space",
//                "https://techcrunch.com/2022/04/17/fintech-roundup-the-gloves-are-off-in-the-spend-management-space/",
//                "Welcome to my weekly fintech-focused column. Ill be publishing this every Sunday, so in between posts, be sure to listen to the Equity podcast and hear Alex Wilhelm, Natasha Mascarenhas and me riff o… [+19795 chars]"));
        return articlesList;
    }
}