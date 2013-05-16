package com.example.teamup;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teamup.bean.Evento;

 
public class ResultSorteoActivity extends Activity {
	private Evento evento;
	private int igualdad;
	private int iteraciones;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //
      // Variables
      // 	
    	final String vTexto; // Contendra el texto resultado del Sorteo
        Bundle bundle = this.getIntent().getExtras(); //Contiene la información pasada en el intent
      //
      // Codigo de la funcion
      //  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_sorteo);
        //
        // Obtenemos una referencia al TextView que va a mostrar 
        // el resultado del Sorteo por pantalla
        //
        TextView txtSorteo = (TextView)findViewById(R.id.TxtSaludo);
        //
        // Obtenemos la estructura que contiene los 
        // equipos sorteados y que nos la envia la Actividad MailingActivity
        //
        evento = (Evento) bundle.getSerializable("EVENTO");
        igualdad = bundle.getInt("IGUALDAD");
        iteraciones = bundle.getInt("ITERACIONES");
        //
        // Obtenemos el texto que contiene el sorteo aleatorio
        // y lo imprimimos por pantalla
        //
        vTexto = ResultSorteo();
        txtSorteo.setText(vTexto);
        //         
    }

    private String ResultSorteo(){
    	String Result;
    	DecimalFormat df = new DecimalFormat("#.###");
    	
    	Result = "Nivel de ensaimada="+igualdad+"; iteración="+iteraciones+"\n\n";
    	// Recorremos el mapa de equipos para pintarlos
		for (int i=0; i<evento.getEquipos().size(); i++) {
			//
			// Pintamos el titulo del equipo
			//
			Result += evento.getEquipos().get(i).getNombre()+" ("+evento.getEquipos().get(i).getColor()+") [total="+df.format(evento.getEquipos().get(i).getTotalPuntEquipo())+"; media="+df.format(evento.getEquipos().get(i).getMediaEquipo())+"]"+"\n\n";
			//
			// Recorremos la lista de miembros del equipo para pintarlos
        	//
			for(int j=0; j<evento.getEquipos().get(i).getMiembros().size(); j++){
        		Result += evento.getEquipos().get(i).getMiembros().get(j).getNombre() + "\n";
			}
        	Result += "\n\n\n";
		}
		return Result;
    }
    
	public void accionSorteoEventual(View view) {
		Intent intent = new Intent(ResultSorteoActivity.this, ListaParticipantesActivity.class);
		finish();
		startActivity(intent);
	}

	public void accionSalir(View view) {
		Intent intent = new Intent(ResultSorteoActivity.this, Inicio.class);
		finish();
		startActivity(intent);
	}	
}    
   
