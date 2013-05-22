package com.example.teamup;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teamup.Inicio.ValidaUsuario;
import com.example.teamup.bean.Usuario_TU;
import com.example.teamup.deporteendpoint.Deporteendpoint;
import com.example.teamup.deporteendpoint.model.Deporte;
import com.example.teamup.usuarioendpoint.Usuarioendpoint;
import com.example.teamup.usuarioendpoint.model.Usuario;
import com.example.teamup.utils.StringCipher;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;

public class Home extends Activity {

    private EditText txtNick;
    private Button btnPerfil;
    private Button btnGrupos;
    private Button btnEventoRapido;
    private Button btnLogout;
    private Usuario usuario;
    private Usuario_TU usuario_tu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_home);
        Bundle bundle = this.getIntent().getExtras(); //Contiene la información pasada en el intent
		
        //usuario = (Usuario)bundle.getSerializable("USUARIO");
        usuario_tu = (Usuario_TU)bundle.getSerializable("USUARIO_TU");
        
		txtNick = (EditText) findViewById(R.id.txtEmail);
		btnPerfil = (Button) findViewById(R.id.bt_miperfil);   
		btnGrupos = (Button) findViewById(R.id.bt_misgrupos);  
		btnEventoRapido = (Button) findViewById(R.id.bt_eventorapido);  
		btnLogout = (Button) findViewById(R.id.logoutButton);  
	}
	
	public void botonPulsado(View view){

	    switch (view.getId()) {
        	case R.id.bt_miperfil:
        		lanzarMiPerfil(view);
        		break;
        	case R.id.bt_misgrupos:
        		lanzarMisGrupos(view);
        		break;
        	case R.id.bt_eventorapido:
        		lanzarEventoRapido(view);
        		break;
        	case R.id.logoutButton:
        		logout();
        		break;
        	default:
        		break;
	    }
	} 

	public void lanzarEventoRapido(View view) {
		Intent intent = new Intent(Home.this, ListaParticipantesActivity.class);
		startActivity(intent);
	}

	public void lanzarMiPerfil(View view) {
		Intent intent = new Intent(Home.this, MiPerfil.class);
		startActivity(intent);
	}

	public void lanzarMisGrupos(View view) {
		Intent intent = new Intent(Home.this, MisGrupos.class);
		startActivity(intent);
	}  
	public void logout(){
		lanzarInicio();
	}

	public void lanzarInicio() {
		Intent intent = new Intent(Home.this, Inicio.class);
		startActivity(intent);
		finish();
	}  

}
