package com.example.teamup.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo  implements Serializable{
	private String nombre;
	private String color;
	private ArrayList<Participante> miembros;
	
	public Equipo(){
		super();
		this.nombre = "";
		this.color = "";
		this.miembros = new ArrayList<Participante>();
	}
	
	public Equipo(String nombre, String color, ArrayList<Participante> miembros) {
		super();
		this.nombre = nombre;
		this.color = color;
		this.miembros = miembros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<Participante> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<Participante> miembros) {
		this.miembros = miembros;
	}
	
	public float getTotalPuntEquipo(){
		float total = 0;
		for(int i=0; i<miembros.size(); i++){
			total += miembros.get(i).getPunt_sub();
		}
		return total;
	}
	
	public float getMediaEquipo(){
		float total = this.getTotalPuntEquipo();
		float num = this.getMiembros().size();
		float media = 0;
		
		if(total==0 || num==0){
			return 0;
		}
		return total/num;
	}
	
	public float getTotalPuntEquipo(int max_rate){
		float total = 0;
		for(int i=0; i<miembros.size(); i++){
			total += miembros.get(i).getPunt_sub(max_rate);
		}
		return total;
	}
	
	public float getMediaEquipo(int max_rate){
		float total = this.getTotalPuntEquipo(max_rate);
		float num = this.getMiembros().size();
		float media = 0;
		
		if(total==0 || num==0){
			return 0;
		}
		return total/num;
	}
	
	public int getTotalMiembros(float valor_min, float valor_max){
		int num = 0;
		for(int i=0; i<miembros.size(); i++){
			float punt = miembros.get(i).getPunt_total();
			if (punt>=valor_min && punt<valor_max){
				num++;
			}
		}
		return num;
	}
	
}
