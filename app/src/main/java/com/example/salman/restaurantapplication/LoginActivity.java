package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    Button btngoto;
    Button btnSignUp;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = findViewById(R.id.btnSignUp);
        Login = findViewById(R.id.btnLogin);

        btngoto = findViewById(R.id.buttonGoTo);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.6:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<Customer> customerCall = apiInterface.getCustomer();

                customerCall.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {

                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btngoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GetRestaurants.class);
                startActivity(intent);
            }
        });


    }
}
