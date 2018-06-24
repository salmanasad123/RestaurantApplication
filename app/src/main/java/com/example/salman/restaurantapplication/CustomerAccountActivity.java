package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerAccountActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    AccountInfoAdapter accountInfoAdapter;

    Button updateButton;
    Button logoutButton;

    SharedPreferences sharedPreferences;
    Integer customerIDfromEventBus;
    List<Customer> customerList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        EventBus.getDefault().register(this);

        logoutButton = findViewById(R.id.btnLogout);
        updateButton = findViewById(R.id.btnAccountUpdate);
        recyclerView = findViewById(R.id.customerAccountRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = RetrofitClient.getClient();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);


        Call<List<Customer>> listCall = apiInterface.getProfile(customerIDfromEventBus);
        listCall.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                customerList1 = response.body();

                accountInfoAdapter = new AccountInfoAdapter(CustomerAccountActivity.this, customerList1);
                recyclerView.setAdapter(accountInfoAdapter);
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


        //  accountInfoAdapter = new AccountInfoAdapter(CustomerAccountActivity.this, customerList);
        // recyclerView.setAdapter(accountInfoAdapter);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerAccountActivity.this, ProfileUpdateActivity.class);
                startActivity(intent);
            }
        });


    }


        @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
        public void Event(AccountIDEvent accountIDEvent) {
            customerIDfromEventBus = accountIDEvent.getId();
            Log.d(TAG, "Event: ID Received" + customerIDfromEventBus);
        }
    /*
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AccountInfoEvent accountInfoEvent) {
        customerList = accountInfoEvent.getCustomers();

    }
    */
}
