package com.pry.sublimadoscr.hecho;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pry.sublimadoscr.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerPedidos extends RecyclerView.Adapter<RecyclerPedidos.MyViewHolder> {

    private Context mContext;
    private List<Pedido> products = new ArrayList<>();


    public RecyclerPedidos(Context context, List<Pedido> products){
        this.mContext = context;
        this.products = products;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mPrice;
        private ImageView mImageView;
        private RatingBar mRate;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            mTitle = view.findViewById(R.id.product_title);
            mImageView = view.findViewById(R.id.product_image);
            mRate = view.findViewById(R.id.product_rating);
            mPrice = view.findViewById(R.id.product_price);
            mContainer = view.findViewById(R.id.product_container);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.pedidoi_tem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Pedido product = products.get(position);

        holder.mPrice.setText("$"+product.getCantidad_t());
      //  holder.mRate.setRating(product.getRating());
        holder.mTitle.setText(product.getFormapago());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
