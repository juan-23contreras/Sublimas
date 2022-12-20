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

public class RecyclerAdmidAdapter extends RecyclerView.Adapter<RecyclerAdmidAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> products = new ArrayList<>();


    public RecyclerAdmidAdapter(Context context, List<Product> products){
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product product = products.get(position);

        holder.mPrice.setText("$"+product.get$precio());
      //  holder.mRate.setRating(product.getRating());
        holder.mTitle.setText(product.getNombre());
        Glide.with(mContext).load(product.get$photo()).into(holder.mImageView);

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,CrearProducto.class);
                intent.putExtra("CLAVE","MOD");
                intent.putExtra("id_producto",product.get$id());
                intent.putExtra("codi",product.get$codigo());
                intent.putExtra("cat",product.get$categoria());
                intent.putExtra("nombre",product.getNombre());
                intent.putExtra("precio",product.get$precio());
                intent.putExtra("descrip",product.get$descripcion());
                intent.putExtra("cantidad",product.get$cantidad());
                intent.putExtra("image",product.get$photo());
                intent.putExtra("carac",product.get$caracteristicas());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
