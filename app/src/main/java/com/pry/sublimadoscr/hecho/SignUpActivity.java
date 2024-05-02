package com.pry.sublimadoscr.hecho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.pry.sublimadoscr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText fname, lname, email, confirm_password, passwordEditText;
    private Button mSignUp;

    // Volley variables
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upoficial);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        fname = findViewById(R.id.fname_editText);
        lname = findViewById(R.id.lname_editText);
        email = findViewById(R.id.email_editText);
      //  phone = findViewById(R.id.phone_editText);

        mSignUp = findViewById(R.id.sign_up_button);
        passwordEditText = findViewById(R.id.user_password_editText);


        mSignUp.setOnClickListener(v -> createUser(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), passwordEditText.getText().toString()));

    }


    private void createUser(final String fname, final String lname, final String email, final String password) {

        mRequestQueue = Volley.newRequestQueue(SignUpActivity.this);
        // Progress
        mSignUp.setText("Creando usuario...");

        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("1")) {
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        mSignUp.setText("Registrado");

                    } else if (success.equals("0")) {
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        mSignUp.setText("Registrate");
                    }

                } catch (JSONException e) {
                    Toast.makeText(SignUpActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    mSignUp.setText("Registrate");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                mSignUp.setText("Registrate");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("email", email);
                params.put("phone", "");
                params.put("password", password);
                params.put("id", "2");
                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);


    }


    private String getBaseUrl() {
       // https://ticssoluciones2024.000webhostapp.com/sublimados/sign_up.php
        return "https://ticssoluciones2024.000webhostapp.com/sublimados/sign_up.php";
    }

    public void onLoginClick(View View) {
        //    startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}
