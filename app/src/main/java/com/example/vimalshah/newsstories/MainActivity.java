package com.example.vimalshah.newsstories;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final News[] news = new News[] {
                new News("vimal","shah"),
                new News("vimal","shah"),
                new News("vimal","shah"),
                new News("vimal","shah"),
                new News("vimal","shah"),
        };
        NewsListAdapter newsListAdapter = new NewsListAdapter(news);*/
        final RecyclerView newsView = (RecyclerView) findViewById(R.id.newsRecyclerView);
        final ImageView imageView = (ImageView) findViewById(R.id.image);

        newsView.setLayoutManager(new LinearLayoutManager(this));
        //newsView.setAdapter(newsListAdapter);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.mocky.io/v2/5c30477c3000005200e77a01", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("vimal", "response : " + response);
                try {
                    /*JSONArray array = new JSONArray("articles");
                    for(int i=0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Log.d("vimal", "value : " + jsonObject.toString());
                    }*/
                    JSONObject jsonObject = new JSONObject(response);
                    //JSONObject articles = jsonObject.getJSONObject("status");

                    String articles = jsonObject.getString("articles");
                    Log.d("vimal", "articles : " + articles);

                    JSONArray sources = new JSONArray(articles);
                    ArrayList<News> newsList = new ArrayList<>(sources.length());
                    for(int i=0; i< sources.length(); i++) {
                        JSONObject source = sources.getJSONObject(i);
                        final News news = new News();
                        Log.d("vimal", "source : " + source.getString("urlToImage"));
                        news.setTitle(source.getString("title"));
                        news.setDescription(source.getString("description"));
                        ImageRequest imageRequest = new ImageRequest(source.getString("urlToImage"), new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                Log.d("vimal", "bitmap : " + response);
                                news.setBitmap(response);
                                imageView.setImageBitmap(response);
                            }
                        },0 ,0 ,null, null);
                        requestQueue.add(imageRequest);
                        newsList.add(news);
                    }
                    NewsListAdapter newsListAdapter = new NewsListAdapter(newsList);
                    newsView.setAdapter(newsListAdapter);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vimal", "error : " + error.getMessage());
            }
        });
        requestQueue.add(stringRequest);

    }
}
