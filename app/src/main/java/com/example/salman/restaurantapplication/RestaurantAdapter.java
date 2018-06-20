package com.example.salman.restaurantapplication;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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

        /**
         * Inflating how each row should appear in the recyclerView List
         * inflating single row of restaurants
         */
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, final int position) {

        final Restaurant restaurant = restaurants.get(position);

        Log.d(TAG, "onBindViewHolder: " + restaurant.getRestaurantName());

        /**
         * Setting values into textViews and Image Views
         * Image View is set with Picasso, Links of Restaurant Images are saved in Database
         */
        holder.restaurantName.setText(restaurant.getRestaurantName());
        holder.restaurantRating.setRating(restaurant.getRating());


        Picasso.with(context)
                .load(restaurants.get(position).getLink())
                .resize(250, 250)
                .into(holder.restaurantImage);


        /**
         * Recycler View Item Clicked
         * When A Restaurant will be clicked its ID will be passed via intent to other activity
         * because ID will be required to show the Categories of only that Restaurant which was clicked
         */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Event Bus to Post the ID of restaurant which was clicked to Show its Products and
                 * its items in the cart. Restaurant ID will be Required by Cart Activity and
                 * show Products Activity
                 * Any activity or method or class that has subscribed/registered to this event will receive the event
                 * in on Subscribe method
                 */
                GetRestaurantIDEvent getRestaurantIDEvent = new GetRestaurantIDEvent(restaurant.getRestaurantID());
                EventBus.getDefault().postSticky(getRestaurantIDEvent);

                // String restaurantGson = gson.toJson(restaurants.get(position));
                Intent intent = new Intent(context, GetRestaurantMenuCategories.class);
                intent.putExtra("myObjectString", restaurant.getRestaurantID());
                intent.putExtra("myImageString", restaurant.getLink());// passing restaurant id to other activity
                context.startActivity(intent);


            }
        });


        gson = new Gson();
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRestaurantIDEvent getRestaurantIDEvent = new GetRestaurantIDEvent(restaurant.getRestaurantID());
                EventBus.getDefault().postSticky(getRestaurantIDEvent);
                String str = gson.toJson(restaurants.get(position));
                Intent intent = new Intent(context, RestaurantDetailsActivity.class);
                intent.putExtra("MyObjString", str);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void filterList(ArrayList<Restaurant> filteredList) {
        restaurants = filteredList;
        notifyDataSetChanged();
    }

    /**
     * View Holder Class to Hold The Views
     * This will help to avoid FindViewByID operation every time
     */
    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName;
        RatingBar restaurantRating;
        CardView cardView;
        ImageButton imageButton;


        public RestaurantViewHolder(View itemView) {

            super(itemView);
            cardView = itemView.findViewById(R.id.restaurantCardView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantRating = itemView.findViewById(R.id.restaurant_rating);
            imageButton = itemView.findViewById(R.id.restaurantInfoButton);

        }
    }
}
