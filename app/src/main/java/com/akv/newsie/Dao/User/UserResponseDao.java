package com.akv.newsie.Dao.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.akv.newsie.Model.Database.User.UserResponseDB;

import java.util.List;

@Dao
public interface UserResponseDao {

    @Query("SELECT * FROM UserResponseDB")
    List<UserResponseDB> getAll();

    @Query("SELECT * FROM UserResponseDB WHERE userResponseId in (:userResponseIds)")
    List<UserResponseDB> loadAllById(int[] userResponseIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserResponseDB... users);

    @Update
    void updateUsers(UserResponseDB... users);

    @Query("DELETE FROM UserResponseDB")
    void deleteAll();
}
