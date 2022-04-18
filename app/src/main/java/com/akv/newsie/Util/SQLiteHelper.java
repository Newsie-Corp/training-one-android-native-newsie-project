package com.akv.newsie.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "news";
    private static final int DB_VER = 1;

    public SQLiteHelper (Context context) {

        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAlarms = "CREATE TABLE `NEWS` (\n" +
                "\t`author`\tTEXT,\n" +
                "\t`title`\tTEXT,\n" +
                "\t`description`\tTEXT,\n" +
                "\t`url`\tTEXT,\n" +
                "\t`img`\tTEXT,\n" +
                "\t`publish`\tTEXT,\n" +
                "\t`content`\tTEXT,\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT\n" +
                ");";

        db.execSQL(createAlarms);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NEWS;");

        onCreate(db);
    }

    public void addNews(String author, String title, String description, String url, String img, String publish, String content){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String insertNews = "INSERT INTO NEWS (author, title, description, url, img, publish, content) \n" +
                    "values ('"+author+"', '"+title+"', '"+description+"', '"+url+"', '"+img+"', '"+publish+"', '"+content+"')";
            db.execSQL(insertNews);
        } catch (Exception ex){
            Log.e("SQLiteHelper", "addNews error : "+ex.toString());
        }
    }

    public List<ArticlesItemJSON> getAllNews(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ArticlesItemJSON> list = new ArrayList<ArticlesItemJSON>();
        String query = "SELECT author, title, description, url, img, publish, content, id FROM NEWS order by publish ASC";
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                ArticlesItemJSON news = new ArticlesItemJSON();
                                news.setAuthor(cursor.getString(0)) ;
                                news.setTitle(cursor.getString(1));
                                news.setDescription(cursor.getString(2));
                                news.setUrl(cursor.getString(3));
                                news.setUrlToImage(cursor.getString(4));
                                news.setPublishedAt(cursor.getString(5));
                                list.add(news);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                }
            }

            cursor.close();
            db.close();

        } catch (Exception ex){
            Log.e("SQLiteHelper", "getAllNews error : "+ex.toString());
        }

        return  list;
    }
}
