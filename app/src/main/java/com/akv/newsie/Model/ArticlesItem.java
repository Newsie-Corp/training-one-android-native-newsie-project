package com.akv.newsie.Model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticlesItem {

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("author")
    private String author;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("description")
    private String description;

    @SerializedName("source")
    private Source source;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("content")
    private String content;

    @SerializedName("content")
    private String id;

    public ArticlesItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public ArticlesItem(String publishedAt, String author, String urlToImage, String description, Source source, String title, String url, String content) {
        this.publishedAt = publishedAt;
        this.author = author;
        this.urlToImage = urlToImage;
        this.description = description;
        this.source = source;
        this.title = title;
        this.url = url;
        this.content = content;
        this.id = id;
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

    public void setSource(Source source) {
        this.source = source;
    }

    public Source getSource() {
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
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesItem.class);
    }

    public static ArrayList<ArticlesItem> generateArticleList() {
        ArrayList<ArticlesItem> articlesList = new ArrayList<>();

        articlesList.add(new ArticlesItem("2022-04-17T14:03:06Z",
                "Mary Ann Azevedo",
                "https://techcrunch.com/wp-content/uploads/2021/04/boxing_gloves_suit-e1650068012177.jpg?w=600",
                "If it feels like we’ve been over-indexing on expense/spend management news, it’s because there has just been so darn much of it.",
                new Source("techcrunch", "TechCrunch"),
                "Fintech Roundup: The gloves are off in the spend management space",
                "https://techcrunch.com/2022/04/17/fintech-roundup-the-gloves-are-off-in-the-spend-management-space/",
                "Welcome to my weekly fintech-focused column. Ill be publishing this every Sunday, so in between posts, be sure to listen to the Equity podcast and hear Alex Wilhelm, Natasha Mascarenhas and me riff o… [+19795 chars]"));
        return articlesList;
    }
}