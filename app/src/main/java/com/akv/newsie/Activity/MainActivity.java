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

import com.akv.newsie.API.APIEndPoint;
import com.akv.newsie.API.RetrofitInstance;
import com.akv.newsie.Adapter.ArticlesAdapter;
import com.akv.newsie.Model.APIResponse;
import com.akv.newsie.Model.ArticlesItem;
import com.akv.newsie.R;
import com.akv.newsie.Util.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ArrayList<ArticlesItem> articles;
    private RecyclerView rvArticles;
    private ArticlesAdapter articlesAdapter;
    private ArrayList<ArticlesItem> articlesList, articlesListFiltered;
    private static final String TAG = "MainActivity";
    private RetrofitInstance retrofitInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvArticles = findViewById(R.id.rv_main_list);
        sessionManager = new SessionManager(this);
        retrofitInstance = new RetrofitInstance();
        try {

//            articles = ArticlesItem.generateArticleList();
            retrofitInstance.getAPI().getResponseByKeyword("Apple", APIEndPoint.NEWSAPI_API_KEY).enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    Log.d(TAG, response.body().toString());
                    articles = response.body().getArticles();
                    if(articles.size()>0){
                        initAdapter();
                    }else{
                        Toast.makeText(getApplicationContext(), "No News Currently", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
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

        articlesAdapter = new ArticlesAdapter(getApplicationContext(), articles);

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