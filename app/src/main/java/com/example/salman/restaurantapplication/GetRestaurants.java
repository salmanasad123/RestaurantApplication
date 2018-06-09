package com.example.salman.restaurantapplication;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRestaurants extends AppCompatActivity {

    private static final String TAG = "MTAG";
    List<Restaurant> restaurants;
    Gson gson;
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_restaurants);


        //RecyclerView
        recyclerView = findViewById(R.id.showRestaurants);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //ToolBar
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Restaurants");


        // RETROFIT INSTANCE
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        final Call<List<Restaurant>> restaurantList = apiInterface.getRestaurants();
        restaurantList.enqueue(new Callback<List<Restaurant>>() {

            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                restaurants = response.body();


                /*
                Collections.sort(restaurants, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant restaurant, Restaurant t1) {
                        return restaurant.getRating() > t1.getRating() ? -1 : (restaurant.getRating() < t1.getRating()) ? 1 : 0;
                    }
                });

                */
                RestaurantAdapter restaurantAdapter = new RestaurantAdapter(GetRestaurants.this, restaurants);
                recyclerView.setAdapter(restaurantAdapter);

            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TOOLBAR BUTTON
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SortActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }


}

