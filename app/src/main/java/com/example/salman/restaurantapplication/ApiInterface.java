package com.example.salman.restaurantapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Salman on 5/31/2018.
 */

public interface ApiInterface {
    @GET("api/restaurants")
    Call<List<Restaurant>> getRestaurants();

    @GET("api/restaurant/categories")
    Call<List<RestaurantCategories>> getRestaurantCategories();

    // Pass the id of Restaurant to get the Menu Categories of that Restaurant
    @GET("api/restaurant/{id}/categories")
    Call<List<RestaurantCategories>> getRestaurantMenuCategories(@Path("id") int id);
}
