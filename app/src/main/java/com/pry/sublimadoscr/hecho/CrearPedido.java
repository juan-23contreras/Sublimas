package com.pry.sublimadoscr.hecho;import android.annotation.SuppressLint;import android.app.ProgressDialog;import android.content.DialogInterface;import android.os.Bundle;import android.util.Log;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.Toast;import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley;import com.pry.sublimadoscr.databinding.ActivityPedidoBinding;import org.json.JSONException;import org.json.JSONObject;import java.util.HashMap;import java.util.Map;import androidx.appcompat.app.AlertDialog;import androidx.appcompat.app.AppCompatActivity;import static java.util.UUID.randomUUID;public class CrearPedido extends AppCompatActivity {	private ActivityPedidoBinding binding;	EditText cantidad, forma,precio,caracteristicas;	String strforma;	String strcantidad;	String strprecio;	String strcaracteristicas;	Button btn_crearProduct;	String clave;	ImageView profile_image;	@Override	protected void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);		binding= ActivityPedidoBinding.inflate(getLayoutInflater());		setContentView(binding.getRoot());		Bundle parametros = getIntent().getExtras();		if (parametros != null) {			clave = getIntent().getStringExtra("CLAVE");			if(clave.equals("CREAR")){				String id_producto = getIntent().getStringExtra("id_prod");				String id_usuario = getIntent().getStringExtra("id_usuario");				String codigo3 = getIntent().getStringExtra("codigo");				cantidad= binding.cantidadedi;				forma =binding.formaedi;				precio=binding.totaledi;				caracteristicas= binding.caracteriticaedi;				btn_crearProduct =binding.uploadButton;				profile_image=binding.subirimagen;				btn_crearProduct.setOnClickListener(view -> {					strcantidad=cantidad.getText().toString();					strforma =forma.getText().toString();					strprecio=precio.getText().toString();					strcaracteristicas=caracteristicas.getText().toString();					if (cantidad.getText().toString().isEmpty()){						cantidad.setError(null);						cantidad.setError("ingrese un cantidad");					}else{						AlertDialog.Builder builder = new AlertDialog.Builder(this);						builder.setTitle("Comprar producto");						builder.setMessage("¿Estas seguro de comprar");						builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {							CrearProducto(strcantidad,strforma,strprecio,strcaracteristicas,id_producto,id_usuario,codigo3);						});						builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {							@Override							public void onClick(DialogInterface dialogInterface, int i) {								finish();							}						});						AlertDialog dialog = builder.create();						dialog.show();					}				});			}		}		binding.back.setOnClickListener(view -> {			finish();		});	}	@SuppressLint("NotConstructor")	private void CrearProducto(String strcantidad, String strforma, String strprecio, String strcaracteristicas, String id_producto, String id_usuario, String codigo3) {		final ProgressDialog progressDialog = new ProgressDialog(this);	String	NombreUUID = randomUUID().toString();		progressDialog.setMessage("Cromprando ....");		progressDialog.show();		StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ticssoluciones.000webhostapp.com/VersionApp/Sublimadosphp/pedido1.php",				new Response.Listener<String>() {					@Override					public void onResponse(String response) {						progressDialog.dismiss();						Log.i("TAG", response.toString());						String success = null;						try {							JSONObject jsonObject = new JSONObject(response);							 success = jsonObject.getString("success");							String message = jsonObject.getString("message");							if (success.equals("1")) {								Toast.makeText(CrearPedido.this, message, Toast.LENGTH_SHORT).show();								btn_crearProduct.setVisibility(View.GONE);								profile_image.setEnabled(false);								btn_crearProduct.setEnabled(false);								finish();								//getUserDetail();							}else if (success.equals("0")) {								Toast.makeText(CrearPedido.this, message, Toast.LENGTH_SHORT).show();								//getUserDetail();							}						} catch (JSONException e) {							e.printStackTrace();							progressDialog.dismiss();								Toast.makeText(CrearPedido.this, "Error!", Toast.LENGTH_SHORT).show();								//getUserDetail();						}					}				},				new Response.ErrorListener() {					@Override					public void onErrorResponse(VolleyError error) {						progressDialog.dismiss();						Toast.makeText(getApplicationContext(), "Error de red vuelvalo a intentar mas tarde!" , Toast.LENGTH_SHORT).show();					}				}) {			@Override			protected Map<String, String> getParams() throws AuthFailureError {				Map<String, String> params = new HashMap<>();				params.put("usuario_id", id_usuario);				params.put("codigo_id", id_producto);				params.put("cantidad_p", strcantidad);				params.put("formapago", strforma);				params.put("cantidad_t", strprecio);				params.put("mensaje", strcaracteristicas);;				return params;			}		};		RequestQueue requestQueue = Volley.newRequestQueue(this);		requestQueue.add(stringRequest);	}}