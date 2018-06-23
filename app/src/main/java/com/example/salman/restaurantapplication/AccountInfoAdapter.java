package com.example.salman.restaurantapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salman on 6/23/2018.
 */

public class AccountInfoAdapter extends RecyclerView.Adapter<AccountInfoAdapter.AccountInfoViewHolder> {

    List<Customer> customers;
    CustomerAccountActivity activity;
    View view;


    public AccountInfoAdapter(CustomerAccountActivity customerAccountActivity, List<Customer> customerList) {
        this.activity = customerAccountActivity;
        this.customers = customerList;
    }

    @Override
    public AccountInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_account_single_row, parent, false);
        return new AccountInfoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AccountInfoViewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.textView.setText(customer.getName());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class AccountInfoViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public AccountInfoViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.customerName);
        }
    }
}
