package com.example.teamup.algoritmo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.teamup.bean.Equipo;
import com.example.teamup.bean.Participante;

public class Ensaimada{
	private int nivel_igualdad;
	private int iteraciones;

    public Ensaimada() {
		super();
		this.nivel_igualdad = 0;
		this.iteraciones = 0;
	}

    public Ensaimada(int nivel_igualdad, int iteraciones) {
		super();
		this.nivel_igualdad = nivel_igualdad;
		this.iteraciones = iteraciones;
	}

	public int getNivel_igualdad() {
		return nivel_igualdad;
	}

	public void setNivel_igualdad(int nivel_igualdad) {
		this.nivel_igualdad = nivel_igualdad;
	}

	public int getIteraciones() {
		return iteraciones;
	}

	public void setIteraciones(int iteraciones) {
		this.iteraciones = iteraciones;
	}

	public ArrayList<Equipo> sortear(ArrayList<Participante> lista, int num_equipos, boolean mejores_peores){
    	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    	int min_equipo = 0;	// Indica el nñumero minimo de items por grupo
    	int resto = 0; 		// Indica el número de grupos que contarán con un item más
    	float[] limites = {0,4,7,11};
    	
    	// Controlams que el número de grupos se mayor o igual que los items a sortear
    	if(num_equipos>0 && lista!=null && lista.size()>=num_equipos){
    		min_equipo = lista.size()/num_equipos;
    		resto = lista.size() % num_equipos;
    		
    		nivelloop:
    		for(int i=0; i<=10; i++){// Primer bucle para definir el umbral máximo de diferencia entre los equipos
    			iterloop:
    			for(int j=0; j<50; j++){// Segundo bucle, como mucho haremos 50 iteraciones
        			List<Float> totales;
        			List<Integer> count;
        			Float min;
        			Float max;
        			Integer max_count=0;
        			Integer min_count=0;
        			// Sorteamos los equipos
            		equipos = sortearEquipos(lista, num_equipos, min_equipo, resto);
            		if (mejores_peores){//separamos los mejores y peores miembros
            			for(int n=0; n<limites.length-1; n++){
                			count = new ArrayList<Integer>();
                			max_count=0;
                			min_count=0;
                    		for (int k=0; k<equipos.size(); k++) {
                    			count.add(equipos.get(k).getTotalMiembros(limites[n], limites[n+1]));
                    		}
                    		// mejor y peor equipo
                    		max_count = Collections.max(count);
                    		min_count = Collections.min(count);
                    		
                    		// El reparto de jugadores no es correcto, hay equipos con mas jugadores de lo debido
                    		if((max_count-min_count) > 1){
                    			continue iterloop;
                    		}
            			}
            		}
            		totales = new ArrayList<Float>();
        			count = new ArrayList<Integer>();
            		for (int k=0; k<equipos.size(); k++) {
            			totales.add(equipos.get(k).getTotalPuntEquipo());
            			count.add(equipos.get(k).getMiembros().size());
            		}
            		// mejor y peor equipo
            		max = Collections.max(totales);
            		min = Collections.min(totales);
            		max_count = Collections.max(count);
            		min_count = Collections.min(count);
            		
            		// El reparto de jugadores no es correcto, hay equipos con mas jugadores de lo debido
            		if((max_count-min_count) > 1){
            			continue iterloop;
            		}
            		
            		// Si la diferencia entre el mejor equipo y el peor está dentro del umbral, nos sirve!
            		if((max-min) <= i){
            			nivel_igualdad = i;
            			iteraciones = j;
            			break nivelloop;
            		}
        		}
    		}
    	}
    	return equipos;
    }
	public static ArrayList<Equipo> crearEquipos(int num_equipos){
    	ArrayList<Equipo> equipos_aux = new ArrayList<Equipo>();
    	Equipo equipo_aux = new Equipo();
    	char letra_grupo = 'A';
		
		// Bucle por el número de grupos
		for(int i=0;i<num_equipos;i++){
			equipo_aux = new Equipo();
			equipo_aux.setNombre("EQUIPO "+letra_grupo);
			
			// Para los dos primeros equipos definimos sus colores
			if(i==0){
				equipo_aux.setColor("BLANCO");
			}else if (i==1){
				equipo_aux.setColor("COLOR");
			}
			equipos_aux.add(equipo_aux);
			letra_grupo++;
		}
		return equipos_aux;
	}
    
    // Metodo para realizar el sorteo de los participantes en los distintos equipos, que devolverá una lista con todos los equipos
    public static ArrayList<Equipo> sortearEquipos_old(ArrayList<Participante> lista, int num_equipos, int min_equipo, int resto){
    	ArrayList<Equipo> equipos_aux = new ArrayList<Equipo>();
    	Equipo equipo_aux = new Equipo();
		Collections.shuffle(lista);
		int ini_grupo = 0;
    	char letra_grupo = 'A';
		
		// Bucle por el número de grupos
		for(int i=0;i<num_equipos;i++){
			equipo_aux = new Equipo();
			equipo_aux.setNombre("EQUIPO "+letra_grupo);
			
			// Para los dos primeros equipos definimos sus colores
			if(i==0){
				equipo_aux.setColor("BLANCO");
			}else if (i==1){
				equipo_aux.setColor("COLOR");
			}
			
			int fin_grupo = ini_grupo + min_equipo;
			if(i<resto) fin_grupo++; // Si el grupo es uno de los que cuentan con un item mas, se lo sumamos
			
			// Recorremos los elementos de ese grupo
    		for(int j=ini_grupo;j<fin_grupo;j++){
    			// Incluimos al participante en el equipo
    			equipo_aux.getMiembros().add(lista.get(j));
    			ini_grupo++;
    		}
    		// Incluimos al equipo en el mapa de equipos
			equipos_aux.add(equipo_aux);
			letra_grupo++;
		}
		return equipos_aux;
    }
    
    // Metodo para realizar el sorteo de los participantes en los distintos equipos, que devolverá una lista con todos los equipos
    public static ArrayList<Equipo> sortearEquipos(ArrayList<Participante> lista, int num_equipos, int min_equipo, int resto){
    	ArrayList<Equipo> equipos_aux = new ArrayList<Equipo>();
    	
		// Creamos los equipos
    	equipos_aux = crearEquipos(num_equipos);

		Collections.shuffle(lista);
		for(int i=0; i<lista.size(); i++){
			int equipo = i % num_equipos;
			equipos_aux.get(equipo).getMiembros().add(lista.get(i));
		}
		
		return equipos_aux;
    }
}
