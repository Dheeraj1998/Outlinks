package com.example.inshortsapp.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.inshortsapp.R;
import com.example.inshortsapp.app.other.NewsItemAdapter;
import com.example.inshortsapp.app.other.news_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // API URL variable
    private String API_URL = "http://starlord.hackerearth.com/newsjson";

    // Variables for the Recycler View
    private RecyclerView recyclerView;
    private ArrayList newsItems;
    private NewsItemAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private int check_count = 0;

    // Function for converting the milli-seconds to the valid date
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Displaying the progress dialog while the content is fetched
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Collecting the news from everywhere...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //region Module for initialising the RecyclerView and displaying the values in a CardView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        newsItems = new ArrayList<>();
        adapter = new NewsItemAdapter(this, newsItems);

        // Setting up the RecyclerView for getting the data
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //endregion

        //region Module for handling the setting up and getting the online content
        // Creating the request object
        JsonArrayRequest news_JSONObjectRequest = new JsonArrayRequest(Request.Method.GET,
                API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i("custom", response.toString());
                            populateNews(response);
                        } catch (Exception error) {
                            Log.i("custom", error.toString());
                        }
                        // Hide the progress dialog
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("custom", error.getMessage());

                // Hide the progress dialog
                progressDialog.hide();
            }
        });

        // Adding the request object to the Volley queue
        Volley.newRequestQueue(getApplicationContext()).add(news_JSONObjectRequest);
        //endregion

        //region Module for setting up the custom Action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.mainactivity_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageButton sort_button = (ImageButton) view.findViewById(R.id.action_bar_sort_button);

        sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_count % 2 == 0) {
                    mLayoutManager.setReverseLayout(true);
                    mLayoutManager.setStackFromEnd(true);
                } else {
                    mLayoutManager.setReverseLayout(false);
                    mLayoutManager.setStackFromEnd(false);
                }

                check_count++;
            }
        });

        ImageButton portfolio_button = (ImageButton) view.findViewById(R.id.portfolio_button);

        portfolio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this, MyPortfolio.class);
                startActivity(temp);
            }
        });
        //endregion


    }
    //endregion

    //region Function for populating the RecyclerView
    private void populateNews(JSONArray all_news) {
        newsItems.clear();

        for (int index = 0; index < all_news.length(); index++) {
            try {
                JSONObject single_news = (JSONObject) all_news.get(index);

                // Extraction of data from the JSONObject
                String id = single_news.getString("ID");
                String title = single_news.getString("TITLE");
                String url = single_news.getString("URL");
                String publisher = single_news.getString("PUBLISHER");
                String category = single_news.getString("CATEGORY");
                String hostname = single_news.getString("HOSTNAME");
                String timestamp = single_news.getString("TIMESTAMP");

                timestamp = getDate(Long.parseLong(timestamp), "dd/MM/yyyy hh:mm");

                // Add an item to the newsItems
                news_item single_row_news = new news_item(id, title, url, publisher, category, hostname, timestamp);
                newsItems.add(single_row_news);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
