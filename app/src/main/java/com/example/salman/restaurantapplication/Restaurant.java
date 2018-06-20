package com.example.salman.restaurantapplication;

/**
 * Created by Salman on 5/31/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Restaurant Pojo Class of the Response received by hitting the url Get All restaurants
 */

public class Restaurant {
/*
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
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("FeedbackID")
    @Expose
    private Integer feedbackID;
    @SerializedName("Rating")
    @Expose
    private Integer rating;
    @SerializedName("comment")
    @Expose
    private String comment;

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

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

*/


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
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

