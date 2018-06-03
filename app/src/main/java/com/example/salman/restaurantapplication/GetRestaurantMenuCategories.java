package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRestaurantMenuCategories extends AppCompatActivity {

    private static final String TAG = "MTAG";
    RecyclerView showCategoriesRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<RestaurantCategories> restaurantMenuCategories;
    int getRestaurantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_restaurant_menu_categories);
        showCategoriesRecyclerView = findViewById(R.id.showRestaurantCategories);
        layoutManager = new LinearLayoutManager(this);
        showCategoriesRecyclerView.setLayoutManager(layoutManager);

        getRestaurantID = getIntent().getIntExtra("myObjectString", 0);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        final Call<List<RestaurantCategories>> restaurantCategoriesCall = apiInterface.getRestaurantMenuCategories(getRestaurantID);
        restaurantCategoriesCall.enqueue(new Callback<List<RestaurantCategories>>() {
            @Override
            public void onResponse(Call<List<RestaurantCategories>> call, Response<List<RestaurantCategories>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                restaurantMenuCategories = response.body();

                RestaurantCategoriesAdapter RCA =
                        new RestaurantCategoriesAdapter(GetRestaurantMenuCategories.this, restaurantMenuCategories);

                showCategoriesRecyclerView.setAdapter(RCA);
            }

            @Override
            public void onFailure(Call<List<RestaurantCategories>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }
}
