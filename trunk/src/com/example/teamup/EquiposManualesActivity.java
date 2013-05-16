package com.example.teamup;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.teamup.bean.Equipo;
import com.example.teamup.bean.Participante;
import com.example.teamup.list.EquiposManualesAdapter;

public class EquiposManualesActivity extends ListActivity {
	private ArrayList<Participante> participantes = new ArrayList<Participante>();
	private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	private int igualdad;
	private int iteraciones;
	private Button btnSiguiente;
	private ListView lvItem;
	private EquiposManualesAdapter adapter;
	private static final int DIALOG_EQUIPOS = 0;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos_manuales);
        
        // Cargamos la lista por defecto

    	Bundle bundle = this.getIntent().getExtras(); //Contiene la información pasada en el intent

    	participantes = (ArrayList<Participante>) bundle.getSerializable("PARTICIPANTES");
    	for(int i=0; i<participantes.size(); i++){
    		participantes.get(i).setSeleccionado(false);
    	}
    	equipos = (ArrayList<Equipo>) bundle.getSerializable("EQUIPOS");
        igualdad = bundle.getInt("IGUALDAD");
        iteraciones = bundle.getInt("ITERACIONES");
        adapter = new EquiposManualesAdapter(this,participantes);
        
        setListAdapter(adapter);
        
        btnSiguiente = (Button)this.findViewById(R.id.bt_continuar);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Intent intent = new Intent(EquiposManualesActivity.this, InfoEventoActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("EQUIPOS",equipos);
                b.putInt("IGUALDAD",igualdad);
                b.putInt("ITERACIONES",iteraciones);
            	
                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }
 
    private Dialog showDialogButtonClick() {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("Seleccion de equipo");
         
        final String[] choiceList = new String[equipos.size()];
        for(int i=0;i<equipos.size();i++){
        	choiceList[i] = equipos.get(i).getNombre();
        }
         
        int selected = -1; // does not select anything

        builder.setSingleChoiceItems(
                choiceList, 
                selected, 
                new DialogInterface.OnClickListener() {
                										@Override
											            public void onClick(DialogInterface dialog, int which) {
											                asignarEquipo(which);
											                removeDialog(DIALOG_EQUIPOS);
											            }
											        });
        return builder.create();
    }
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog = new Dialog(this);
    	switch (id){
	        case DIALOG_EQUIPOS:
	        	return showDialogButtonClick();
    	}
    	return dialog;
    }
    
    public void asignarEquipo(int equipo){
    	if(equipo>-1){
	    	for(int i=0;i<participantes.size();i++){
	    		Participante aux = participantes.get(i); 
	    		if(aux.isSeleccionado()){
	    			for(int j=0;j<equipos.size();j++){
	    				if(equipos.get(j).getMiembros().contains(aux)){
	    					equipos.get(j).getMiembros().remove(aux);
	    				}
	    			}
	    			if(!equipos.get(equipo).getMiembros().contains(aux)){
	    				participantes.get(i).setEquipo(equipos.get(equipo).getNombre());
	    				equipos.get(equipo).getMiembros().add(aux);
	    			}
	    		}
	    	}
	    	for(int i=0; i<participantes.size(); i++){
	    		participantes.get(i).setSeleccionado(false);
	    	}
			adapter.notifyDataSetChanged();
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_equipos_manuales, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_select_equipo:
	            showDialog(DIALOG_EQUIPOS);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    @Override 
    protected void onListItemClick(ListView listView,  View view, int position, long id) {
			super.onListItemClick(listView, view, position, id);
			Participante aux = (Participante) getListAdapter().getItem(position);
			CheckBox checkBox = (CheckBox)view.findViewById(R.id.row_chkSelect);
			if(checkBox.isChecked()){
				checkBox.setChecked(false);
				aux.setSeleccionado(false);
			}else{
				checkBox.setChecked(true);
				aux.setSeleccionado(true);
			}
	}

}
