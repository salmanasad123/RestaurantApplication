package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

public class RestaurantDetailsActivity extends AppCompatActivity {

    Gson gson;
    String target;
    Restaurant restaurant;

    TextView restaurantName;
    TextView restaurantAddress;
    TextView restaurantPhone;
    RatingBar restaurantRatingBar;
    Button OrderFood;
    ImageButton restaurantFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        restaurantName = findViewById(R.id.tvRestaurantName);
        restaurantAddress = findViewById(R.id.tvRestaurantAddress);
        restaurantPhone = findViewById(R.id.tvRestaurantPhone);
        restaurantRatingBar = findViewById(R.id.RestaurantratingBar);
        OrderFood = findViewById(R.id.Orderbutton);
        restaurantFb = findViewById(R.id.btnFacebook);

        /**
         *
         * Getting Object from other activity and converting it back to Gson
         */
        gson = new Gson();
        target = getIntent().getStringExtra("MyObjString");
        restaurant = gson.fromJson(target, Restaurant.class);

        /**
         * Setting Values of TextViews, Rating Bars etc
         */
        restaurantName.setText(restaurant.getRestaurantName());
        restaurantAddress.setText(restaurant.getRestaurantAddress());
        restaurantPhone.setText(restaurant.getRestaurantPhone().toString());
        restaurantRatingBar.setRating(restaurant.getRating());

        OrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RestaurantDetailsActivity.this, GetRestaurantMenuCategories.class);
                startActivity(intent);
            }
        });
        restaurantFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Gourmet-Restaurant-Sweets-470988696345213/"));
                startActivity(browserIntent);
            }
        });


    }
}
