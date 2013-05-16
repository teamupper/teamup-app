package com.example.teamup.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class Evento implements Serializable{
	private String nombre;
	private String lugar;
	private Calendar fechaYhora;
	private ArrayList<Equipo> equipos;
	
	public Evento() {
		super();
		this.nombre = "";
		this.lugar = "";
		this.equipos = new ArrayList<Equipo>();
	}

	public Evento(String nombre, String lugar, Calendar fechaYhora,
			ArrayList<Equipo> equipos) {
		super();
		this.nombre = nombre;
		this.lugar = lugar;
		this.fechaYhora = fechaYhora;
		this.equipos = equipos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Calendar getFechaYhora() {
		return fechaYhora;
	}

	public void setFechaYhora(Calendar fechaYhora) {
		this.fechaYhora = fechaYhora;
	}

	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	
}
