package com.akv.newsie.Model.Database.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.akv.newsie.Model.Database.User.UserDataDB;
import com.akv.newsie.Model.Database.User.UserResponseDB;

import java.util.ArrayList;

public class UserResponseWithUserDataDB {
    @Embedded
    public UserResponseDB userResponse;
    @Relation(
            parentColumn = "userResponseId",
            entityColumn = "userId"
    )
    public ArrayList<UserDataDB> userDataList;
}
