package com.example.salman.restaurantapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Salman on 5/31/2018.
 */

public interface ApiInterface {

    /**
     * Get All Restaurants from database through this route via GET REQUEST
     * Response will be in List
     */

    @GET("api/restaurants")
    Call<List<Restaurant>> getRestaurants();


    @GET("api/restaurant/categories")
    Call<List<RestaurantCategories>> getRestaurantCategories();

    /**
     * Pass the id of Restaurant to get the Menu Categories of that Restaurant via Get Request
     * with this route
     */

    @GET("api/restaurant/{id}/categories")
    Call<List<RestaurantCategories>> getRestaurantMenuCategories(@Path("id") int id);

    /**
     * Get Products of restaurant categories, Pass category ID and Restaurant ID to show
     * products of a specific Restaurant.
     */

    @GET("api/category/{category_id}/product/{restaurant_id}")
    Call<List<GetMenuProducts>> getMenuProducts(@Path("category_id") int category_id, @Path("restaurant_id") int restaurant_id);
    //can use @ Field as well

    /**
     * Get CartItems Table with Get Request
     * Every Restaurant will have a seperate cart maintained
     * Restaurant ID being passed as parameter to Get Route to get cart of that specific restaurant
     */

    @GET("api/cartitems/{id}")
    Call<List<Cart>> showCart(@Path("id") int id);


    /**
     * Post Route to Save the items added to Cart to Database
     **/


    @POST("api/cartitems")
    @FormUrlEncoded
    Call<Cart> addToCart(@Field("ProductID") int ProductID,
                         @Field("ProductName") String ProductName,
                         @Field("ProductPrice") int ProductPrice,
                         @Field("quantity") int quantity,
                         @Field("RestaurantID") int RestaurantID
    );

    @PUT("api/cartitems/{id}")
    Call<Cart> updateCart(@Path("id") int id, @Body Cart cart);

    @DELETE("api/cartitems/{id}")
    Call<Cart> deleteItemFromCart(@Path("id") int id);

}
