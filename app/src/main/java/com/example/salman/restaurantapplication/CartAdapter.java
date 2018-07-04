package com.example.salman.restaurantapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.IslamicCalendar;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salman on 6/10/2018.
 */


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = "MTAG";
    Integer counter = 1;
    Integer total = 0;


    List<Cart> carts;
    CartActivity activity;
    SharedPreferences preferences;


    public CartAdapter(CartActivity cartActivity, List<Cart> cartList) {
        this.activity = cartActivity;
        this.carts = cartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_sinlge_row, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        final Cart cart = carts.get(position);


        holder.CartProductName.setText(cart.getProductName());
        holder.CartProductPrice.setText("Rs " + cart.getProductPrice().toString());
        holder.productQty.setText(cart.getQuantity().toString());


        Integer qty = Integer.valueOf(holder.productQty.getText().toString());
        total = total + (qty * carts.get(position).getProductPrice());


        //   total = total + carts.get(position).getProductPrice();
        Log.d(TAG, "onBindViewHolder: TOTAL AMOUNT " + total);


        CartTotalEvent cartTotalEvent = new CartTotalEvent(total);
        EventBus.getDefault().post(cartTotalEvent);


        holder.cartIncrease.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {


                counter = Integer.parseInt(holder.productQty.getText().toString());
                if (counter >= 1) {
                    holder.productQty.setText("" + ++counter);
                }
                //Cart cart1 = new Cart(cart.getCartItemID(), cart.getProductID(), cart.getProductName(),cart.getProductPrice(), counter);


                total = total + cart.getProductPrice();

                EventBus.getDefault().post(new CartTotalEvent(total));


                Log.d(TAG, "onClick: " + total);


                Cart cart1 = new Cart(counter);

                Retrofit retrofit = RetrofitClient.getClient();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Cart> call = apiInterface.updateCart(cart.getCartItemID(), cart1);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });


            }
        });


        holder.cartDecrease.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                counter = Integer.parseInt(holder.productQty.getText().toString());
                if (counter > 1) {
                    holder.productQty.setText("" + --counter);

                    total = total - cart.getProductPrice();
                    EventBus.getDefault().post(new CartTotalEvent(total));
                    Log.d(TAG, "onClick: " + total);
                }


                Cart cart1 = new Cart(counter);
                Retrofit retrofit = RetrofitClient.getClient();


                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Cart> call = apiInterface.updateCart(cart.getCartItemID(), cart1);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });

            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                total = 0;

                EventBus.getDefault().post(new CartTotalEvent(total));

                Retrofit retrofit = RetrofitClient.getClient();


                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Cart> cartCall = apiInterface.deleteItemFromCart(cart.getCartItemID());
                cartCall.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");


                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });


                carts.remove(position);
                notifyDataSetChanged();
            }

        });


    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView CartProductName;
        TextView CartProductPrice;
        TextView productQty;
        ImageButton cartIncrease;
        ImageButton cartDecrease;
        ImageButton deleteProduct;


        public CartViewHolder(View itemView) {
            super(itemView);
            CartProductName = itemView.findViewById(R.id.cartItemName);
            CartProductPrice = itemView.findViewById(R.id.cartItemPrice);
            productQty = itemView.findViewById(R.id.cartItemQuantity);
            cartIncrease = itemView.findViewById(R.id.increaseQuantity);
            cartDecrease = itemView.findViewById(R.id.cartDecrease);
            deleteProduct = itemView.findViewById(R.id.btnCartDelete);


        }


    }

}
