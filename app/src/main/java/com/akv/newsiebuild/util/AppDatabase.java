package com.akv.newsiebuild.util;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.akv.newsiebuild.dao.articles.ArticlesItemDao;
import com.akv.newsiebuild.dao.articles.ArticlesResponseDao;
import com.akv.newsiebuild.dao.user.UserArticlesCrossRefDao;
import com.akv.newsiebuild.dao.user.UserDataDao;
import com.akv.newsiebuild.dao.user.UserResponseDao;
import com.akv.newsiebuild.model.database.articles.ArticlesItemDB;
import com.akv.newsiebuild.model.database.articles.ArticlesResponseDB;
import com.akv.newsiebuild.model.database.relations.UserArticlesCrossRefDB;
import com.akv.newsiebuild.model.database.user.UserDataDB;
import com.akv.newsiebuild.model.database.user.UserResponseDB;

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