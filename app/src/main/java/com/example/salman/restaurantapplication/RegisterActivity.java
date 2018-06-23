package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";


    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etAddress;
    EditText etCity;
    EditText etPassword;
    Button btnRegister;
    android.support.v7.widget.Toolbar toolbar;

    //Variables Passed to Register Form via Post Request

    String CustomerName;
    String CustomerEmail;
    Editable CustomerPhone;
    String CustomerAddress;
    String CustomerCity;
    String CustomerPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.registerActivitToolbar);


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().trim().equals("")) {
                    etName.setError("Name is Required");
                } else {
                    CustomerName = etName.getText().toString();
                }
                if (etEmail.getText().toString().trim().equals("")) {
                    etEmail.setError("Email is Required");
                } else {
                    CustomerEmail = etEmail.getText().toString();
                }
                if (etPhone.getText().toString().equals("")) {
                    etPhone.setError("Phone is Required");
                } else {
                    CustomerPhone = etPhone.getText();
                }
                if (etAddress.getText().toString().trim().equals("")) {
                    etAddress.setError("Address is Required");
                } else {
                    CustomerAddress = etAddress.getText().toString();
                }
                if (etCity.getText().toString().trim().equals("")) {
                    etCity.setError("City is Required");
                } else {
                    CustomerCity = etCity.getText().toString();
                }
                if (etPassword.getText().toString().trim().equals("")) {
                    etPassword.setError("Password is Required");
                } else {
                    CustomerPassword = etPassword.getText().toString();
                }


                final Retrofit retrofit = RetrofitClient.getClient();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);


                Call<Customer> customerCall = apiInterface.registerCustomer(CustomerName, CustomerEmail,
                        (CustomerPhone), CustomerAddress, CustomerCity, CustomerPassword);

                customerCall.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");


                        //Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful()) {

                            Snackbar.make(findViewById(android.R.id.content), "Successfully Registered", Snackbar.LENGTH_LONG)
                                    .show();

                            Intent intent = new Intent(RegisterActivity.this, GetRestaurants.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Failed To Register", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

                    }
                });


            }
        });


    }
}
