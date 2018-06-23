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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileUpdateActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    EditText updatePhone;
    EditText updateAddress;
    EditText updatePassword;
    Button profileUpdate;

    Integer customerIDfromEventBus;
    String phoneNumber;
    String Address;
    String Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        EventBus.getDefault().register(this);
        updatePhone = findViewById(R.id.updatePhone);
        updateAddress = findViewById(R.id.updateAddress);
        updatePassword = findViewById(R.id.updatePassword);
        profileUpdate = findViewById(R.id.btnProfileUpdate);


        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumber = updatePhone.getText().toString();
                Address = updateAddress.getText().toString();
                Password = updatePassword.getText().toString();

                Customer customer = new Customer(phoneNumber, Address, Password);


                Retrofit retrofit = RetrofitClient.getClient();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<Customer> customerCall = apiInterface.updateProfile(customerIDfromEventBus, customer);

                customerCall.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        
                        if (response.code() == 200) {
                            Snackbar.make(findViewById(android.R.id.content), "Profile Updated", Snackbar.LENGTH_LONG)
                                    .show();
                            Intent intent = new Intent(ProfileUpdateActivity.this, GetRestaurants.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Fail to Update", Snackbar.LENGTH_LONG)
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


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AccountIDEvent accountIDEvent) {
        customerIDfromEventBus = accountIDEvent.getId();
    }
}
