package models;

import java.util.List;

public class Ruta {
	private int id;
	private String nombre;
	private int numLugares;
	private int duracion;
	private int distancia;
	private List<Lugar> lugares;
	private List<Trayectoria> trayectorias;
	
	public Ruta() {}
		
	public List<Lugar> getLugares() {
		return lugares;
	}

	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
	}

	public List<Trayectoria> getTrayectorias() {
		return trayectorias;
	}

	public void setTrayectorias(List<Trayectoria> trayectorias) {
		this.trayectorias = trayectorias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumLugares() {
		return numLugares;
	}

	public void setNumLugares(int numLugares) {
		this.numLugares = numLugares;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public void calcularTiempoRuta() {
		int tiempo = 0;
		for(Trayectoria t: this.trayectorias) {
			tiempo+=t.getTiempo();
		}
		for(Lugar l: this.lugares) {
			tiempo+=l.getTiempoUsuario();
		}
		
		this.setDuracion(tiempo);
	}
	
	public void calcularDistanciaRuta() {
		int distancia = 0;
		for(Trayectoria t: this.trayectorias) {
			distancia+=t.getDistancia();
		}
		this.setDistancia(distancia);
	}
	
	public void agregarTrayectoria(Trayectoria trayectoria) {
		this.trayectorias.add(trayectoria);
	}
	
	public void agregarLugar(Lugar lugar) {
		this.lugares.add(lugar);
	}
	
	public void actualizarRuta() {
		
	}

}
