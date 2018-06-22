package com.example.salman.restaurantapplication;

import android.content.Entity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    Button btngoto;
    EditText inputEmail;
    EditText inputPassword;
    Button btnSignUp;
    Button Login;

    String getEmail;
    String getPassword;

    List<Customer> customerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.etInputEmail);
        inputPassword = findViewById(R.id.etInputPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        Login = findViewById(R.id.btnLogin);


        btngoto = findViewById(R.id.buttonGoTo);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail = inputEmail.getText().toString().trim();
                getPassword = inputPassword.getText().toString().trim();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.3:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<List<Customer>> listCall = apiInterface.postLoginData(getEmail, getPassword);

                listCall.enqueue(new Callback<List<Customer>>() {
                    @Override
                    public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        Log.d(TAG, "onResponse: " + response.body());


                    }

                    @Override
                    public void onFailure(Call<List<Customer>> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
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
        });
    }
}
