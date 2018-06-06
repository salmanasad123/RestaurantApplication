package com.example.salman.restaurantapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Salman on 6/3/2018.
 */

public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.MenuCategoriesViewHolder> {

    Context menuCategories;
    List<RestaurantCategories> restaurantCategories;

    public MenuCategoriesAdapter(Context applicationContext, List<RestaurantCategories> restaurantCategoriesList) {
        this.menuCategories = applicationContext;
        this.restaurantCategories = restaurantCategoriesList;

    }

    @Override
    public MenuCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_restaurant_categories_single_row, parent, false);
        return new MenuCategoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuCategoriesViewHolder holder, int position) {
        RestaurantCategories categories = restaurantCategories.get(position);
        holder.textView.setText(categories.getCategoryName());
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
}
