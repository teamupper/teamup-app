package com.example.teamup;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teamup.bean.Deporte;

public class NuevoGrupo extends Activity {
	private EditText et_nombre;
	private Spinner sp_deporte;
	private EditText et_email;
	private static final int DIALOG_ERROR_NOMBRE = 0;
	private static final int DIALOG_ERROR_DEPORTE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_grupo);
		setUp();
	}

	private void setUp() {
		et_nombre = (EditText) findViewById(R.id.et_nombre);
		et_email = (EditText) findViewById(R.id.et_email);
		sp_deporte = (Spinner) findViewById(R.id.sp_deporte);
		cargarDatosSpinner();
	}

	private void cargarDatosSpinner() {
        // Spinner Drop down elements
		String deporte1;
        List<Deporte> deportes = new ArrayList<Deporte>();
		
        // Creating adapter for spinner
        ArrayAdapter<Deporte> dataAdapter = new ArrayAdapter<Deporte>(this,
                android.R.layout.simple_spinner_item, deportes);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        sp_deporte.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nuevo_grupo, menu);
		return true;
	}
	
	public void botonPulsado(View view){
		
	    switch (view.getId()) {
        	case R.id.bt_creargrupo:
        		crearGrupo(view);
        		break;
	    }
	} 

	public void crearGrupo(View view) {
		String nombre = et_nombre.getText().toString();
		if("".equals(nombre)||null==nombre){
			showDialog(DIALOG_ERROR_NOMBRE);
			return;
		}
		if(null==sp_deporte.getSelectedItem()){
			showDialog(DIALOG_ERROR_DEPORTE);
			return;
		}
		//Intent intent = new Intent(MisGrupos.this, NuevoGrupo.class);
		//startActivity(intent);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
        case DIALOG_ERROR_NOMBRE:
            return new AlertDialog.Builder(this)
	            .setTitle("Atención!")
	            .setMessage("Debe introducir un nombre para el grupo.")
	            .setNeutralButton("Cerrar", new OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
	            })
	            .create();
        case DIALOG_ERROR_DEPORTE:
            return new AlertDialog.Builder(this)
	            .setTitle("Atención!")
	            .setMessage("Debe introducir un deporte para el grupo.")
	            .setNeutralButton("Cerrar", new OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
	            })
	            .create();
	    }
	    return null;
	}

}
