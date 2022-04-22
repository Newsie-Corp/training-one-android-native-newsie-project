package com.akv.newsiebuild.model.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.akv.newsiebuild.model.database.user.UserDataDB;
import com.akv.newsiebuild.model.database.user.UserResponseDB;

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
