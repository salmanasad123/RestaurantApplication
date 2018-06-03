package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SortActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";
    List<Restaurant> restaurants;
    Button b1;
    Gson gson;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        b1 = findViewById(R.id.buttonSort);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.11:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                final Call<List<Restaurant>> restaurantList = apiInterface.getRestaurants();
                restaurantList.enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        restaurants = response.body();
                        Collections.sort(restaurants, new Comparator<Restaurant>() {
                            @Override
                            public int compare(Restaurant restaurant, Restaurant t1) {
                                return restaurant.getRating() > t1.getRating() ? -1 : (restaurant.getRating() < t1.getRating()) ? 1 : 0;
                            }
                        });

                        Intent intent = new Intent(SortActivity.this, MainActivity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                    }
                });


            }
        });
    }
}
