package com.integro.sjc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
   Context context;
   ArrayList<News>newsArrayList;

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_news,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news=newsArrayList.get(position);

        Glide.with(context)
                .load(news.getImage())
                .into(holder.ivImage);
        holder.tvDate.setText(news.getDate());
        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewNews;
        ImageView ivImage;
        TextView tvTitle,tvDate,tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewNews=itemView.findViewById(R.id.cr_news);
            ivImage=itemView.findViewById(R.id.iv_news);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvDescription=itemView.findViewById(R.id.tv_description);
        }
    }
}



