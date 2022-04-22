package com.akv.newsiebuild.model.converter.user;

import com.akv.newsiebuild.model.database.user.UserDataDB;
import com.akv.newsiebuild.model.json.user.UserDataJSON;

public class UserDataConverter {

    public UserDataDB userDataJSONtoDB(UserDataJSON userDataJSON) {
//        UserDataDB userDataDB = new UserDataDB(
//                userDataJSON.getRememberExp(),
//                userDataJSON.getCompanyId(),
//                userDataJSON.getLastLogin(),
//                userDataJSON.getDateCreated(),
//                userDataJSON.getOauthUid(),
//                userDataJSON.getAvatar(),
//                userDataJSON.getIpAddress(),
//                userDataJSON.getForgotExp(),
//                userDataJSON.getRememberTime(),
//                userDataJSON.getFullName(),
//                userDataJSON.getLastActivity(),
//                userDataJSON.getTopSecret(),
//                userDataJSON.getOauthProvider(),
//                userDataJSON.getBanned(),
//                userDataJSON.getVerificationCode(),
//                userDataJSON.getEmail(),
//                userDataJSON.getUsername()
//        );

        UserDataDB userDataDB = new UserDataDB();

        userDataDB.setRememberExp(userDataJSON.getRememberExp());
        userDataDB.setCompanyId(userDataJSON.getCompanyId());
        userDataDB.setLastLogin(userDataJSON.getLastLogin());
        userDataDB.setDateCreated(userDataJSON.getDateCreated());
        userDataDB.setOauthUid(userDataJSON.getOauthUid());
        userDataDB.setAvatar(userDataJSON.getAvatar());
        userDataDB.setIpAddress(userDataJSON.getIpAddress());
        userDataDB.setForgotExp(userDataJSON.getForgotExp());
        userDataDB.setRememberTime(userDataJSON.getRememberTime());
        userDataDB.setFullName(userDataJSON.getFullName());
        userDataDB.setLastActivity(userDataJSON.getLastActivity());
        userDataDB.setTopSecret(userDataJSON.getTopSecret());
        userDataDB.setOauthProvider(userDataJSON.getOauthProvider());
        userDataDB.setBanned(userDataJSON.getBanned());
        userDataDB.setVerificationCode(userDataJSON.getVerificationCode());
        userDataDB.setEmail(userDataJSON.getEmail());
        userDataDB.setUsername(userDataJSON.getUsername());
        userDataDB.setUserResponseId(userDataJSON.getUserResponseId());

        if (userDataJSON.getId() != null) {
            userDataDB.setId(Integer.valueOf(userDataJSON.getId()));
        }

        return userDataDB;
    }

    public UserDataJSON userDataDBtoJSON(UserDataDB userDataDB) {
//        UserDataJSON userDataJSON = new UserDataJSON(
//                userDataDB.getRememberExp(),
//                userDataDB.getCompanyId(),
//                userDataDB.getLastLogin(),
//                userDataDB.getDateCreated(),
//                userDataDB.getOauthUid(),
//                userDataDB.getAvatar(),
//                userDataDB.getIpAddress(),
//                userDataDB.getForgotExp(),
//                userDataDB.getRememberTime(),
//                userDataDB.getFullName(),
//                userDataDB.getLastActivity(),
//                userDataDB.getTopSecret(),
//                userDataDB.getOauthProvider(),
//                userDataDB.getBanned(),
//                userDataDB.getVerificationCode(),
//                userDataDB.getEmail(),
//                userDataDB.getUsername()
//        );

        UserDataJSON userDataJSON = new UserDataJSON();

        userDataJSON.setRememberExp(userDataDB.getRememberExp());
        userDataJSON.setCompanyId(userDataDB.getCompanyId());
        userDataJSON.setLastLogin(userDataDB.getLastLogin());
        userDataJSON.setDateCreated(userDataDB.getDateCreated());
        userDataJSON.setOauthUid(userDataDB.getOauthUid());
        userDataJSON.setAvatar(userDataDB.getAvatar());
        userDataJSON.setIpAddress(userDataDB.getIpAddress());
        userDataJSON.setForgotExp(userDataDB.getForgotExp());
        userDataJSON.setRememberTime(userDataDB.getRememberTime());
        userDataJSON.setFullName(userDataDB.getFullName());
        userDataJSON.setLastActivity(userDataDB.getLastActivity());
        userDataJSON.setTopSecret(userDataDB.getTopSecret());
        userDataJSON.setOauthProvider(userDataDB.getOauthProvider());
        userDataJSON.setBanned(userDataDB.getBanned());
        userDataJSON.setVerificationCode(userDataDB.getVerificationCode());
        userDataJSON.setEmail(userDataDB.getEmail());
        userDataJSON.setUsername(userDataDB.getUsername());
        userDataJSON.setUserResponseId(userDataDB.getUserResponseId());

        userDataJSON.setId(String.valueOf(userDataDB.getId()));
        return userDataJSON;
    }
}
