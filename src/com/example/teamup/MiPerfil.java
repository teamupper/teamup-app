package com.example.teamup;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.teamup.pestanas.*;

public class MiPerfil extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		
		TabHost tabhost = getTabHost(); // creamos el Tabhost de la actividad
		TabHost.TabSpec spec; // creamos un recurso para las propiedades de la pestaña
		Intent intent; // intent que se utilizara para abrir cada pestaña
		Resources res = getResources(); // obtenemos los recursos
		
		// Lanzamos la actividad para abrir la pestaña Futbol 
		intent = new Intent().setClass(this,futbol.class);
		// configuramos la pestaña con sus propiedades
		spec = tabhost.newTabSpec("Futbol").setIndicator("", res.getDrawable(R.drawable.futbol)).setContent(intent);
		//cargamos la pestaña en el contenedor tabhost
		tabhost.addTab(spec);
		//
		// (Jacin) Aqui iria un bucle con los deportes asignados al usuario, de momento cargamos por 
		// defecto 4 pestañas para ver que tal queda
		//
		// Lanzamos la actividad para abrir la pestaña Voley Playa
		intent = new Intent().setClass(this,voley_playa.class);
		// configuramos la pestaña con sus propiedades
		spec = tabhost.newTabSpec("Voley").setIndicator("", res.getDrawable(R.drawable.voley_playa)).setContent(intent);
		//cargamos la pestaña en el contenedor tabhost
		tabhost.addTab(spec);	
		//
		// Lanzamos la actividad para abrir la pestaña Baloncesto
		intent = new Intent().setClass(this,baloncesto.class);
		// configuramos la pestaña con sus propiedades
		spec = tabhost.newTabSpec("Baloncesto").setIndicator("", res.getDrawable(R.drawable.baloncesto)).setContent(intent);
		//cargamos la pestaña en el contenedor tabhost
		tabhost.addTab(spec);	
		//
		// Lanzamos la actividad para abrir la pestaña Waterpolo
		intent = new Intent().setClass(this,waterpolo.class);
		// configuramos la pestaña con sus propiedades
		spec = tabhost.newTabSpec("Waterpolo").setIndicator("", res.getDrawable(R.drawable.waterpolo)).setContent(intent);
		//cargamos la pestaña en el contenedor tabhost
		tabhost.addTab(spec);		
	}

}
