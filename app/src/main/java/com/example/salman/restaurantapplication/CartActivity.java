package com.example.salman.restaurantapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    public int RestaurantIDFromEventBus;

    RecyclerView cartRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;
    List<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.CartRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(cartRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        cartRecyclerView.addItemDecoration(dividerItemDecoration);


        EventBus.getDefault().register(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Cart>> listCall = apiInterface.showCart(RestaurantIDFromEventBus);

        listCall.enqueue(new Callback<List<Cart>>() {

            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                cartList = response.body();
                CartAdapter cartAdapter = new CartAdapter(CartActivity.this, cartList);
                cartRecyclerView.setAdapter(cartAdapter);

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

            }
        });


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GetRestaurantIDEvent event) {
        RestaurantIDFromEventBus = event.getValue();
    }

}
