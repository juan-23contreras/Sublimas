package com.pry.sublimadoscr.hecho;import android.annotation.SuppressLint;import android.app.ProgressDialog;import android.content.DialogInterface;import android.content.Intent;import android.content.pm.PackageManager;import android.graphics.Bitmap;import android.net.Uri;import android.os.Build;import android.os.Bundle;import android.os.Environment;import android.provider.MediaStore;import android.provider.Settings;import android.util.Base64;import android.util.Log;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.Toast;import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley;import com.pry.sublimadoscr.databinding.ActivityProductoBinding;import org.json.JSONException;import org.json.JSONObject;import java.io.ByteArrayOutputStream;import java.io.IOException;import java.util.HashMap;import java.util.Map;import java.util.UUID;import androidx.appcompat.app.AlertDialog;import androidx.appcompat.app.AppCompatActivity;import androidx.core.app.ActivityCompat;import androidx.core.content.ContextCompat;import static android.Manifest.permission.READ_EXTERNAL_STORAGE;import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;import static android.os.Build.VERSION.SDK_INT;import static java.util.UUID.randomUUID;public class CrearProducto extends AppCompatActivity {	private ActivityProductoBinding binding;	private static final int PERMISSION_REQUEST_CODE = 2296;	EditText codigo,categoria,cantidad, nombreti,descripcion,precio,caracteristicas;	String strcodigo,strcategoria,strcantidad, strnombreti,strdescripcion,strprecio,strcaracteristicas;	Button btn_crearProduct;	//int id;	String clave, id_Product;	private  boolean imagenexist=false;	private Bitmap bitmap;	ImageView profile_image;	@Override	protected void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);		binding= ActivityProductoBinding.inflate(getLayoutInflater());		setContentView(binding.getRoot());		Bundle parametros = getIntent().getExtras();		if (parametros != null) {			clave = getIntent().getStringExtra("CLAVE");			if(clave.equals("CREAR")){			//	id = getIntent().getIntExtra("DESCR",0);			//	convertirid= String.valueOf(id);				codigo=binding.codigoedi;				categoria=binding.categoriaedi;				cantidad= binding.cantidadEdid;				nombreti =binding.nombreedi;				descripcion=binding.descripedi;				precio=binding.precioedi;				caracteristicas= binding.caracteriticaedi;				btn_crearProduct =binding.uploadButton;				profile_image=binding.subirimagen;				btn_crearProduct.setOnClickListener(view -> {					strcodigo= codigo.getText().toString();					strcategoria= categoria.getText().toString();					strcantidad=cantidad.getText().toString();					strnombreti=nombreti.getText().toString();					strdescripcion=descripcion.getText().toString();					strprecio=precio.getText().toString();					strcaracteristicas=caracteristicas.getText().toString();					if(categoria.getText().toString().isEmpty()){						Toast.makeText(this, "por ingrese un categoria", Toast.LENGTH_SHORT).show();						categoria.setError("ingrese categoria");					}else if (codigo.getText().toString().isEmpty()){						categoria.setError(null);						codigo.setError("ingrese codigo");					}else if (cantidad.getText().toString().isEmpty()){						cantidad.setError(null);						cantidad.setError("ingrese un cantidad");					}else if (!imagenexist){						cantidad.setError(null);						//	 registrarfaena(strTitulo, strDescripcion);						Toast.makeText(this, "Seleccione una imagen", Toast.LENGTH_SHORT).show();					}else{						AlertDialog.Builder builder = new AlertDialog.Builder(this);						builder.setTitle("Crear producto");						builder.setMessage("¿Estas seguro de registrar esta producto?");						builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {							CrearProducto(strcodigo,strcategoria,strnombreti,strprecio,strdescripcion,strcantidad, getStringImage(bitmap),strcaracteristicas);						});						builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {							@Override							public void onClick(DialogInterface dialogInterface, int i) {								finish();							}						});						AlertDialog dialog = builder.create();						dialog.show();					}				});				profile_image.setOnClickListener(View->{					if (checkPermission()) {						//Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();						chooseFile();					} else {						requestPermission();					}				});			}else if(clave.equals("MOD")){				id_Product = getIntent().getStringExtra("id_producto");				String		codi = getIntent().getStringExtra("codi");				String catego = getIntent().getStringExtra("cat");				String		nombre = getIntent().getStringExtra("nombre");		        String price = getIntent().getStringExtra("precio");				String		descrip = getIntent().getStringExtra("descrip");				String cantida = getIntent().getStringExtra("cantidad");				String		imagen = getIntent().getStringExtra("image");				String caracter = getIntent().getStringExtra("carac");				binding.subirimagen.setVisibility(View.GONE);				codigo=binding.codigoedi;				categoria=binding.categoriaedi;				cantidad= binding.cantidadEdid;				nombreti =binding.nombreedi;				descripcion=binding.descripedi;				precio=binding.precioedi;				caracteristicas= binding.caracteriticaedi;				btn_crearProduct =binding.uploadButton;				categoria.setText(catego);				codigo.setText(codi);				nombreti.setText(nombre);				precio.setText(price);				descripcion.setText(descrip);				cantidad.setText(cantida);				caracteristicas.setText(caracter);				binding.txtdatos.setText("Actualizar producto");				binding.uploadButton.setText("ACTUALIZAR");				binding.uploadButton.setOnClickListener(view -> {					strcodigo= codigo.getText().toString();					strcategoria= categoria.getText().toString();					strcantidad=cantidad.getText().toString();					strnombreti=nombreti.getText().toString();					strdescripcion=descripcion.getText().toString();					strprecio=precio.getText().toString();					strcaracteristicas=caracteristicas.getText().toString();					if(categoria.getText().toString().isEmpty()){						Toast.makeText(this, "por ingrese un categoria", Toast.LENGTH_SHORT).show();						categoria.setError("ingrese categoria");					}else if (nombreti.getText().toString().isEmpty()){						categoria.setError(null);						nombreti.setError("ingrese nombre");					}else if (cantidad.getText().toString().isEmpty()){						cantidad.setError(null);						cantidad.setError("ingrese un cantidad");					}else{						AlertDialog.Builder builder = new AlertDialog.Builder(this);						builder.setTitle("Actualizar");						builder.setMessage("¿Estas seguro de actulizar esta producto?");						builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {							Modiccarproducto(id_Product);						});						builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {							@Override							public void onClick(DialogInterface dialogInterface, int i) {                            finish();							}						});						AlertDialog dialog = builder.create();						dialog.show();					}				});			}		}else{//		convertirid=(PreferenceUtils.getId_localidad(this));		}		binding.back.setOnClickListener(view -> {			finish();		});	}	//TODO METODOS TERMINA AQUI	//TODO METODO PARA SUBIR IMAGEN	private void chooseFile() {		Intent intent = new Intent();		intent.setType("image/*");		intent.setAction(Intent.ACTION_GET_CONTENT);		startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), 1);	}	//otro metodo	@Override	protected void onActivityResult(int requestCode, int resultCode, Intent data) {		super.onActivityResult(requestCode, resultCode, data);		if (requestCode == 2296) {			if (SDK_INT >= Build.VERSION_CODES.R) {				if (Environment.isExternalStorageManager()) {					// perform action when allow permission success				} else {					Toast.makeText(getApplicationContext(), "¡Permitir permiso para el acceso al almacenamiento!", Toast.LENGTH_SHORT).show();				}			}		}		if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {			Uri filePath = data.getData();			try {				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);				profile_image.setImageBitmap(bitmap);				imagenexist=true;			} catch (IOException e) {				e.printStackTrace();			}//            UploadPicture(String.valueOf("5c7c637e2f76d1.50870717"), getStringImage(bitmap));		}	}	//otro metodo modificar	//otro metodo	private void Modiccarproducto(String id_f) {		strnombreti= nombreti.getText().toString();		strdescripcion=descripcion.getText().toString();		final ProgressDialog progressDialog = new ProgressDialog(this);		progressDialog.setMessage("Actulizando producto ....");		progressDialog.show();		StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ticssoluciones.000webhostapp.com/VersionApp/Sublimadosphp/editar.php",				new Response.Listener<String>() {					@Override					public void onResponse(String response) {						progressDialog.dismiss();						Log.i("TAG", response.toString());						String success = null;						try {							JSONObject jsonObject = new JSONObject(response);							success = jsonObject.getString("success");							String message = jsonObject.getString("message");							if (success.equals("1")) {								Toast.makeText(CrearProducto.this, message, Toast.LENGTH_SHORT).show();								finish();								//getUserDetail();							}						} catch (JSONException e) {							e.printStackTrace();							progressDialog.dismiss();							Toast.makeText(CrearProducto.this, "Error !", Toast.LENGTH_SHORT).show();							//getUserDetail();						}					}				},				new Response.ErrorListener() {					@Override					public void onErrorResponse(VolleyError error) {						progressDialog.dismiss();						Toast.makeText(getApplicationContext(), "Error de red vuelvalo a intentar mas tarde!" , Toast.LENGTH_SHORT).show();					}				}) {			@Override			protected Map<String, String> getParams() throws AuthFailureError {				Map<String, String> params = new HashMap<>();				params.put("codigo", strcodigo);				params.put("categoria", strcategoria);				params.put("nombre", strnombreti);				params.put("precio",strprecio);				params.put("descripcion", strdescripcion);				params.put("cantidad",strcantidad);				params.put("caracteristicas", strcaracteristicas);				params.put("id_producto", id_f);				return params;			}		};		RequestQueue requestQueue = Volley.newRequestQueue(this);		requestQueue.add(stringRequest);	}	@SuppressLint("NotConstructor")	private void CrearProducto(String strcodigo, String strcategoria, String strnombreti, String strprecio, String strdescripcion, String strcantidad, String stringImage, String strcaracteristicas) {		final ProgressDialog progressDialog = new ProgressDialog(this);	String	NombreUUID = randomUUID().toString();		progressDialog.setMessage("Creando producto ....");		progressDialog.show();		StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ticssoluciones.000webhostapp.com/VersionApp/Sublimadosphp/productos/crear.php",				new Response.Listener<String>() {					@Override					public void onResponse(String response) {						progressDialog.dismiss();						Log.i("TAG", response.toString());						String success = null;						try {							JSONObject jsonObject = new JSONObject(response);							 success = jsonObject.getString("success");							String message = jsonObject.getString("message");							if (success.equals("1")) {								Toast.makeText(CrearProducto.this, message , Toast.LENGTH_SHORT).show();								btn_crearProduct.setVisibility(View.GONE);								profile_image.setEnabled(false);								btn_crearProduct.setEnabled(false);								finish();								//getUserDetail();							} else	if (success.equals("0")) {								Toast.makeText(CrearProducto.this, message , Toast.LENGTH_SHORT).show();								//getUserDetail();							}						} catch (JSONException e) {							e.printStackTrace();							progressDialog.dismiss();								Toast.makeText(CrearProducto.this, "error", Toast.LENGTH_SHORT).show();								//getUserDetail();						}					}				},				new Response.ErrorListener() {					@Override					public void onErrorResponse(VolleyError error) {						progressDialog.dismiss();						Toast.makeText(getApplicationContext(), "Error de red vuelvalo a intentar mas tarde!" , Toast.LENGTH_SHORT).show();					}				}) {			@Override			protected Map<String, String> getParams() throws AuthFailureError {				Map<String, String> params = new HashMap<>();				params.put("codigo", strcodigo);				params.put("categoria", strcategoria);				params.put("nombre", strnombreti);				params.put("precio",strprecio);				params.put("descripcion", strdescripcion);				params.put("cantidad",strcantidad);				params.put("ramdomname", NombreUUID);				params.put("photo",stringImage);				params.put("caracteristicas", strcaracteristicas);				return params;			}		};		RequestQueue requestQueue = Volley.newRequestQueue(this);		requestQueue.add(stringRequest);	}	public String getStringImage(Bitmap bitmap) {		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();		bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);		byte[] imageByteArray = byteArrayOutputStream.toByteArray();		String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);		return encodedImage;	}//permisos de carpetas	private boolean checkPermission() {		if (SDK_INT >= Build.VERSION_CODES.R) {			return Environment.isExternalStorageManager();		} else {			int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);			int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);			return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;		}	}	private void requestPermission() {		if (SDK_INT >= Build.VERSION_CODES.R) {			try {				Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);				intent.addCategory("android.intent.category.DEFAULT");				intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));				startActivityForResult(intent, 2296);			} catch (Exception e) {				Intent intent = new Intent();				intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);				startActivityForResult(intent, 2296);			}		} else {			//below android 11			ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);		}	}	@Override	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {		super.onRequestPermissionsResult(requestCode, permissions, grantResults);		switch (requestCode) {			case PERMISSION_REQUEST_CODE:				if (grantResults.length > 0) {					boolean READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;					boolean WRITE_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED;					if (READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE) {						// perform action when allow permission success					} else {						Toast.makeText(getApplicationContext(), "¡Permitir permiso para el acceso al almacenamiento!", Toast.LENGTH_SHORT).show();					}				}				break;		}	}	@Override	public void onBackPressed() {		// back was pressed		AlertDialog.Builder builder = new AlertDialog.Builder(this);		builder.setTitle("SALIR");		builder.setMessage("¿Estas seguro de salir?");		builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {			finish();		});		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {			@Override			public void onClick(DialogInterface dialogInterface, int i) {			}		});		AlertDialog dialog = builder.create();		dialog.show();	}}