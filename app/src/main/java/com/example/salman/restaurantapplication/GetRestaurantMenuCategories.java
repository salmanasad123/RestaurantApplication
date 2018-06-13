package com.example.salman.restaurantapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRestaurantMenuCategories extends AppCompatActivity {

    private static final String TAG = "MTAG";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<RestaurantCategories> restaurantCategoriesList;

    public int getRestaurantID;
    String getRestaurantImage;
    ImageView imageView;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_restaurant_menu_categories);

        recyclerView = findViewById(R.id.ShowCategoriesRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        imageView = findViewById(R.id.menuCategorybackgroundImage);
        cardView = findViewById(R.id.categoriesCardView);

        /**
         * Restaurant ID received from RestaurantActivity
         */

        getRestaurantImage = getIntent().getStringExtra("myImageString");
        getRestaurantID = getIntent().getIntExtra("myObjectString", 0);


        Picasso.with(GetRestaurantMenuCategories.this)
                .load(getRestaurantImage)
                .resize(1440, 1000)
                .into(imageView);

        //Event BUS///////////////////////////////////////////

        /**
         * IMPORTANT
         * Second Activity should be registered before the Bus sends the Event otherwise it will not work,
         * in such case use sticky event
         */

        // GetRestaurantIDEvent getRestaurantIDEvent = new GetRestaurantIDEvent(getRestaurantID);
        // EventBus.getDefault().postSticky(getRestaurantIDEvent);


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

                restaurantCategoriesList = response.body();


                MenuCategoriesAdapter menuCategoriesAdapter = new MenuCategoriesAdapter(GetRestaurantMenuCategories.this, restaurantCategoriesList);
                recyclerView.setAdapter(menuCategoriesAdapter);

                findViewById(R.id.loadingPanel).setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<RestaurantCategories>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }
}
