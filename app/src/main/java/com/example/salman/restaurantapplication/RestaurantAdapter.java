package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by Salman on 5/31/2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private static final String TAG = "MTAG";
    View view;
    List<Restaurant> restaurants;
    GetRestaurants context;
    Gson gson;


    public RestaurantAdapter(GetRestaurants context, List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.context = context;

    }


    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, final int position) {
        final Restaurant restaurant = restaurants.get(position);

        Log.d(TAG, "onBindViewHolder: " + restaurant.getRestaurantName());

        holder.restaurantName.setText(restaurant.getRestaurantName());
        holder.restaurantRating.setRating(restaurant.getRating());

        //Convert Each Object to Json to send to another Activity
        // Creating Gson Object
        gson = new Gson();


        // Recycler View Item Clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // String restaurantGson = gson.toJson(restaurants.get(position));
                Intent intent = new Intent(context, GetRestaurantMenuCategories.class);
                intent.putExtra("myObjectString", restaurant.getRestaurantID());  // passing restaurant id to other activity
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName;
        RatingBar restaurantRating;
        CardView cardView;


        public RestaurantViewHolder(View itemView) {

            super(itemView);
            cardView = itemView.findViewById(R.id.restaurantCardView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantRating = itemView.findViewById(R.id.restaurant_rating);

        }
    }
}
