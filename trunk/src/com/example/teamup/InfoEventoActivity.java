package com.example.teamup;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.teamup.bean.Equipo;
import com.example.teamup.bean.Evento;

public class InfoEventoActivity extends Activity {
	private EditText nombre_partido;
	private EditText lugar_partido;
	private EditText fecha_partido;
	private EditText hora_partido;
	private EditText input_nombre;
	private EditText input_color;
	private Button boton_sorteo;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private String currentDate;
	private String currentTime;
	private Context context;
	private Calendar dateAndTime = Calendar.getInstance();
	private ListView lista_equipos;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
	
	private Evento evento;
	private int position;
	private int igualdad;
	private int iteraciones;
	private static final int DATE_DIALOG_ID = 0;
	private static final int TIME_DIALOG_ID = 1;
	private static final int DIALOG_NOMBRE_EQUIPO = 2;
	private static final int DIALOG_COLOR_EQUIPO = 3;
	private static final int DIALOG_SORTEO = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_evento);

    	Bundle bundle = this.getIntent().getExtras(); //Contiene la información pasada en el intent
        
        evento = new Evento();
        evento.setEquipos((ArrayList<Equipo>) bundle.getSerializable("EQUIPOS"));
        igualdad = bundle.getInt("IGUALDAD");
        iteraciones = bundle.getInt("ITERACIONES");
        
        list = new ArrayList<String>();

	    context = getApplicationContext();
	    
	    lista_equipos = (ListView) findViewById(R.id.lv_equipos);
	    lista_equipos.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                arg1.showContextMenu();
            }
        });

		for (int i=0; i<evento.getEquipos().size(); i++) {
			list.add(evento.getEquipos().get(i).getNombre() + " [" + evento.getEquipos().get(i).getColor() + "]");
		}
	    
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        lista_equipos.setAdapter(adapter);
        registerForContextMenu(lista_equipos);
        
		nombre_partido = (EditText)this.findViewById(R.id.et_nombre_partido);
		lugar_partido = (EditText)this.findViewById(R.id.et_lugar_partido);
		boton_sorteo = (Button)this.findViewById(R.id.bt_sortear);
		fecha_partido = (EditText)this.findViewById(R.id.et_fecha_partido);
		hora_partido = (EditText)this.findViewById(R.id.et_hora_partido);
        input_nombre = new EditText(context);
        input_nombre.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input_color = new EditText(context);
        input_color.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		
		fecha_partido.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View arg0) {
	            final Calendar c = Calendar.getInstance();
	            year = c.get(Calendar.YEAR);
	            month = c.get(Calendar.MONTH);
	            day = c.get(Calendar.DAY_OF_MONTH);
	            showDialog(DATE_DIALOG_ID);
	        }
	    });
		
		hora_partido.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View arg0) {
	            hour = 19;
	            min = 0;
	            showDialog(TIME_DIALOG_ID);
	        }
	    });
		boton_sorteo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
	            showDialog(DIALOG_SORTEO);
            }
		});
	}
	private void updateDate() {
		dateAndTime.set(Calendar.YEAR, year);
		dateAndTime.set(Calendar.MONTH, month);
		dateAndTime.set(Calendar.DAY_OF_MONTH, day);
		
		String dia = String.valueOf(day);
		String mes = String.valueOf(month + 1);
		if (day<10) dia = "0"+dia;
		if ((month + 1)<10) mes = "0"+mes;
		
	    currentDate = new StringBuilder().append(dia).append("/")
	            .append(mes).append("/").append(year).toString();

	    Log.i("DATE", currentDate);
	}
	OnDateSetListener myDateSetListener = new OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker datePicker, int i, int j, int k) {

	        year = i;
	        month = j;
	        day = k;
	        updateDate();
	        fecha_partido.setText(currentDate);
	    }
	};
	private void updateTime() {
		dateAndTime.set(Calendar.HOUR_OF_DAY, hour);
		dateAndTime.set(Calendar.MINUTE, min);
	      
		String hora = String.valueOf(hour);
		String minutos = String.valueOf(min);
		if (hour<10) hora = "0"+hora;
		if ((min + 1)<10) minutos = "0"+minutos;
		
	    currentTime = new StringBuilder().append(hora).append(":")
	            .append(minutos).toString();

	    Log.i("TIME", currentTime);
	}
	OnTimeSetListener myTimeSetListener = new OnTimeSetListener() {

	    @Override
	    public void onTimeSet(TimePicker timePicker, int i, int j) {

	        hour = i;
	        min = j;
	        updateTime();
	        hora_partido.setText(currentTime);
	    }
	};
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this, myDateSetListener, year, month,
	                day);
	    case TIME_DIALOG_ID:
	        return new TimePickerDialog(this, myTimeSetListener, hour, min, true);
        case DIALOG_NOMBRE_EQUIPO:
            return new AlertDialog.Builder(InfoEventoActivity.this)
	            .setTitle("Nombre del equipo")
	            .setMessage("Introduzca el nombre del equipo.")
	            .setView(input_nombre)
	            .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
	            })
	            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	evento.getEquipos().get(position).setNombre(input_nombre.getText().toString());
	                	list.set(position,evento.getEquipos().get(position).getNombre() + " [" + evento.getEquipos().get(position).getColor() + "]");
	        	        adapter.notifyDataSetChanged();
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
				})
	            .create();
        case DIALOG_COLOR_EQUIPO:
            return new AlertDialog.Builder(InfoEventoActivity.this)
	            .setTitle("Color del equipo")
	            .setMessage("Introduzca el color del equipo.")
	            .setView(input_color)
	            .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
	            })
	            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	evento.getEquipos().get(position).setColor(input_color.getText().toString());
	                	list.set(position,evento.getEquipos().get(position).getNombre() + " [" + evento.getEquipos().get(position).getColor() + "]");
	        	        adapter.notifyDataSetChanged();
	        	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	                }
				})
	            .create();
        case DIALOG_SORTEO:
            return new AlertDialog.Builder(InfoEventoActivity.this)
            .setTitle("Correo electrónico")
            .setMessage("¿Desea enviar una copia del evento por email?")
            .setNeutralButton("Cancelar", null)
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
    				Intent intent = new Intent(InfoEventoActivity.this, ResultSorteoActivity.class);
                    try{
                    	evento.setNombre(nombre_partido.getText().toString());
                    	evento.setLugar(lugar_partido.getText().toString());
                    	evento.setFechaYhora(dateAndTime);
                        //Creamos la información a pasar entre actividades
                        Bundle b = new Bundle();
                        b.putSerializable("EVENTO",evento);
                        b.putInt("IGUALDAD",igualdad);
                        b.putInt("ITERACIONES",iteraciones);
                    	
                        //Añadimos la información al intent
                        intent.putExtras(b);
       
                        //Iniciamos la nueva actividad
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                    	e.printStackTrace();
                    }
                }
            })
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
    				Intent intent = new Intent(InfoEventoActivity.this, MailingActivity.class);
                    try{
                    	evento.setNombre(nombre_partido.getText().toString());
                    	evento.setLugar(lugar_partido.getText().toString());
                    	evento.setFechaYhora(dateAndTime);
                        //Creamos la información a pasar entre actividades
                        Bundle b = new Bundle();
                        b.putSerializable("EVENTO",evento);
                        b.putInt("IGUALDAD",igualdad);
                        b.putInt("ITERACIONES",iteraciones);
                    	
                        //Añadimos la información al intent
                        intent.putExtras(b);
       
                        //Iniciamos la nueva actividad
                        startActivityForResult(intent,1);
                    }catch (Exception e){
                    	e.printStackTrace();
                    }
                }
			})
            .create();
	    }
	    return null;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if(resultCode==1){
	        finish();
	    }
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	        ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.list_equipos_menu, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
	            .getMenuInfo();
	 
	    switch (item.getItemId()) {
	    case R.id.editar_nombre_equipo:
		    Log.d("MENU_EQUIPO_NOMBRE", info.toString());
		    System.out.println("MENU_EQUIPO_NOMBRE:"+info.toString());
		    position = info.position;
            input_nombre.setText(evento.getEquipos().get(position).getNombre());
            input_nombre.setSelection(input_nombre.getText().length());
            showDialog(DIALOG_NOMBRE_EQUIPO);
	        return true;
	    case R.id.editar_color_equipo:
		    Log.d("MENU_EQUIPO_COLOR", String.valueOf(info.position));
		    System.out.println("MENU_EQUIPO_NOMBRE:"+String.valueOf(info.position));
		    position = info.position;
            input_color.setText(evento.getEquipos().get(position).getColor());
            input_color.setSelection(input_color.getText().length());
            showDialog(DIALOG_COLOR_EQUIPO);
	        return true;
	    }
        return false;
	}
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
