package com.example.teamup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MisGrupos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mis_grupos);
		cargarListaGrupos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mis_grupos, menu);
		return true;
	}
	
	public void cargarListaGrupos(){
		
	}
	
	public void botonPulsado(View view){
		
	    switch (view.getId()) {
        	case R.id.bt_nuevogrupo:
        		lanzarNuevoGrupo(view);
        		break;
	    }
	} 

	public void lanzarNuevoGrupo(View view) {
		Intent intent = new Intent(MisGrupos.this, NuevoGrupo.class);
		startActivity(intent);
	}

}
