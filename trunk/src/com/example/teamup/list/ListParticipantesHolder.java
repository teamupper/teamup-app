package com.example.teamup.list;

import com.example.teamup.bean.Participante;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class ListParticipantesHolder {
	Participante item;
    TextView nombre;
    Button bt_delete;
    CheckBox chk_activo;
    com.example.teamup.widget.ScrollableRatingBar rating;
    
	public ListParticipantesHolder() {
		super();
		this.item = new Participante();
		this.nombre = null;
		this.bt_delete = null;
	}
	
	public ListParticipantesHolder(Participante item, TextView nombre, Button bt_delete) {
		super();
		this.item = item;
		this.nombre = nombre;
		this.bt_delete = bt_delete;
	}

	public ListParticipantesHolder(Participante item, TextView nombre,
			Button bt_delete, CheckBox chk_activo) {
		super();
		this.item = item;
		this.nombre = nombre;
		this.bt_delete = bt_delete;
		this.chk_activo = chk_activo;
	}

	public ListParticipantesHolder(Participante item, TextView nombre,
			Button bt_delete, CheckBox chk_activo, com.example.teamup.widget.ScrollableRatingBar rating) {
		super();
		this.item = item;
		this.nombre = nombre;
		this.bt_delete = bt_delete;
		this.chk_activo = chk_activo;
		this.rating = rating;
	}

	public RatingBar getRating() {
		return rating;
	}

	public void setRating(com.example.teamup.widget.ScrollableRatingBar rating) {
		this.rating = rating;
	}

	public CheckBox getChk_activo() {
		return chk_activo;
	}

	public void setChk_activo(CheckBox chk_activo) {
		this.chk_activo = chk_activo;
	}

	public Participante getItem() {
		return item;
	}

	public void setItem(Participante item) {
		this.item = item;
	}

	public TextView getNombre() {
		return nombre;
	}
	public void setNombre(TextView nombre) {
		this.nombre = nombre;
	}
	public Button getBt_delete() {
		return bt_delete;
	}
	public void setBt_delete(Button bt_delete) {
		this.bt_delete = bt_delete;
	}
    
}
