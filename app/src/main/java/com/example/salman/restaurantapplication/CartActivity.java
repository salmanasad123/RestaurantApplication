package com.example.salman.restaurantapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

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
    double Total;
    RecyclerView cartRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;
    List<Cart> cartList;
    TextView cartTotalAmount;
    TextView cartSubTotalAmount;
    TextView cartTaxAmount;
    CartAdapter cartAdapter;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        cartRecyclerView = findViewById(R.id.CartRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(cartRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        cartRecyclerView.addItemDecoration(dividerItemDecoration);

        cartSubTotalAmount = findViewById(R.id.CartSubTotalAmount);
        cartTaxAmount = findViewById(R.id.CartTaxAmount);
        cartTotalAmount = findViewById(R.id.CartTotalAmount);

        EventBus.getDefault().register(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6:8000")
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onevent(CartTotalEvent cartTotalEvent) {

        Total = cartTotalEvent.getTotal();

        if (Total >= 0) {
            cartSubTotalAmount.setText(Total + "");
            cartTaxAmount.setText("17%");
            Total += Total * 0.17;
            cartTotalAmount.setText(Total + "");
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        super.onBackPressed();

    }
}

