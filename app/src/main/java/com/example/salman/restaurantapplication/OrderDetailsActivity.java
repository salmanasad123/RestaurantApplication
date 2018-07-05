package com.example.salman.restaurantapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";


    Integer CustomerIDfromSharedPreference;
    List<OrderHistory> orderHistoryList;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrderDetailsAdapter orderDetailsAdapter;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        CustomerIDfromSharedPreference = preferences.getInt("customerID", 0);

        recyclerView = findViewById(R.id.orderDetailsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = RetrofitClient.getClient();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<OrderHistory>> listCall = apiInterface.getOrderHistory(CustomerIDfromSharedPreference);

        listCall.enqueue(new Callback<List<OrderHistory>>() {
            @Override
            public void onResponse(Call<List<OrderHistory>> call, Response<List<OrderHistory>> response) {

                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                orderHistoryList = response.body();
                orderDetailsAdapter = new OrderDetailsAdapter(OrderDetailsActivity.this, orderHistoryList);
                recyclerView.setAdapter(orderDetailsAdapter);


            }

            @Override
            public void onFailure(Call<List<OrderHistory>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }


}
