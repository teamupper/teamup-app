package com.example.teamup.bean;

public class Deporte {
	private Long id;
	private String nombre;

	public Deporte() {
		super();
		this.nombre = null;
		this.id = null;
	}
	public Deporte(Long id, String nombre) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
