package com.akv.newsiebuild.dao.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.akv.newsiebuild.model.database.user.UserDataDB;

import java.util.List;

@Dao
public interface UserDataDao {

    @Query("SELECT * FROM UserDataDB")
    List<UserDataDB> getAll();

    @Query("SELECT * FROM UserDataDB WHERE id in (:userIds)")
    List<UserDataDB> loadAllById(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserDataDB... users);

    @Update
    void updateUsers(UserDataDB... users);

    @Query("DELETE FROM UserDataDB")
    void deleteAll();
}
