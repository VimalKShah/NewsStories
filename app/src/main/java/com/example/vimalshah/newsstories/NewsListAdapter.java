package com.example.vimalshah.newsstories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private ArrayList<News> newsList;

    NewsListAdapter(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater  = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.news_item, viewGroup, false);
        return new NewsViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        final News news = newsList.get(i);
        newsViewHolder.title.setText(news.getTitle());
        newsViewHolder.description.setText(news.getDescription());
        newsViewHolder.imageView.setImageBitmap(news.getBitmap());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView title, description;
        public ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.imageView = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
