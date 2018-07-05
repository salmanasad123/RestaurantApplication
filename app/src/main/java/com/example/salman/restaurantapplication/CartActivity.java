package com.example.salman.restaurantapplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
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
    Button placeOrder;
    Toolbar toolbar;
    CartAdapter cartAdapter;

    List<Cart> OrderDetailsList;

    SharedPreferences preferences;
    Integer CustomerIDfromSharedPreference;

    AlertDialog alertDialog;
    CharSequence[] values = {" Cash On Delivery ", " Dine In ", " Take Away "};
    String choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        CustomerIDfromSharedPreference = preferences.getInt("customerID", 0);


        cartRecyclerView = findViewById(R.id.CartRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(cartRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        cartRecyclerView.addItemDecoration(dividerItemDecoration);


        cartSubTotalAmount = findViewById(R.id.CartSubTotalAmount);
        cartTaxAmount = findViewById(R.id.CartTaxAmount);
        cartTotalAmount = findViewById(R.id.CartTotalAmount);
        placeOrder = findViewById(R.id.btnPlaceOrder);

        toolbar = findViewById(R.id.cartActivityToolbar);
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        Retrofit retrofit = RetrofitClient.getClient();


        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Cart>> listCall = apiInterface.getCart(RestaurantIDFromEventBus, CustomerIDfromSharedPreference);

        listCall.enqueue(new Callback<List<Cart>>() {

            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                cartList = response.body();
                cartAdapter = new CartAdapter(CartActivity.this, cartList);
                cartRecyclerView.setAdapter(cartAdapter);


            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

            }
        });


        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Select Your Choice");
                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        switch (item) {
                            case 0:
                                Toast.makeText(CartActivity.this, "Cash on Delivery", Toast.LENGTH_SHORT).show();
                                choice = "Cash on Delivery";
                                break;

                            case 1:
                                Toast.makeText(CartActivity.this, "Dine In", Toast.LENGTH_SHORT).show();
                                choice = "Dine In";
                                break;


                            case 2:
                                Toast.makeText(CartActivity.this, "Take Away", Toast.LENGTH_SHORT).show();
                                choice = "Take Away";
                                break;
                        }
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();


                Retrofit retrofit = RetrofitClient.getClient();
                final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<List<Cart>> listCall = apiInterface.getCart(RestaurantIDFromEventBus, CustomerIDfromSharedPreference);

                listCall.enqueue(new Callback<List<Cart>>() {
                    @Override
                    public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {

                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        OrderDetailsList = response.body();


                        for (int i = 0; i < OrderDetailsList.size(); i++) {

                            Retrofit retrofit2 = RetrofitClient.getClient();
                            ApiInterface apiInterface2 = retrofit2.create(ApiInterface.class);
                            Call<DetailsOrder> orderCall = apiInterface2.getOrderDetails(OrderDetailsList.get(i).getProductName(),
                                    OrderDetailsList.get(i).getQuantity(), choice, (int) Total, CustomerIDfromSharedPreference, RestaurantIDFromEventBus);

                            orderCall.enqueue(new Callback<DetailsOrder>() {
                                @Override
                                public void onResponse(Call<DetailsOrder> call, Response<DetailsOrder> response) {

                                    Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                                }

                                @Override
                                public void onFailure(Call<DetailsOrder> call, Throwable t) {

                                    Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Cart>> call, Throwable t) {

                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });

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

