package com.example.salman.restaurantapplication;

/**
 * Created by Salman on 6/19/2018.
 */

public class CartTotalEvent {
    Integer total;

    public CartTotalEvent(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
