package com.akv.newsie.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akv.newsie.Model.ArticlesItem;
import com.akv.newsie.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.Holder> implements Filterable {


    private RecyclerView mRecyclerView;
    private static final String TAG = "ArticlesAdapter";

    private ArrayList<ArticlesItem> articleList, articleListFiltered;
//    private ItemPresidentBinding itemPresidentBinding;

    public ArticlesAdapter(Context context, ArrayList<ArticlesItem> articleList) {
        this.articleList = articleList;
        this.articleListFiltered = new ArrayList<>(articleList);
    }

    public ArrayList<ArticlesItem> getPresidentList() {
        return articleList;
    }

    public void setPresidentList(ArrayList<ArticlesItem> articleList) {
        this.articleList = articleList;
        this.articleListFiltered = new ArrayList<>(articleList);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_news, parent, false);
        mRecyclerView = (RecyclerView) parent;
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ArticlesItem article = articleList.get(position);

        Log.v(TAG, "ArticlesItem is " + article.toString());

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView articleTitle;
        private TextView articleAuthor;
        private TextView articleShortDescription;
        private TextView artcilePublishedAt;
        private TextView articleContent;

        public Holder(@NonNull View itemView) {
            super(itemView);

            articleTitle = itemView.findViewById(R.id.tv_art_title);
            articleAuthor = itemView.findViewById(R.id.tv_art_author);
            articleShortDescription = itemView.findViewById(R.id.tv_art_short_desc);
            artcilePublishedAt = itemView.findViewById(R.id.tv_art_pa);
            articleContent = itemView.findViewById(R.id.tv_art_content_prev);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String message = "Getting to article " + articleTitle.getText();
                        showSnackbar(v, message, Snackbar.LENGTH_LONG);
                    }
                }

            });
        }

        public void showSnackbar(View view, String message, int duration) {
            Snackbar.make(view, message, duration).show();
        }

    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence txt) {
            List<ArticlesItem> filteredList = new ArrayList<>();
            String filterPattern = "";
            if (txt == null || txt.length() == 0) {
                filteredList.addAll(articleListFiltered);
            } else {
                filterPattern = txt.toString().toLowerCase().trim();

                for (ArticlesItem item : articleListFiltered) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)
                    || item.getDescription().toLowerCase().contains(filterPattern)
                    || item.getContent().toLowerCase().contains(filterPattern)
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
                Collections.sort(filteredList, new Comparator<ArticlesItem>() {
                    @Override
                    public int compare(ArticlesItem p1, ArticlesItem p2) {
                        return p1.getPublishedAt().compareTo(p2.getPublishedAt());
                    }
                });
            }

            Log.d(TAG, "ArticlesItem List Filter: " + articleListFiltered);
            Log.d(TAG, "ArticlesItem List Filter size: " + articleListFiltered.size());
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            articleList.clear();
            articleList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }

    };


}
