package com.example.teamup;

import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teamup.Registro.InsertaUsuario;
import com.example.teamup.bean.Usuario_TU;
import com.example.teamup.usuarioendpoint.Usuarioendpoint;
import com.example.teamup.usuarioendpoint.model.Usuario;
import com.example.teamup.utils.StringCipher;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;

public class Inicio extends Activity {
	private static String DEFAULT_HOST_URL = "http://teamup-server.appspot.com/";

    private TextView lblGotoRegister;
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView loginErrorMsg;
    private Usuario usuario;
    private Usuario_TU usuario_tu;
    private ProgressDialog pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_inicio);
		
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPassword = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.bt_login);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);
        lblGotoRegister = (TextView) findViewById(R.id.link_to_register);
        lblGotoRegister.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
			lanzarRegistro(v);
		  }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_inicio, menu);
		return true;
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			accionPreferencias(null);
			break;
		}
		return true;
		/** true -> consumimos el item, no se propaga */
	}

	public void accionPreferencias(View view) {
		Intent intent = new Intent(this, Preferencias.class);
		startActivity(intent);
	}
	
	public void botonPulsado(View view){

	    switch (view.getId()) {
        	case R.id.bt_eventorapido:
        		lanzarEventoRapido(view);
        		break;
        	case R.id.bt_login:
        		login();
        		break;
        	default:
        		break;
	    }
	} 

	public void login() {
    	pd = ProgressDialog.show(this, "Iniciando sesi�n", "Espere unos segundos...", true, false);
        new ValidaUsuario().execute(getApplicationContext());
	}
    
    public class ValidaUsuario extends AsyncTask<Context, Integer, Integer> {
        protected Integer doInBackground(Context... contexts) {

        	Usuarioendpoint.Builder endpointBuilder = new Usuarioendpoint.Builder(
        	        AndroidHttp.newCompatibleTransport(),
        	        new JacksonFactory(),
        	        new HttpRequestInitializer() {
        	        	public void initialize(HttpRequest httpRequest) { }
        	        });
        	Usuarioendpoint endpoint = CloudEndpointUtils.updateBuilder(
        	          endpointBuilder).build();
        	Usuario user = null;
            try {
                  user = endpoint.getUsuario(inputEmail.getText().toString()).execute();
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
            try {
            	StringCipher cipher = new StringCipher();
    	        String pass_encrypt = cipher.encrypt(inputPassword.getText().toString());
    	        if(!user.getPassword().equals(pass_encrypt)){
    	            return 2;
    	        }
            } catch (Exception e) {
                e.printStackTrace();
                return 3;
            }
	        usuario = user;
	        usuario_tu = new Usuario_TU(user);
            return 0;
        }
        protected void onPostExecute(Integer result) {
     	   // Do something with the result.
            if (pd != null) {
                pd.dismiss();
            }
        	switch(result){
        	case 0:
    			lanzarHome();
                break;
        	case 1:
                loginErrorMsg.setText("El usuario no existe!");
                break;
        	case 2:
	            loginErrorMsg.setText("La contrase�a es incorrecta");
                break;
        	case 3:
                loginErrorMsg.setText("La contrase�a es incorrecta");
                break;
        	default:
                break;
        	}
        }
    }
	
    public void lanzarHome(){
		Intent intent = new Intent(this, Home.class);
        Bundle b = new Bundle();
        b.putSerializable("USUARIO",usuario);
        b.putSerializable("USUARIO_TU",usuario_tu);
        intent.putExtras(b);
		startActivity(intent);
    }

	public void lanzarEventoRapido(View view) {
		Intent intent = new Intent(Inicio.this, ListaParticipantesActivity.class);
		startActivity(intent);
	}

	public void lanzarRegistro(View view) {
		Intent intent = new Intent(this, Registro.class);
		startActivity(intent);
	}

}
