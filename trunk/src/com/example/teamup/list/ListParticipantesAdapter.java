package com.example.teamup.list;

import java.util.ArrayList;

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
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.teamup.R;
import com.example.teamup.bean.Participante;

public class ListParticipantesAdapter extends BaseAdapter {
	protected Activity activity;
	protected ArrayList<Participante> list;
	private int max_rate_stars = 5;
         
	public ListParticipantesAdapter(Activity activity, ArrayList<Participante> list) {
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
		ListParticipantesHolder holder = new ListParticipantesHolder();
		
        LayoutInflater inflater = (LayoutInflater) activity.getLayoutInflater();
        row = inflater.inflate(R.layout.participante_lista_participantes, parent, false);
        //row = inflater.inflate(R.layout.listview_row, null,true);
		// item to display  
		final Participante item = (Participante) this.getItem(position);   
		// Elementos de la fila
		CheckBox checkBox = (CheckBox)row.findViewById(R.id.row_chkActivo);
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
             //notifyDataSetChanged();
			}
    	});
        
        TextView textView =(TextView)row.findViewById(R.id.row_txtName);
        textView.setText(item.getNombre());
        
        Button deleteButton = (Button) row.findViewById(R.id.row_btDelete);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(
            new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer index = (Integer) v.getTag();
                    final int positionToRemove = index;
                    AlertDialog.Builder adb=new AlertDialog.Builder(activity);
                    adb.setTitle("Eliminar?");
                    adb.setMessage("Seguro que quiere eliminar a " + item.getNombre()+"?");
                    adb.setNegativeButton("Cancelar", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
						@Override
                        public void onClick(DialogInterface dialog, int which) {
                        	list.remove(positionToRemove);
                            texto_selecteds();
                            notifyDataSetChanged();
						}});
                    adb.show();
                }
            }
        );

        com.example.teamup.widget.ScrollableRatingBar ratingBar = (com.example.teamup.widget.ScrollableRatingBar)row.findViewById(R.id.row_rating);
		ratingBar.setRating(item.getPunt_sub(max_rate_stars));
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
               public void onRatingChanged(RatingBar ratingBar, float rating,
                       boolean fromUser) {
                   item.setPunt_sub(max_rate_stars,rating);
               }
	      }
	    );
        
        holder.setItem(item);
        holder.setNombre(textView);
        holder.setBt_delete(deleteButton);
        holder.setChk_activo(checkBox);
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
