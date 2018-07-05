package com.example.salman.restaurantapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salman on 7/5/2018.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    private static final String TAG = "MTAG";
    List<OrderHistory> orderHistories;
    OrderDetailsActivity activity;

    public OrderDetailsAdapter(OrderDetailsActivity orderDetailsActivity, List<OrderHistory> orderHistoryList) {
        this.activity = orderDetailsActivity;
        this.orderHistories = orderHistoryList;
    }


    @Override
    public OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_single_row, parent, false);
        return new OrderDetailsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(OrderDetailsViewHolder holder, int position) {
        OrderHistory orderHistory = orderHistories.get(position);
        holder.tvRestaurantName.setText(orderHistory.getRestaurantName());

    }

    @Override
    public int getItemCount() {
        return orderHistories.size();
    }

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView tvRestaurantName;
        TextView tvRestaurantAddress;
        TextView tvOrderType;
        ImageView RestaurantImageView;


        public OrderDetailsViewHolder(View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.restaurantNameTextView);
            tvRestaurantAddress = itemView.findViewById(R.id.restaurantAddress);
            tvOrderType = itemView.findViewById(R.id.tvOrderType);
            RestaurantImageView = itemView.findViewById(R.id.restaurantImageView);
        }
    }
}
