package com.akv.newsie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.akv.newsie.Action.ArticlesItemAction;
import com.akv.newsie.Activity.MainActivity;
import com.akv.newsie.Activity.ReadLaterActivity;
import com.akv.newsie.Dao.User.UserArticlesCrossRefDao;
import com.akv.newsie.Model.Database.Articles.ArticlesItemDB;
import com.akv.newsie.Model.Database.Relations.UserArticlesCrossRefDB;
import com.akv.newsie.R;
import com.akv.newsie.Util.AppDatabase;
import com.akv.newsie.Util.SessionManager;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.Holder> implements Filterable {

    public interface ClickListener {
        void onItemClick(ArticlesItemDB articlesItemDB);
    }

    private RecyclerView mRecyclerView;
    private static final String TAG = "ArticlesAdapter";
    private String currentActivity;
    private List<ArticlesItemDB> articleList, articleListFiltered;
    private UserArticlesCrossRefDao userBookmarksDao;
    private List<UserArticlesCrossRefDB> userBookmarksDB;
    private SessionManager sessionManager;
    private AppDatabase database;
    private Context context;
    private ArticlesItemAction articlesItemAction;

    private ClickListener listener;

    public ArticlesAdapter(Context context, List<ArticlesItemDB> articleList, ClickListener clickListener, String currentActivity) {
        this.context = context;
        this.articleList = articleList;
        this.articleListFiltered = new ArrayList<>(articleList);
        this.listener = clickListener;
        this.sessionManager = new SessionManager(context);
        articlesItemAction = new ArticlesItemAction(context);

        database = Room.databaseBuilder(context, AppDatabase.class, "newsie-db")
                .allowMainThreadQueries()
                .build();
        this.userBookmarksDao = database.userBookmarksDao();
        this.currentActivity = currentActivity;
    }

    public List<ArticlesItemDB> getPresidentList() {
        return articleList;
    }

    public void setPresidentList(List<ArticlesItemDB> articleList) {
        this.articleList = articleList;
        this.articleListFiltered = new ArrayList<>(articleList);
    }

    public ClickListener getListener() {
        return listener;
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_news, parent, false);
        mRecyclerView = (RecyclerView) parent;
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
        ArticlesItemDB article = articleList.get(position);

        String publishedDate = null;
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(article.getPublishedAt());
            publishedDate = "on " + new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "ArticlesItemDB is " + article.toString());
        holder.articleTitle.setText(article.getTitle());
        holder.articleAuthor.setText("by " + article.getAuthor());
        holder.artcilePublishedAt.setText(publishedDate);
        holder.articleImage.setImageResource(R.drawable.news_icon);

        if (currentActivity.equals(MainActivity.TAG)) {
            holder.actionIcon.setImageResource(R.drawable.plus_icon);
            holder.actionIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        String message = "adding article " + article.getTitle() + " to read later";
                        articlesItemAction.showSnackbar(v, message, Snackbar.LENGTH_LONG);
//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                        UserArticlesCrossRefDB userArticlesCrossRefDB = new UserArticlesCrossRefDB();
                        userArticlesCrossRefDB.setArticleId(article.getArticleId());
                        userArticlesCrossRefDB.setUserId(sessionManager.getUsername());
                        userBookmarksDao.insertAll(userArticlesCrossRefDB);

                        userBookmarksDB = userBookmarksDao.getAll();
                        Log.d(TAG, "userBookmarkDB " + userBookmarksDB.size() + " " + userBookmarksDB.toString());

                    }
                }
            });
        } else if (currentActivity.equals(ReadLaterActivity.TAG)) {
            holder.actionIcon.setImageResource(R.drawable.delete_icon);
            holder.actionIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        callback.onDeletePressed(articleList.get(position));
                    }
                }
            });
        }
        Glide.with(mRecyclerView).load(article.getUrlToImage()).into(holder.articleImage);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView articleTitle;
        private TextView articleAuthor;
        private TextView artcilePublishedAt;
        private ImageView articleImage;
        private ImageView actionIcon;

        public Holder(@NonNull View itemView) {
            super(itemView);

            articleTitle = itemView.findViewById(R.id.tv_art_title);
            articleAuthor = itemView.findViewById(R.id.tv_art_author);
            artcilePublishedAt = itemView.findViewById(R.id.tv_art_pa);
            articleImage = (ImageView) itemView.findViewById(R.id.iv_art_content_img);
            actionIcon = (ImageView) itemView.findViewById(R.id.iv_art_plus_ic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
//                        String message = "Getting to article " + articleTitle.getText();
//                        articlesItemAction.showSnackbar(v, message, Snackbar.LENGTH_LONG);
                        articlesItemAction.goToDetail(articleList.get(position));
                    }
                }

            });

        }

    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onRefreshItem();
        void onDeletePressed(ArticlesItemDB articlesItemDB);
    }

    @Override
    public Filter getFilter() {
        return articleFilter;
    }

    private Filter articleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence txt) {
            List<ArticlesItemDB> filteredList = new ArrayList<>();
            String filterPattern = "";
            if (txt == null || txt.length() == 0) {
                filteredList.addAll(articleListFiltered);
            } else {
                filterPattern = txt.toString().toLowerCase().trim();

                for (ArticlesItemDB item : articleListFiltered) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)
//                            || item.getDescription().toLowerCase().contains(filterPattern)
//                            || item.getContent().toLowerCase().contains(filterPattern)
                    )
                        filteredList.add(item);
                }

            }

            if (filteredList.size() == 0) {
                Log.d(TAG, "filterPattern: " + filterPattern);
                Snackbar snackbar = Snackbar
                        .make(mRecyclerView,
                                filterPattern + " yield no result", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                Collections.sort(filteredList, (p1, p2) -> {
                    return p1.getPublishedAt().compareTo(p2.getPublishedAt());
                });
            }

            Log.d(TAG, "ArticlesItemDB List Filter: " + articleListFiltered);
            Log.d(TAG, "ArticlesItemDB List Filter size: " + articleListFiltered.size());
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            articleList.clear();
            articleList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}