package com.example.salman.restaurantapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    // Get Products of restaurant categories

    @GET("api/category/{category_id}/product/{restaurant_id}")
    Call<List<GetMenuProducts>> getMenuProducts(@Path("category_id") int category_id, @Path("restaurant_id") int restaurant_id);
    //can use @ Field as well

    // Post Product to Database CartItems Table


    @GET("api/cartitems")
    Call<List<Cart>> showCart();

    @POST("api/cartitems")
    @FormUrlEncoded
    Call<Cart> addToCart(@Field("ProductID") String ProductID,
                         @Field("ProductName") String ProductName,
                         @Field("ProductPrice") String ProductPrice,
                         @Field("quantity") String quantity
    );


}
