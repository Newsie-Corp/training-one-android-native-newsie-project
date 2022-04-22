package com.akv.newsie.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.akv.newsie.API.RetrofitInstance;
import com.akv.newsie.Action.ArticlesItemAction;
import com.akv.newsie.Adapter.ArticlesAdapter;
import com.akv.newsie.Dao.Articles.ArticlesItemDao;
import com.akv.newsie.Dao.User.UserArticlesCrossRefDao;
import com.akv.newsie.Model.Converter.Articles.ArticlesItemConverter;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.Relations.UserArticlesCrossRefDB;
import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;
import com.akv.newsie.R;
import com.akv.newsie.Util.AppDatabase;
import com.akv.newsie.Util.SessionManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ReadLaterActivity extends AppCompatActivity {

    public static final String TAG = "ReadLaterActivity";
    private RecyclerView rvArticles;
    private ArticlesAdapter articlesAdapter;

    private SessionManager sessionManager;
    private List<ArticlesItemJSON> articlesItemsJSON;
    private List<ArticlesItemDB> articlesItemsDB;
    private List<UserArticlesCrossRefDB> userBookmarksDB;
    private ArticlesItemConverter articlesItemConverter;

    private RetrofitInstance newsRetrofitInstance;
    private AppDatabase database;

    private ArticlesItemDao articlesItemsDao;
    private UserArticlesCrossRefDao userBookmarksDao;

    private ArticlesItemAction articlesItemAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later);
        rvArticles = findViewById(R.id.rv_readlater_list);
        sessionManager = new SessionManager(this);

        initRvData();

        SearchView searchBtn = findViewById(R.id.sv_rl_btn);
        filterFunction(searchBtn);
    }

    public void initAdapter() {
        articlesItemAction = new ArticlesItemAction(getApplicationContext());

        articlesAdapter = new ArticlesAdapter(getApplicationContext(), articlesItemsDB, new ArticlesAdapter.ClickListener() {
            @Override
            public void onItemClick(ArticlesItemDB articlesItemsDB) {
                articlesItemAction.goToDetail(articlesItemsDB);
            }
        }, TAG);

        articlesAdapter.setCallback(new ArticlesAdapter.Callback() {
            @Override
            public void onRefreshItem() {
                initRvData();
            }

            @Override
            public void onDeletePressed(ArticlesItemDB articlesItemDB) {
                deleteConfirmation(articlesItemDB);
            }


        });

        rvArticles.setAdapter(articlesAdapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public void initRvData() {
        try {

            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "newsie-db")
                    .allowMainThreadQueries()
                    .build();

            String userId = sessionManager.getUsername();
            userBookmarksDao = database.userBookmarksDao();
            userBookmarksDB = userBookmarksDao.getAllByUserId(userId);

            if (userBookmarksDB.size() > 0) {

                int size = userBookmarksDB.size();

                articlesItemsDao = database.articlesItemDao();
                int[] articlesItemsIds = new int[size];
                for (int i = 0; i < userBookmarksDB.size(); i++) {
                    articlesItemsIds[i] = userBookmarksDB.get(i).getArticleId();
                }

                articlesItemsDB = articlesItemsDao.getAllById(articlesItemsIds);

                initAdapter();
            } else {
//                Toast.makeText(getApplicationContext(), "No saved News", Toast.LENGTH_SHORT).show();
                articlesItemAction.showSnackbar(rvArticles, "No saved News", Snackbar.LENGTH_LONG);
                onBackPressed();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
        }
    }

    public void deleteConfirmation(ArticlesItemDB articlesItemDB) {
        android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(this);
        ab.setTitle("Delete");
        ab.setMessage("Are you Sure want to delete this news?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String message = "deleted article " + articlesItemDB.getTitle() + " from read later";
//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                articlesItemAction.showSnackbar(rvArticles, message, Snackbar.LENGTH_LONG);
                UserArticlesCrossRefDB userArticlesCrossRefDB
                        = userBookmarksDao.getByArticleItemsIdAndUserId(
                        articlesItemDB.getArticleId(),
                        sessionManager.getUsername());
                userBookmarksDao.deleteUserBookmarks(userArticlesCrossRefDB);

                userBookmarksDB = userBookmarksDao.getAll();
//                        callback.onRefreshItem();
                Log.d(TAG, "userBookmarkDB " + userBookmarksDB.size() + " " + userBookmarksDB.toString());
                dialog.dismiss();
                initRvData();

            }
        });
        ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ab.show();
    }

    public void filterFunction(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                articlesAdapter.getFilter().filter(text);
                return false;
            }
        });
    }
}