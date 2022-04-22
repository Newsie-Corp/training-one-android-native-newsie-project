package com.akv.newsiebuild.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.akv.newsiebuild.action.ArticlesItemAction;
import com.akv.newsiebuild.adapter.ArticlesAdapter;
import com.akv.newsiebuild.dao.articles.ArticlesItemDao;
import com.akv.newsiebuild.dao.user.UserArticlesCrossRefDao;
import com.akv.newsiebuild.model.converter.articles.ArticlesItemConverter;
import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.database.relations.UserArticlesCrossRefDB;
import com.akv.newsiebuild.R;
import com.akv.newsiebuild.util.AppDatabase;
import com.akv.newsiebuild.util.SessionManager;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailNewsActivity extends AppCompatActivity {

    public static final String TAG = "DetailNewsActivity";

    private ArticlesAdapter articlesAdapter;
    private List<ArticlesItemDB> articlesItemsDB;
    private List<UserArticlesCrossRefDB> userBookmarksDB;
    private ArticlesItemAction articlesItemAction;
    private SessionManager sessionManager;

    private ArticlesItemConverter articlesItemConverter;

    private AppDatabase database;
    private ArticlesItemDao articlesItemsDao;
    private UserArticlesCrossRefDao userBookmarksDao;

    private TextView articleTitle;
    private TextView articleAuthor;
    //    private TextView articleShortDescription;
    private TextView artcilePublishedAt;
    private TextView articleContent;
    private ImageView articleImage;
    private ImageView actionIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        sessionManager = new SessionManager(this);
        articlesItemAction = new ArticlesItemAction(this);
        int[] articlesItemsIds = new int[1];
        articlesItemsIds[0] = getIntent().getIntExtra("articlesId", -1);

        if (articlesItemsIds[0] == -1)
//            Toast.makeText(this, "Invalid action", Toast.LENGTH_SHORT).show();
            articlesItemAction.showSnackbar(articleTitle, "Invalid action", Snackbar.LENGTH_LONG);

        try {
            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "newsie-db")
                    .allowMainThreadQueries()
                    .build();

            articlesItemsDao = database.articlesItemDao();
            userBookmarksDao = database.userBookmarksDao();
            articlesItemsDB = articlesItemsDao.getAllById(articlesItemsIds);

            initView(articlesItemsDB.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
        }
    }

    public void initView(ArticlesItemDB articlesItemDB) {
        articleTitle = findViewById(R.id.tv_art_title);
        articleAuthor = findViewById(R.id.tv_art_author);
        artcilePublishedAt = findViewById(R.id.tv_art_pa);
        articleImage = (ImageView) findViewById(R.id.iv_art_content_img);
        actionIcon = (ImageView) findViewById(R.id.iv_art_plus_ic);
//        articleShortDescription = findViewById(R.id.tv_art_short_desc);
        articleContent = findViewById(R.id.tv_art_content_prev);

        String publishedDate = null;
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(articlesItemDB.getPublishedAt());
            publishedDate = "published on " + new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        articleTitle.setText(articlesItemDB.getTitle());
        articleAuthor.setText("written by " + articlesItemDB.getAuthor());
        artcilePublishedAt.setText(publishedDate);
        articleImage.setImageResource(R.drawable.news_icon);
//        articleShortDescription.setText(articlesItemDB.getDescription());
        articleContent.setText(articlesItemDB.getContent());
        actionIcon.setImageResource(R.drawable.plus_icon);
        actionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "added article " + articlesItemDB.getTitle() + " to read later";
//                Toast.makeText(DetailNewsActivity.this, message, Toast.LENGTH_SHORT).show();
                articlesItemAction.showSnackbar(actionIcon, message, Snackbar.LENGTH_SHORT);
                UserArticlesCrossRefDB userArticlesCrossRefDB = new UserArticlesCrossRefDB();
                userArticlesCrossRefDB.setArticleId(articlesItemDB.getArticleId());
                userArticlesCrossRefDB.setUserId(sessionManager.getUsername());
                userBookmarksDao.insertAll(userArticlesCrossRefDB);

                userBookmarksDB = userBookmarksDao.getAll();
                Log.d(TAG, "userBookmarkDB " + userBookmarksDB.size() + " " + userBookmarksDB.toString());

            }
        });

        Glide.with(this).load(articlesItemDB.getUrlToImage()).into(articleImage);

    }
}