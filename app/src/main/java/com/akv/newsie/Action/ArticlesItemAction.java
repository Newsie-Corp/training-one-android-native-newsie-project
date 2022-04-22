package com.akv.newsie.Action;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.akv.newsie.Activity.DetailNewsActivity;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.google.android.material.snackbar.Snackbar;

public class ArticlesItemAction {
    Context context;

    public ArticlesItemAction(Context context) {
        this.context = context;
    }

    public void goToDetail(ArticlesItemDB articlesItemDB) {
        Intent intent = new Intent(context, DetailNewsActivity.class);
        intent.putExtra("articlesId", articlesItemDB.getArticleId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

}
