package com.akv.newsie.Dao.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.akv.newsie.Model.Database.Relations.UserArticlesCrossRefDB;

import java.util.List;

@Dao
public interface UserArticlesCrossRefDao {

    @Query("SELECT * FROM UserArticlesCrossRefDB")
    List<UserArticlesCrossRefDB> getAll();

    @Query("SELECT * FROM UserArticlesCrossRefDB WHERE userId in (:userIds)")
    List<UserArticlesCrossRefDB> getAllByUserId(String... userIds);

    @Query("SELECT * FROM UserArticlesCrossRefDB WHERE articleId in (:articlesItemsIds)")
    List<UserArticlesCrossRefDB> getAllByArticleItemsId(int[] articlesItemsIds);

    @Query("SELECT * FROM UserArticlesCrossRefDB WHERE articleId = :articlesItemsId AND userId = :userId")
    UserArticlesCrossRefDB getByArticleItemsIdAndUserId(int articlesItemsId, String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserArticlesCrossRefDB... articlesResponses);

    @Update
    void updateUsers(UserArticlesCrossRefDB... articlesResponses);

    @Query("DELETE FROM UserArticlesCrossRefDB")
    void deleteAll();

    @Delete
    void deleteUserBookmarks(UserArticlesCrossRefDB... userArticlesCrossRefDBS);

}
