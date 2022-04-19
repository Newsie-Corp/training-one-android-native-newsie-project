package com.akv.newsie.Model.Converter.User;

import com.akv.newsie.Model.Database.User.UserResponseDB;
import com.akv.newsie.Model.JSON.User.UserResponseJSON;

public class UserResponseConverter {
    public UserDataConverter userDataConverter = new UserDataConverter();

    public UserResponseDB userResponseJSONtoDB(UserResponseJSON userResponseJSON) {
//        UserResponseDB userResponseDB = new UserResponseDB(
//                userDataConverter.userDataJSONtoDB(userResponseJSON.getData()),
//                userResponseJSON.getMessage(),
//                userResponseJSON.isStatus(),
//                userResponseJSON.getToken()
//        );

        UserResponseDB userResponseDB = new UserResponseDB();

        userResponseDB.setMessage(userResponseJSON.getMessage());
        userResponseDB.setStatus(userResponseJSON.isStatus());
        userResponseDB.setToken(userResponseJSON.getToken());

        return userResponseDB;
    }

    public UserResponseJSON userResponseDBtoJSON(UserResponseDB userResponseDB) {
//        UserResponseJSON userResponseJSON = new UserResponseJSON(
//                userDataConverter.userDataDBtoJSON(userResponseDB.getData()),
//                userResponseDB.getMessage(),
//                userResponseDB.isStatus(),
//                userResponseDB.getToken()
//        );

        UserResponseJSON userResponseJSON = new UserResponseJSON();

        userResponseJSON.setMessage(userResponseDB.getMessage());
        userResponseJSON.setStatus(userResponseDB.isStatus());
        userResponseJSON.setToken(userResponseDB.getToken());

        return userResponseJSON;
    }
}
