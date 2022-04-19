package com.akv.newsie.Action;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.akv.newsie.Activity.DetailNewsActivity;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;

public class ArticlesItemAction {
    Context context;

    public ArticlesItemAction(Context context) {
        this.context = context;
    }

    public void goToDetail(ArticlesItemDB articlesItemDB) {
        Toast.makeText(context, "selected " + articlesItemDB.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailNewsActivity.class);
        intent.putExtra("id", articlesItemDB.getArticleId());
    }

}
