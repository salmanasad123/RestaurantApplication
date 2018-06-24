package com.example.salman.restaurantapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

public class TempActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;
    Gson gson;
    List<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        textView = findViewById(R.id.textView);

        gson = new Gson();
        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String jsonText = sharedPreferences.getString("customerList", "x");



    }
}
