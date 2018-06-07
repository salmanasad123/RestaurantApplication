package com.example.salman.restaurantapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Salman on 6/7/2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.productsViewHolder> {

    private static final String TAG = "MTAG";
    ShowMenuProducts showMenuProducts;
    List<GetMenuProducts> getMenuProducts;


    public ProductsAdapter(ShowMenuProducts showMenuProducts, List<GetMenuProducts> getMenuProducts) {
        this.showMenuProducts = showMenuProducts;
        this.getMenuProducts = getMenuProducts;
    }

    @Override
    public productsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_products_single_row, parent, false);
        return new productsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(productsViewHolder holder, int position) {
        GetMenuProducts products = getMenuProducts.get(position);
        Log.d(TAG, "onBindViewHolder: " + products.getProductName() + products.getPrice());

        holder.productName.setText(products.getProductName());
        holder.productPrice.setText("PKR  "+ products.getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return getMenuProducts.size();
    }

    public class productsViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public productsViewHolder(View itemView) {

            super(itemView);
            productImage = itemView.findViewById(R.id.ProductImageView);
            productName = itemView.findViewById(R.id.ProductName);
            productPrice = itemView.findViewById(R.id.ProductPrice);
        }
    }
}
