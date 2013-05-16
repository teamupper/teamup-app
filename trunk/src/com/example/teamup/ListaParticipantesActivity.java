package com.example.teamup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.example.teamup.algoritmo.Ensaimada;
import com.example.teamup.bean.Equipo;
import com.example.teamup.bean.Participante;
import com.example.teamup.list.ListParticipantesAdapter;

public class ListaParticipantesActivity extends Activity implements OnKeyListener{
	private EditText etInput;
	private Button btnSorteo;
	private ListView lvItem;
	private ArrayList<Participante> list;
	private ListParticipantesAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lista_participantes);
		setUpView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lista_participantes, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_add_items:
            	etInput.setVisibility(View.VISIBLE);
            	etInput.requestFocus();
            	InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etInput, InputMethodManager.SHOW_FORCED);
            	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                	// Al pulsar enter en el input, insertamos el participante en la lista
                	addItemList();
                    return true;
            }
        }
        return false;
    }

	private void setUpView() {
        // TODO Auto-generated method stub
        etInput = (EditText)this.findViewById(R.id.editText_input);
        etInput.setFocusableInTouchMode(true);
        etInput.setVisibility(View.VISIBLE);
        etInput.setOnKeyListener(this);
        //Listeners para el input
        etInput.addTextChangedListener(new TextWatcher(){

			@Override
            public void  afterTextChanged (Editable s){ 
				if (etInput.getText().toString().trim().length()>0) {
					etInput.setError(null);
				}
            }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			} 
        });
        
        // Cargamos la lista por defecto
        list = obtenerItems();
        //list.clear();
        adapter = new ListParticipantesAdapter(this,list);
        
        lvItem = (ListView)this.findViewById(R.id.listView_items);
        lvItem.setAdapter(adapter);
        
        //Set up para el boton de sortear
        btnSorteo = (Button)this.findViewById(R.id.bt_continuar);
        btnSorteo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				// Creamos el picker para el numero de equipos
				LayoutInflater li = LayoutInflater.from(ListaParticipantesActivity.this);
				View promptsView = li.inflate(R.layout.dialog_sorteo, null);
                final AlertDialog.Builder num_grupos = new AlertDialog.Builder(new ContextThemeWrapper(ListaParticipantesActivity.this, R.style.CustomDialogTheme));
                num_grupos.setTitle("Número de equipos");
                
                num_grupos.setView(promptsView);
                
                final EditText et_numequipos = (EditText) promptsView.findViewById(R.id.et_numequipos);
                et_numequipos.setText("2");
                Button btnMinus = (Button) promptsView.findViewById(R.id.bt_numequipos_minus);
                btnMinus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	int num = Integer.valueOf(et_numequipos.getText().toString());
                        if(num>0){
                        	et_numequipos.setText(String.valueOf(num-1));
                        }
                    }
                });
                Button btnPlus = (Button) promptsView.findViewById(R.id.bt_numequipos_plus);
                btnPlus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	int num = Integer.valueOf(et_numequipos.getText().toString());
                    	et_numequipos.setText(String.valueOf(num+1));
                    }
                });
                final ToggleButton tb_aleatorio = (ToggleButton) promptsView.findViewById(R.id.tb_swialeatorios);
                tb_aleatorio.setChecked(true);
