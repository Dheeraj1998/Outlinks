package com.example.inshortsapp.app.other;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inshortsapp.R;
import com.example.inshortsapp.app.WebBrowser;

import java.util.List;

/**
 * Created by dheeraj on 14/09/17.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.MyViewHolder> {
    private Context mContext;
    private List<news_item> news_itemList;
    private String[] background_colors = {"#1abc9c", "#2ecc71", "#3498db", "#bdc3c7", "#9b59b6", "#f1c40f", "#d35400", "#e74c3c", "#95a5a6"};

    public NewsItemAdapter(Context mContext, List<news_item> news_itemList) {
        this.mContext = mContext;
        this.news_itemList = news_itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_news_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        news_item single_news_item = news_itemList.get(position);

        //region Setting up the values in the different cards

        switch (position % background_colors.length) {
            case 0:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_zero);
                break;
            case 1:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_one);
                break;
            case 2:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_two);
                break;
            case 3:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_three);
                break;
            case 4:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_four);
                break;
            case 5:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_five);
                break;
            case 6:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_six);
                break;
            case 7:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_seven);
                break;
            case 8:
                holder.news_publisher_round.setBackgroundResource(R.drawable.circle_background_eight);
                break;
        }

        switch (single_news_item.getCategory()) {
            case "t":
                holder.news_category.setBackgroundResource(R.drawable.category_science);
                break;
            case "b":
                holder.news_category.setBackgroundResource(R.drawable.category_business);
                break;
            case "e":
                holder.news_category.setBackgroundResource(R.drawable.category_entertainment);
                break;
            case "m":
                holder.news_category.setBackgroundResource(R.drawable.category_health);
                break;
        }

        holder.news_publisher_round.setText(single_news_item.getPublisher().substring(0, 1));
        holder.news_title.setText(single_news_item.getTitle());
        holder.news_publisher_name.setText(single_news_item.getPublisher());
        holder.news_timestamp.setText(single_news_item.getTimestamp());
        //endregion
    }

    @Override
    public int getItemCount() {
        return news_itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView news_title, news_publisher_name, news_timestamp, news_publisher_round;
        public ImageView news_category;

        public MyViewHolder(View view) {
            super(view);

            news_category = (ImageView) view.findViewById(R.id.news_category);
            news_publisher_round = (TextView) view.findViewById(R.id.news_publisher_round);
            news_title = (TextView) view.findViewById(R.id.news_title);
            news_publisher_name = (TextView) view.findViewById(R.id.news_publisher_name);
            news_timestamp = (TextView) view.findViewById(R.id.news_timestamp);

            view.setOnClickListener(this);
        }

        //region Code for moving to the web-browser on clock
        @Override
        public void onClick(View v) {
            Log.i("custom", getAdapterPosition() + "");
            Intent temp = new Intent(mContext, WebBrowser.class);
            temp.putExtra("url", news_itemList.get(getAdapterPosition()).getUrl());
            mContext.startActivity(temp);
        }
    }
}
