package com.example.salman.restaurantapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class RestaurantDetailsActivity extends AppCompatActivity {

    Gson gson;
    String target;
    Restaurant restaurant;

    TextView restaurantName;
    TextView restaurantAddress;
    TextView restaurantPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        restaurantName = findViewById(R.id.tvRestaurantName);
        restaurantAddress = findViewById(R.id.tvRestaurantAddress);
        restaurantPhone = findViewById(R.id.tvRestaurantPhone);


        gson = new Gson();
        target = getIntent().getStringExtra("MyObjString");
        restaurant = gson.fromJson(target, Restaurant.class);

        restaurantName.setText(restaurant.getRestaurantName());
        restaurantAddress.setText(restaurant.getRestaurantAddress());
        restaurantPhone.setText(restaurant.getRestaurantPhone().toString());


    }
}