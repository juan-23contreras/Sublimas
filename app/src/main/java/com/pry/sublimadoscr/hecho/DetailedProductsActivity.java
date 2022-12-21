package com.pry.sublimadoscr.hecho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pry.sublimadoscr.R;

public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ImageView mImage;
    private TextView mTitle, descrip2, mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);

        mToolbar = findViewById(R.id.toolbar);
        mImage = findViewById(R.id.image_view);
        mPrice = findViewById(R.id.price);
        descrip2 = findViewById(R.id.descripcion);
        mTitle = findViewById(R.id.name);

        // Setting up action bar
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));

        // Catching incoming intent
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String descrip = intent.getStringExtra("descrip");
        String title = intent.getStringExtra("title");
        String id = intent.getStringExtra("id");
        String id_usuario = intent.getStringExtra("id_user");
        String codigo = intent.getStringExtra("codigo");
        String image = intent.getStringExtra("image");

        if (intent !=null){
            mActionBar.setTitle(title);
            mTitle.setText(title);
           descrip2.setText(descrip);
            mPrice.setText("$ "+price);
            Glide.with(DetailedProductsActivity.this).load(image).into(mImage);
            Button button=findViewById(R.id.comprarnbtn);
            button.setOnClickListener(v -> {
                startActivity(new Intent(DetailedProductsActivity.this,CrearPedido.class).
                        putExtra("CLAVE","CREAR").
                        putExtra("id_prod",id).
                        putExtra("id_usuario",id_usuario).
                        putExtra("codigo",codigo));
            });
        }

    }
}
