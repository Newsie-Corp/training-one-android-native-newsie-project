package com.akv.newsie.Util;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.akv.newsie.Dao.Articles.ArticlesItemDao;
import com.akv.newsie.Dao.Articles.ArticlesResponseDao;
import com.akv.newsie.Dao.User.UserArticlesCrossRefDao;
import com.akv.newsie.Dao.User.UserDataDao;
import com.akv.newsie.Dao.User.UserResponseDao;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.Articles.ArticlesResponseDB;
import com.akv.newsie.Model.Database.Relations.UserArticlesCrossRefDB;
import com.akv.newsie.Model.Database.User.UserDataDB;
import com.akv.newsie.Model.Database.User.UserResponseDB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        ArticlesItemDB.class,
        ArticlesResponseDB.class,
        UserArticlesCrossRefDB.class,
        UserDataDB.class,
        UserResponseDB.class,
}, version = 4, exportSchema = true)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticlesItemDao articlesItemDao();
    public abstract ArticlesResponseDao articlesResponseDao();
    public abstract UserDataDao userDataDao();
    public abstract UserResponseDao userResponseDao();
    public abstract UserArticlesCrossRefDao userBookmarksDao();

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);

}