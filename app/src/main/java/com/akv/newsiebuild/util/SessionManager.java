package com.akv.newsiebuild.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.akv.newsiebuild.activity.LoginActivity;
import com.akv.newsiebuild.activity.MainActivity;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public static final String key_email = "user_email";
    public static final String key_id = "username";
    public static final String key_nama_user = "nama_user";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String username, String fullname, String email){
        editor.putBoolean(is_login, true);
        editor.putString(key_id, username);
        editor.putString(key_email, email);
        editor.putString(key_nama_user, fullname);
        editor.commit();
    }

    public void createSessionNama(String nama_user){
        editor.putBoolean(is_login, true);
        editor.putString(key_nama_user, nama_user);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(key_nama_user, null);
    }

    public String getEmail() {
        return pref.getString(key_email, null);
    }

    public void checkLogin(){
        if (!this.is_login()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}