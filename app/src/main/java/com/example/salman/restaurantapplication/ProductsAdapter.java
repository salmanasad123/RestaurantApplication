package com.example.salman.restaurantapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salman on 6/7/2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.productsViewHolder> {

    private static final String TAG = "MTAG";
    ShowMenuProducts showMenuProducts;
    List<GetMenuProducts> getMenuProducts;
    List<Cart> cartList;


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
    public void onBindViewHolder(productsViewHolder holder, final int position) {
        final GetMenuProducts products = getMenuProducts.get(position);
        Log.d(TAG, "onBindViewHolder: " + products.getProductName() + products.getPrice());

        holder.productName.setText(products.getProductName());
        holder.productPrice.setText("PKR  " + products.getPrice().toString());

        holder.addProductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(showMenuProducts, "Item Added To Cart" + products.getProductName(), Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.5:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                final Call<Cart> cartCall = apiInterface.addToCart(products.getProductID(),
                        products.getProductName(), products.getPrice(), 1);


                cartCall.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });


            }
        });


    }

    @Override
    public int getItemCount() {
        return getMenuProducts.size();
    }

    public class productsViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button addProductbtn;

        public productsViewHolder(View itemView) {

            super(itemView);
            productImage = itemView.findViewById(R.id.ProductImageView);
            productName = itemView.findViewById(R.id.ProductName);
            productPrice = itemView.findViewById(R.id.ProductPrice);
            addProductbtn = itemView.findViewById(R.id.addProducts);
        }
    }
}
