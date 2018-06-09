package com.example.salman.restaurantapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Salman on 6/10/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Cart> carts;
    CartActivity activity;

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
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.CartProductName.setText(cart.getProductName());
        holder.CartProductPrice.setText(cart.getProductPrice().toString());
        holder.productQty.setText(cart.getQuantity().toString());


    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView CartProductName;
        TextView CartProductPrice;
        TextView productQty;
        Button cartIncrease;
        Button cartDecrease;
        Button deleteProduct;

        public CartViewHolder(View itemView) {
            super(itemView);
            CartProductName = itemView.findViewById(R.id.cartItemName);
            CartProductPrice = itemView.findViewById(R.id.cartItemPrice);
            productQty = itemView.findViewById(R.id.btnShowQuantity);
            cartIncrease = itemView.findViewById(R.id.btnCartIncrease);
            cartDecrease = itemView.findViewById(R.id.btnCartDecrease);
            deleteProduct = itemView.findViewById(R.id.btnCartDelete);

        }
    }
}