//                final NumberPicker np = (NumberPicker) promptsView.findViewById(R.id.numberPicker);
//		        np.setMaxValue(10);
//		        np.setMinValue(0);
//		        np.setValue(2);
//		        np.setFocusable(true);
//		        np.setFocusableInTouchMode(true);
		        
                num_grupos.setNegativeButton("Cancelar", null);
                num_grupos.setPositiveButton("Aceptar", new AlertDialog.OnClickListener() {
					@Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Creamos el Intent que nos llevara a la info del envento
						ArrayList<Participante> participantes = new ArrayList<Participante>();
						ArrayList<Equipo> equipos = new ArrayList<Equipo>();
						Intent intent = new Intent(ListaParticipantesActivity.this, InfoEventoActivity.class);

                        try{
	                        // Guardamos la lista de participantes en las preferencias
	                    	String sParticipantes = new String("");
	                		SharedPreferences pref = getSharedPreferences(
	                				"com.example.teamup_preferences", MODE_PRIVATE);

	                        for(int i=0;i<list.size();i++){
	                       		if (!"".equals(sParticipantes)) {
	                       			sParticipantes = sParticipantes.concat(",");
	                       		}
	                       		sParticipantes = sParticipantes.concat(list.get(i).getNombre() + "##" + list.get(i).getPunt_sub());
	                        }
	                        // guardar sParticipantes
	                		SharedPreferences.Editor editor = pref.edit();
	                		editor.putString("lista_participantes", sParticipantes);
	                		editor.commit();
	                		
	                        //Creamos la información a pasar entre actividades
	                        Bundle b = new Bundle();
	                        for(int i=0;i<list.size();i++){
	                        	if(list.get(i).isSeleccionado()){
	                        		participantes.add(list.get(i));	
	                        	}
	                        }
	                		Ensaimada ensaimada = new Ensaimada();
	                        if(tb_aleatorio.isChecked()){
		                        equipos = ensaimada.sortear(participantes, Integer.valueOf(et_numequipos.getText().toString()), true);
	                        }else{
	                        	intent = new Intent(ListaParticipantesActivity.this, EquiposManualesActivity.class);
		                        equipos = Ensaimada.crearEquipos(Integer.valueOf(et_numequipos.getText().toString()));
		                        b.putSerializable("PARTICIPANTES",participantes);
	                        }
	                        b.putSerializable("EQUIPOS",equipos);
	                        b.putInt("IGUALDAD",ensaimada.getNivel_igualdad());
	                        b.putInt("ITERACIONES",ensaimada.getIteraciones());
                        	
	                        //Añadimos la información al intent
	                        intent.putExtras(b);
	       
	                        //Iniciamos la nueva actividad
	                        startActivity(intent);
                        }catch (Exception e){
                        	num_grupos.show();
                        }
					}});
                num_grupos.show();
            }
        });

	}

	private ArrayList<Participante> obtenerItems() {
		SharedPreferences pref = getSharedPreferences(
				"com.example.teamup_preferences", MODE_PRIVATE);
		Boolean cargar_ult = Boolean.valueOf(pref.getBoolean("ultimo_sorteo_rap", true));
		String sParticipantes = new String(pref.getString("lista_participantes", ""));
		ArrayList<Participante> items = new ArrayList<Participante>();

        // se carga el ultimo sorteo
		if (cargar_ult && !"".equals(sParticipantes)) {
			String[] lista_ult = sParticipantes.split(",");
			
			for (int i = 0; i < lista_ult.length; i++) {
				float punt = Float.parseFloat(lista_ult[i].split("##")[1]);
				String nom = lista_ult[i].split("##")[0];

				Participante ult = new Participante(nom.trim());
				ult.setActivo(true);
				ult.setSeleccionado(true);
				ult.setPunt_sub(punt);
				items.add(ult);
			}
		}
		return items;
	}

	protected void addItemList() {
		// TODO Auto-generated method stub
		if (isInputValid(etInput) && noExiste(list, etInput)) {
			Participante part_aux = new Participante(etInput.getText().toString().trim()); 
			part_aux.setActivo(true);
			part_aux.setSeleccionado(true);
			part_aux.setPunt_sub(5);
			list.add(part_aux);
			etInput.setText("");

			adapter.notifyDataSetChanged();
			lvItem.setSelection(lvItem.getCount() - 1);
		}
	}

	protected boolean noExiste(ArrayList<Participante> lista_items,
			EditText etInput2) {
		// TODO Auto-generatd method stub
		if (lista_items != null) {
			for (int i = 0; i < lista_items.size(); i++) {
				if (lista_items.get(i) != null
						&& etInput2.getText().toString().trim()
								.equals(lista_items.get(i).getNombre())) {
					etInput2.setError("Pringao, el participante ya existe!");
					return false;
				}
			}
		}
		etInput2.setError(null);
		return true;
	}

	protected boolean isInputValid(EditText etInput2) {
		// TODO Auto-generatd method stub
		if (etInput2.getText().toString().trim().length() < 1) {
			etInput2.setError("Introduce un participante");
			return false;
		} else {
			etInput2.setError(null);
			return true;
		}

	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
