package com.pry.sublimadoscr.hecho;

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

import androidx.appcompat.app.AppCompatActivity;


public class SignAdminUpActivity extends AppCompatActivity {

    private TextInputEditText fname, lname, email, phone, confirm_password,  passwordEditText;
    private Button mSignUp;

    // Volley variables
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upoficial);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        fname = findViewById(R.id.fname_editText);
        lname = findViewById(R.id.lname_editText);
        email = findViewById(R.id.email_editText);
        phone = findViewById(R.id.phone_editText);
        confirm_password = findViewById(R.id.confirm_password_editText);
        mSignUp = findViewById(R.id.sign_up_button);
        passwordEditText = findViewById(R.id.user_password_editText);


       mSignUp.setOnClickListener(v -> createUser(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),phone.getText().toString(),passwordEditText.getText().toString()));

    }


    private void createUser(final String fname, final String lname, final String email, final String phone, final String password){

        mRequestQueue = Volley.newRequestQueue(SignAdminUpActivity.this);
        // Progress
        mSignUp.setText("CREANDO USUARIO ADMIN...");

                mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {

                                Toast.makeText(SignAdminUpActivity.this,message,Toast.LENGTH_SHORT).show();
                                mSignUp.setText("Inscribirse");


                            }

                        } catch (JSONException e) {

                            Toast.makeText(SignAdminUpActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                            mSignUp.setText("Inscribirse");

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignAdminUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        mSignUp.setText("Inscribirse");

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("fname",fname);
                        params.put("lname",lname);
                        params.put("email",email);
                        params.put("id","1");
                        params.put("phone",phone);
                        params.put("password",password);

                        return params;
                    }
                };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);


    }


    private String getBaseUrl (){
        return "https://sicazmovil.000webhostapp.com/sublimados/sign_up.php";
    }
    public void onLoginClick(View View){
    //    startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}
