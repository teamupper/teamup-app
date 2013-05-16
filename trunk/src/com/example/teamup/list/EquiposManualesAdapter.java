package com.example.teamup.list;

import java.util.ArrayList;

import com.example.teamup.R;
import com.example.teamup.R.id;
import com.example.teamup.R.layout;
import com.example.teamup.bean.Participante;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class EquiposManualesAdapter extends BaseAdapter {
	protected Activity activity;
	protected ArrayList<Participante> list;
	private int max_rate_stars = 5;
         
	public EquiposManualesAdapter(Activity activity, ArrayList<Participante> list) {
		this.activity = activity;
		this.list = list;
	}
	 
	@Override
	public int getCount() {
		return list.size();
	}
	 
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
	    return position;
	}
	  
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		//ListParticipantesHolder holder = new ListParticipantesHolder();
		
        LayoutInflater inflater = (LayoutInflater) activity.getLayoutInflater();
        row = inflater.inflate(R.layout.participante_equipos_manual, parent, false);
        //row = inflater.inflate(R.layout.listview_row, null,true);
		// item to display  
		final Participante item = (Participante) this.getItem(position);   
		// Elementos de la fila
		CheckBox checkBox = (CheckBox)row.findViewById(R.id.row_chkSelect);
        checkBox.setChecked(item.isSeleccionado());
        checkBox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			 if(((CheckBox)v).isChecked()){
				  item.setSeleccionado(true);
			 }
			 else{
				  item.setSeleccionado(false);
			 }
             texto_selecteds();
			}
    	});
        
        TextView textView =(TextView)row.findViewById(R.id.row_txtName);
        textView.setText(item.getNombre());
        
        TextView textView_equipo =(TextView)row.findViewById(R.id.row_txtEquipo);
        textView_equipo.setText(item.getEquipo());

        com.example.teamup.widget.ScrollableRatingBar ratingBar = (com.example.teamup.widget.ScrollableRatingBar)row.findViewById(R.id.row_rating);
		ratingBar.setRating(item.getPunt_sub(max_rate_stars));
        
//        holder.setItem(item);
//        holder.setNombre(textView);
//        holder.setBt_delete(deleteButton);
//        holder.setChk_activo(checkBox);
        texto_selecteds();
        return row;
	}
	public void texto_selecteds(){
		 int count=0;
        for(int i=0;i<list.size();i++){
        	if(list.get(i).isSeleccionado()){
        		count++;
        	}
        }
        TextView textView =(TextView)activity.findViewById(R.id.txt_seleccionados);
        textView.setText("Seleccionados: "+count+"/"+list.size());
        
		Log.d("Click Check",count+"/"+list.size());
	}
}
