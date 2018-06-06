package com.example.salman.restaurantapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by Salman on 6/3/2018.
 */

public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.MenuCategoriesViewHolder> {

    private static final String TAG = "MTAG";
    public int restaurantIDfromEventBus;
    int categoryID;


    Context menuCategories;
    List<RestaurantCategories> restaurantCategories;


    public MenuCategoriesAdapter(Context applicationContext, List<RestaurantCategories> restaurantCategoriesList) {
        this.menuCategories = applicationContext;
        this.restaurantCategories = restaurantCategoriesList;
        EventBus.getDefault().register(this);
    }

    @Override
    public MenuCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_restaurant_categories_single_row, parent, false);
        return new MenuCategoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuCategoriesViewHolder holder, final int position) {
        final RestaurantCategories categories = restaurantCategories.get(position);
        holder.textView.setText(categories.getCategoryName());


        ////////////////////////////////////////////////////////////////////////////////////////////////////

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryID = categories.getCategoryID();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);


                Call<List<GetMenuProducts>> listCall = apiInterface.getMenuProducts(categoryID, restaurantIDfromEventBus);
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
        });


    }

    @Override
    public int getItemCount() {
        return restaurantCategories.size();
    }

    public class MenuCategoriesViewHolder extends RecyclerView.ViewHolder {


        TextView textView;

        public MenuCategoriesViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.showCategories);


        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GetRestaurantIDEvent getRestaurantIDEvent) {

        restaurantIDfromEventBus = getRestaurantIDEvent.getValue();
        Log.d(TAG, "onEvent: Chal gayaaa" + restaurantIDfromEventBus);
    }
}

