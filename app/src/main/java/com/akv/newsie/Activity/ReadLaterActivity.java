package com.akv.newsie.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akv.newsie.Adapter.ArticlesAdapter;
import com.akv.newsie.Model.JSON.Articles.ArticlesItemJSON;
import com.akv.newsie.R;

import java.util.ArrayList;

public class ReadLaterActivity extends AppCompatActivity {

    ArrayList<ArticlesItemJSON> articles;
    RecyclerView rvArticles;
    ArticlesAdapter articlesAdapter;
    ArrayList<ArticlesItemJSON> articlesList, articlesListFiltered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later);
        articles = ArticlesItemJSON.generateArticleList();
        rvArticles = findViewById(R.id.rv_readlater_list);
        articlesAdapter = new ArticlesAdapter(getApplicationContext(), articles);
        rvArticles.setAdapter(articlesAdapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_read_later, menu);
        filterFunction(menu);
        return true;
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