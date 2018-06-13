package com.example.salman.restaurantapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Salman on 6/9/2018.
 */

public class Cart {

    @SerializedName("CartItemID")
    @Expose
    private Integer cartItemID;
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("RestaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Cart(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(Integer cartItemID) {
        this.cartItemID = cartItemID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
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

}





  /*
    @SerializedName("CartItemID")
    @Expose
    private Integer cartItemID;
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /*
    public Cart(Integer cartItemID, Integer productID, String productName, Integer productPrice, Integer quantity) {
        this.cartItemID = cartItemID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
*/
/*
    public Cart(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(Integer cartItemID) {
        this.cartItemID = cartItemID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

*/

