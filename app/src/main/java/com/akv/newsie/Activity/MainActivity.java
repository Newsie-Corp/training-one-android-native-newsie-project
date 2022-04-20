package com.akv.newsie.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akv.newsie.Adapter.ArticlesAdapter;
import com.akv.newsie.Model.ArticlesItem;
import com.akv.newsie.R;
import com.akv.newsie.Util.SessionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ArrayList<ArticlesItem> articles;
    RecyclerView rvArticles;
    ArticlesAdapter articlesAdapter;
    ArrayList<ArticlesItem> articlesList, articlesListFiltered;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);

        try {
//            articles = ArticlesItem.generateArticleList();
            rvArticles = findViewById(R.id.rv_main_list);
            articlesAdapter = new ArticlesAdapter(getApplicationContext(), articles);
            rvArticles.setAdapter(articlesAdapter);
            rvArticles.setLayoutManager(new LinearLayoutManager(this));
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