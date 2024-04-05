package com.pry.sublimadoscr.hecho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.IllegalFormatCodePointException;
import java.util.Map;

public class DeleteAccount extends AppCompatActivity {
    // Variable declarations
    private TextInputEditText mEmail, mPassword;
    private Button DELETEBUTTON;
    private ProgressBar mProgress;

    // Volley Variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        mEmail = findViewById(R.id.emailAddress_EditText);
        mPassword = findViewById(R.id.password_EditText);
        DELETEBUTTON = findViewById(R.id.DELETEBUTTON);
        mProgress = findViewById(R.id.progress);

        DELETEBUTTON.setOnClickListener(view -> {
            if (mEmail.getText().toString().isEmpty() && mPassword.getText().toString().isEmpty() ){
                Toast.makeText(this,"Ingresa todos los campos",Toast.LENGTH_SHORT).show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Elimina tu cuenta");
                builder.setMessage("¿Estas seguro de eliminar tu cuenta");

                builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {
                    eliminarCuenta(mEmail.getText().toString(),mPassword.getText().toString());
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });


    }


    public void eliminarCuenta(final String email, final String password) {
        mProgress.setVisibility(View.VISIBLE);
        String url = "https://ticssoluciones.000webhostapp.com/VersionApp/Sublimadosphp/DeleteAccount.php"; // Reemplaza con la URL de tu servicio PHP

        // Crear una solicitud POST para enviar los datos
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta del servidor
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");

                            // Procesar la respuesta
                            if (success) {
                                // Cuenta eliminada correctamente
                                mProgress.setVisibility(View.GONE);
                                Toast.makeText(DeleteAccount.this, message, Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(DeleteAccount.this, MenuInicio.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                            } else {

                                mProgress.setVisibility(View.GONE);
                                // Error al eliminar la cuenta
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Error al eliminar la cuenta
                            Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();

                            mProgress.setVisibility(View.GONE);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        Toast.makeText(getApplicationContext(), "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Parámetros POST
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(this).add(request);
    }


    private String getDeleteAccout (){
        //return "http://"+getResources().getString(R.string.machine_ip_address)+"/android/sign_in.php";
        return "https://ticssoluciones.000webhostapp.com/VersionApp/Sublimadosphp/DeleteAccount.php";
    }

}