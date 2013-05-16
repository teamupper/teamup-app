package com.example.teamup.bean;

import java.io.Serializable;

public class Participante implements Serializable{
	private String nombre;
	private String alias;
	private boolean seleccionado; //indica si el participante ha sido seleccionado para jugar
	private boolean activo; //indica si el participante está lesionado, etc...
	private String estado; //indica si el participante está disponible para jugar
	private float punt_obj; //puntuacion objetiva, votaciones de otros participantes
	private float punt_sub; //en base a partidos ganados/perdidos y diferencia entre equipos
	private int total_partidos;
	private String equipo;
	
	private int max_rate = 10;

	public Participante() {
		this.nombre = "";
		this.alias = "";
		this.seleccionado = true;
		this.activo = true;
		this.estado = "";
		this.punt_obj = 0;
		this.punt_sub = 0;
		this.total_partidos = 0;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Participante(String nombre, boolean activo) {
		super();
		this.nombre = nombre;
		this.activo = activo;
	}

	public Participante(String nombre, String alias, boolean seleccionado,
			boolean activo, String estado, float punt_obj, float punt_sub,
			int total_partidos) {
		super();
		this.nombre = nombre;
		this.alias = alias;
		this.seleccionado = seleccionado;
		this.activo = activo;
		this.estado = estado;
		this.punt_obj = punt_obj;
		this.punt_sub = punt_sub;
		this.total_partidos = total_partidos;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getPunt_obj() {
		return punt_obj;
	}

	public float getPunt_obj(int max_rate) {
		float ratio_rating = 0;
		try{
			ratio_rating = this.max_rate/max_rate;
		}catch (Exception e){
			ratio_rating = 0;
		}
		
		try{
			return punt_obj/ratio_rating;
		}catch (Exception e){
			return 0;
		}
	}

	public void setPunt_obj(float punt_obj) {
		this.punt_obj = punt_obj;
	}

	public void setPunt_obj(int max_rate, float punt_obj) {
		float ratio_rating = 0;
		try{
			ratio_rating = this.max_rate/max_rate;
		}catch (Exception e){
			ratio_rating = 0;
		}
		
		try{
			this.punt_obj = punt_obj*ratio_rating;
		}catch (Exception e){
			this.punt_obj = 0;
		}
	}

	public float getPunt_sub() {
		return punt_sub;
	}

	public float getPunt_sub(int max_rate) {
		float ratio_rating = 0;
		try{
			ratio_rating = this.max_rate/max_rate;
		}catch (Exception e){
			ratio_rating = 0;
		}
		
		try{
			return punt_sub/ratio_rating;
		}catch (Exception e){
			return 0;
		}
	}

	public void setPunt_sub(float punt_sub) {
		this.punt_sub = punt_sub;
	}

	public void setPunt_sub(int max_rate, float punt_sub) {
		float ratio_rating = 0;
		try{
			ratio_rating = this.max_rate/max_rate;
		}catch (Exception e){
			ratio_rating = 0;
		}
		
		try{
			this.punt_sub = punt_sub*ratio_rating;
		}catch (Exception e){
			this.punt_sub = 0;
		}
	}

	public float getPunt_total() {
		return this.getPunt_sub();
	}

	public float getPunt_total(int max_rate) {
		return this.getPunt_sub(max_rate);
	}

	public int getTotal_partidos() {
		return total_partidos;
	}

	public void setTotal_partidos(int total_partidos) {
		this.total_partidos = total_partidos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Participante(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
