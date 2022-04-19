package com.akv.newsie.Activity;

import android.content.DialogInterface;
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

import com.akv.newsie.API.NewsAPIEndPoint;
import com.akv.newsie.API.RetrofitInstance;
import com.akv.newsie.Action.ArticlesItemAction;
import com.akv.newsie.Adapter.ArticlesAdapter;
import com.akv.newsie.Dao.Articles.ArticlesItemDao;
import com.akv.newsie.Model.Converter.Articles.ArticlesItemConverter;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;
import com.akv.newsie.Model.JSON.Articles.ArticlesResponseJSON;
import com.akv.newsie.R;
import com.akv.newsie.Util.AppDatabase;
import com.akv.newsie.Util.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SessionManager sessionManager;
    private List<ArticlesItemJSON> articlesItemsJSON;
    private List<ArticlesItemDB> articlesItemsDB;
    private RecyclerView rvArticles;
    private ArticlesAdapter articlesAdapter;

    private RetrofitInstance newsRetrofitInstance;
    private AppDatabase database;
    private ArticlesItemDao articlesItemDao;
    private ArticlesItemAction articlesItemAction;

    private ArticlesItemConverter articlesItemConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvArticles = findViewById(R.id.rv_main_list);
        sessionManager = new SessionManager(this);

        newsRetrofitInstance = new RetrofitInstance(NewsAPIEndPoint.API_BASE_URL);
        try {

//            articlesItemsJSON = ArticlesItemJSON.generateArticleList();

            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "newsie-db")
                    .allowMainThreadQueries()
                    .build();
            articlesItemDao = database.articlesItemDao();

            articlesItemDao.deleteAll();

            articlesItemConverter = new ArticlesItemConverter();
            newsRetrofitInstance.getAPI().getResponseByKeyword("Apple", NewsAPIEndPoint.API_KEY).enqueue(new Callback<ArticlesResponseJSON>() {
                @Override
                public void onResponse(Call<ArticlesResponseJSON> call, Response<ArticlesResponseJSON> response) {
                    articlesItemsJSON = response.body().getArticles();
                    for (int i = 0; i < articlesItemsJSON.size(); i++) {
                        articlesItemDao.insertAll(articlesItemConverter.articlesItemJSONtoDB(articlesItemsJSON.get(i)));
                    }

                    articlesItemsDB = articlesItemDao.getAll();
                    if(articlesItemsDB.size()>0){
                        initAdapter();
                    }else{
                        Toast.makeText(getApplicationContext(), "No News Currently", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArticlesResponseJSON> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

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

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(MainActivity.this);
        ab.setTitle("Logout");
        ab.setMessage("Are you Sure to logout?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                sessionManager.logout();

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

    public void initAdapter() {
        articlesItemAction = new ArticlesItemAction(getApplicationContext());

        articlesAdapter = new ArticlesAdapter(getApplicationContext(), articlesItemsDB, new ArticlesAdapter.ClickListener() {
            @Override
            public void onItemClick(ArticlesItemDB articlesItemDB) {
//                Toast.makeText(getApplicationContext(), "selected " + articlesItemDB.getTitle(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), DetailNewsActivity.class);
//                intent.putExtra("id", articlesItemDB.getArticleId());
                articlesItemAction.goToDetail(articlesItemDB);

            }
        });

//        articlesAdapter.setListener(new ArticlesAdapter.ClickListener() {
//            @Override
//            public void onItemClick(ArticlesItemDB articlesItemDB) {
//                Toast.makeText(getApplicationContext(), "selected " + articlesItemDB.getTitle(), Toast.LENGTH_SHORT).show();
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