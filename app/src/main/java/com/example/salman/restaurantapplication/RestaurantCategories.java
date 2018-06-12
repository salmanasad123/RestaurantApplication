package com.example.salman.restaurantapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Salman on 6/2/2018.
 */
public class RestaurantCategories {

    @SerializedName("RestaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("RestaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("RestaurantAddress")
    @Expose
    private String restaurantAddress;
    @SerializedName("RestaurantPhone")
    @Expose
    private Integer restaurantPhone;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Rating")
    @Expose
    private Integer rating;
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public Integer getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(Integer restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}

