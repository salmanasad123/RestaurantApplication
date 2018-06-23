package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    Gson gson;
    String target;
    Restaurant restaurant;
    int RestaurantIDfromIntent;

    TextView restaurantName;
    TextView restaurantAddress;
    TextView restaurantPhone;
    RatingBar restaurantRatingBar;
    Button OrderFood;
    Button GiveFeedback;
    ImageButton restaurantFb;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CardView cardView;
    List<Feedback> feedbackList;

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
        GiveFeedback = findViewById(R.id.btnGiveFeedback);

        recyclerView = findViewById(R.id.feedbacksRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cardView = findViewById(R.id.FeedbackCardView);

        RestaurantIDfromIntent = getIntent().getIntExtra("MyObjectID", 0);


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

        GiveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetailsActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });


        Retrofit retrofit = RetrofitClient.getClient();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Feedback>> listCall = apiInterface.getFeedback(RestaurantIDfromIntent);

        listCall.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {

                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                feedbackList = response.body();

                FeedbackAdapter feedbackAdapter = new FeedbackAdapter(RestaurantDetailsActivity.this, feedbackList);
                recyclerView.setAdapter(feedbackAdapter);
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }


}
