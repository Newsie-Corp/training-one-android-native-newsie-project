package com.akv.newsiebuild.action;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.akv.newsiebuild.activity.DetailNewsActivity;
import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
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
