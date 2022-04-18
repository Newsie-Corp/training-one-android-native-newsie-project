package com.akv.newsie.Dao.Articles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.akv.newsie.Model.Database.Articles.ArticlesResponseDB;

import java.util.List;

@Dao
public interface ArticlesResponseDao {

    @Query("SELECT * FROM ArticlesResponseDB")
    List<ArticlesResponseDB> getAll();

    @Query("SELECT * FROM ArticlesResponseDB WHERE articleResponseId in (:articleResponseIds)")
    List<ArticlesResponseDB> loadAllById(int[] articleResponseIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArticlesResponseDB... articlesResponses);

    @Update
    void updateUsers(ArticlesResponseDB... articlesResponses);

    @Query("DELETE FROM ArticlesResponseDB")
    void deleteAll();
}
