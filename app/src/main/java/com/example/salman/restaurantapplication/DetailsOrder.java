package com.example.salman.restaurantapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Salman on 7/5/2018.
 */

public class DetailsOrder {


        @SerializedName("ProductName")
        @Expose
        private String productName;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("OrderType")
        @Expose
        private String orderType;
        @SerializedName("TotalAmount")
        @Expose
        private String totalAmount;
        @SerializedName("CustomerID")
        @Expose
        private String customerID;
        @SerializedName("RestaurantID")
        @Expose
        private String restaurantID;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("OrderDetailsID")
        @Expose
        private Integer orderDetailsID;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public String getRestaurantID() {
            return restaurantID;
        }

        public void setRestaurantID(String restaurantID) {
            this.restaurantID = restaurantID;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getOrderDetailsID() {
            return orderDetailsID;
        }

        public void setOrderDetailsID(Integer orderDetailsID) {
            this.orderDetailsID = orderDetailsID;
        }

    }

