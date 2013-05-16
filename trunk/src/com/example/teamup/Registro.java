package com.example.teamup;

import junit.framework.Assert;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import android.os.AsyncTask;
import android.content.Context;

import com.example.teamup.usuarioendpoint.Usuarioendpoint;
import com.example.teamup.usuarioendpoint.model.Usuario;
import com.example.teamup.utils.StringCipher;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;

public class Registro extends Activity {

    private TextView lblGotoLogin;
    private Button btnRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputNombre;
    private EditText inputApellido;
    private EditText inputNick;
    private TextView registerErrorMsg;    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_registro);    

		inputNombre = (EditText) findViewById(R.id.txtNombre);
		inputApellido = (EditText) findViewById(R.id.txtApellido);
		inputNick = (EditText) findViewById(R.id.txtNick);
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPassword = (EditText) findViewById(R.id.txtPass);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
        lblGotoLogin = (TextView) findViewById(R.id.link_to_login);
        lblGotoLogin.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
       		lanzarInicio();
		  }
		  });
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
    public void registrar(){
    	if(isEmailValid(inputEmail.getText().toString())){
            new InsertaUsuario().execute(getApplicationContext());
//        	if (insertaUsuario()){
//                //
//                // Finalmente cargamos el Home
//                //
//        		lanzarInicio(); 
//        	}else{
//        		registerErrorMsg.setText("Se ha producido un error al registrar el usuario.");
//        	}
    	}else{
    		inputEmail.setError("Tu email no es correcto, zoquete!");
    	}
    }  
    
    public class InsertaUsuario extends AsyncTask<Context, Integer, Long> {
        protected Long doInBackground(Context... contexts) {

        	Usuarioendpoint.Builder endpointBuilder = new Usuarioendpoint.Builder(
              AndroidHttp.newCompatibleTransport(),
              new JacksonFactory(),
              new HttpRequestInitializer() {
              public void initialize(HttpRequest httpRequest) { }
              });
        	Usuarioendpoint endpoint = CloudEndpointUtils.updateBuilder(
			endpointBuilder).build();
	      try {
	        	StringCipher cipher = new StringCipher();
	              Usuario user = new Usuario()
		              .setName(inputEmail.getText().toString())
		              .setEmail(inputEmail.getText().toString())
		              .setPassword(cipher.encrypt(inputPassword.getText().toString()))
		              .setNombre(inputNombre.getText().toString())
		              .setApellido(inputApellido.getText().toString())
		              .setNick(inputNick.getText().toString())
		              .setFechaAlta(new DateTime(new Date()));
	              Usuario result = endpoint.insertUsuario(user).execute();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
          return (long) 0;
        }
    }
    
    public boolean isEmailValid(String email)
    {
         String regExpn =
             "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                 +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                   +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                   +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                   +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                   +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

     CharSequence inputStr = email;

     Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
     Matcher matcher = pattern.matcher(inputStr);

     if(matcher.matches())
        return true;
     else
        return false;
    }

    private void lanzarHome()
    {
    	try 
    	{ 
    		Bundle b = new Bundle();
    		b.putString("MAIL",inputEmail.getText().toString());
            //
    		Intent intent = new Intent(Registro.this, Home.class);  
    		intent.putExtras(b); 
    		startActivity(intent);
    	} catch (Exception e) 
    	{   
    		Log.e("LanzarActivityHome", e.getMessage(), e);   
    	} 
    }
	
	public void botonPulsado(View view){

	    switch (view.getId()) {
        	case R.id.btnRegister:
        		registrar();
        		break;
        	default:
        		break;
	    }
	}     

	public void lanzarInicio() {
		Intent intent = new Intent(this, Inicio.class);
		startActivity(intent);
        finish();
	}
    
	
}
