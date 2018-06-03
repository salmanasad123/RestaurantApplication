package com.example.salman.restaurantapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salman on 6/3/2018.
 */

public class RestaurantCategoriesAdapter extends RecyclerView.Adapter<RestaurantCategoriesAdapter.RestaurantCategoriesViewHolder> {

    private static final String TAG = "MTAG";
    List<RestaurantCategories> restaurantCategory;
    GetRestaurantMenuCategories categories;


    public RestaurantCategoriesAdapter(GetRestaurantMenuCategories getRestaurantMenuCategories, List<RestaurantCategories> restaurantMenuCategories) {
        this.restaurantCategory = restaurantMenuCategories;
        this.categories = getRestaurantMenuCategories;

    }

    @Override
    public RestaurantCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.get_restaurant_categories_single_row, parent, false);
        return new RestaurantCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantCategoriesViewHolder holder, int position) {

        final RestaurantCategories restaurantCategories = restaurantCategory.get(position);
        holder.txtView.setText(restaurantCategories.getCategoryName());
        final RestaurantCategories restaurantCategories1 = restaurantCategory.get(++position);
        holder.textView.setText(restaurantCategories1.getCategoryName());





        //this should be inserted in between two textviews
        //final RestaurantCategories restaurantCategories1 = restaurantCategory.get(++position);
    }

    @Override
    public int getItemCount() {
        return restaurantCategory.size();
    }

    public class RestaurantCategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;
        TextView textView;

        public RestaurantCategoriesViewHolder(View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.showCategories);
            textView = itemView.findViewById(R.id.showCategories2);
        }
    }
}
