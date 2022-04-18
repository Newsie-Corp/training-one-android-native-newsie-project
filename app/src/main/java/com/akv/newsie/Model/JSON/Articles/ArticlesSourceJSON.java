package com.akv.newsie.Model.JSON.Articles;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class ArticlesSourceJSON {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public ArticlesSourceJSON(String name, String id) {
        this.name = name;
        this.id = id;
    }

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

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, ArticlesSourceJSON.class);
    }
}