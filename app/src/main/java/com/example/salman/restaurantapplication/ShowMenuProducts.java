package com.example.salman.restaurantapplication;

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
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowMenuProducts extends AppCompatActivity {

    private static final String TAG = "MTAG";
    public int getCategoryiD;
    public int RestaurantIDfromEventBus;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_products);

        recyclerView = findViewById(R.id.showProducts);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getCategoryiD = getIntent().getIntExtra("myCategoryID", 0);

        EventBus.getDefault().register(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<GetMenuProducts>> listCall = apiInterface.getMenuProducts(getCategoryiD, RestaurantIDfromEventBus);
        listCall.enqueue(new Callback<List<GetMenuProducts>>() {

            @Override
            public void onResponse(Call<List<GetMenuProducts>> call, Response<List<GetMenuProducts>> response) {

                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<List<GetMenuProducts>> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GetRestaurantIDEvent getRestaurantIDEvent) {
        RestaurantIDfromEventBus = getRestaurantIDEvent.getValue();
    }
}
