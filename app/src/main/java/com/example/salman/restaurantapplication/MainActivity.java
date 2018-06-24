package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String username;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);

        b1 = findViewById(R.id.btn_continue);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = sharedPreferences.getString("username", "x");

                if (username != "x") {
                    Intent intent = new Intent(MainActivity.this, GetRestaurants.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
