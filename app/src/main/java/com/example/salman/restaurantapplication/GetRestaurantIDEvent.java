package com.example.salman.restaurantapplication;

import android.util.Log;

/**
 * Created by Salman on 6/6/2018.
 */

public class GetRestaurantIDEvent {
    public int value;
    private static final String TAG = "MTAG";

    public GetRestaurantIDEvent(int value) {
        this.value = value;

        Log.d(TAG, "GetRestaurantIDEvent: " + value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
