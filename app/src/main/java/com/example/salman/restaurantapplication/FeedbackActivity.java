package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackActivity extends AppCompatActivity {


    private static final String TAG = "MTAG";
    int RestaurantID;
    RatingBar ratingBar;
    EditText comment;
    Button sendFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EventBus.getDefault().register(this);
        ratingBar = findViewById(R.id.feedbackRating);
        sendFeedback = findViewById(R.id.btnFeedbackSumbit);
        comment = findViewById(R.id.inputFeedback);


        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FeedbackActivity.this, "Feedback Sent Successfully", Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.6:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Feedback> feedbackCall = apiInterface.sendFeedback(((int) ratingBar.getRating()), comment.getText().toString(), RestaurantID);

                feedbackCall.enqueue(new Callback<Feedback>() {
                    @Override
                    public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                    }



                    @Override
                    public void onFailure(Call<Feedback> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });

                Intent intent = new Intent(FeedbackActivity.this,GetRestaurants.class);
                startActivity(intent);
            }

        });


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GetRestaurantIDEvent idEvent) {
        RestaurantID = idEvent.getValue();

    }
}
