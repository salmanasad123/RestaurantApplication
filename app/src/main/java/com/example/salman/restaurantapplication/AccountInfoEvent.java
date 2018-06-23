package com.example.salman.restaurantapplication;

import android.util.Log;

import java.util.List;

/**
 * Created by Salman on 6/23/2018.
 */

public class AccountInfoEvent {

    private static final String TAG = "MTAG";
    private List<Customer> customers;

    public AccountInfoEvent(List<Customer> customers) {
        this.customers = customers;
        Log.d(TAG, "AccountInfoEvent: " + customers);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
