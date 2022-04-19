package com.akv.newsie.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

import java.util.List;

public class ReadLaterActivity extends AppCompatActivity {

    private static final String TAG = "ReadLaterActivity";
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
        articlesItemsDB = ArticlesItemDB.generateArticleList();
        rvArticles = findViewById(R.id.rv_readlater_list);
        sessionManager = new SessionManager(this);

        try {

//            articlesItemsJSON = ArticlesItemJSON.generateArticleList();

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

                articlesItemsDB.add((ArticlesItemDB) articlesItemsDao.getAllById(articlesItemsIds));
                initAdapter();
            } else {
                Toast.makeText(getApplicationContext(), "No news to be read later currently", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_read_later, menu);
        filterFunction(menu);
        return true;
    }

    public void initAdapter() {
        articlesItemAction = new ArticlesItemAction(getApplicationContext());

        articlesAdapter = new ArticlesAdapter(getApplicationContext(), articlesItemsDB, new ArticlesAdapter.ClickListener() {
            @Override
            public void onItemClick(ArticlesItemDB articlesItemsDB) {
//                Toast.makeText(getApplicationContext(), "selected " + articlesItemsDB.getTitle(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), DetailNewsActivity.class);
//                intent.putExtra("id", articlesItemsDB.getArticleId());
                articlesItemAction.goToDetail(articlesItemsDB);

            }
        });

//        articlesAdapter.setListener(new ArticlesAdapter.ClickListener() {
//            @Override
//            public void onItemClick(ArticlesItemDB articlesItemsDB) {
//                Toast.makeText(getApplicationContext(), "selected " + articlesItemsDB.getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });

        rvArticles.setAdapter(articlesAdapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public void filterFunction(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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