package com.example.salman.restaurantapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CustomerAccountActivity extends AppCompatActivity {

    List<Customer> customerList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    AccountInfoAdapter accountInfoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        EventBus.getDefault().register(this);

        recyclerView = findViewById(R.id.customerAccountRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        accountInfoAdapter = new AccountInfoAdapter(CustomerAccountActivity.this, customerList);
        recyclerView.setAdapter(accountInfoAdapter);


    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AccountInfoEvent accountInfoEvent) {
        customerList = accountInfoEvent.getCustomers();
    }
}
