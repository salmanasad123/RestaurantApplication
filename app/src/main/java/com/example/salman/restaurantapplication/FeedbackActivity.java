package com.example.salman.restaurantapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView txtRatingValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        txtRatingValue = findViewById(R.id.textRatingValue);
        ratingBar = findViewById(R.id.feedbackRating);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));
            }
        });
    }
}
