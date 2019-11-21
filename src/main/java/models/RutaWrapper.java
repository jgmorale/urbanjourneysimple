package models;

import java.util.ArrayList;
import java.util.List;

import util.GoogleMapsUtil;

public class RutaWrapper {
	private Ruta ruta;
	private List<Lugar> lugares;
	private List<Trayectoria> trayectorias;
	
	public RutaWrapper() {}
	
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public List<Lugar> getLugares() {
		return lugares;
	}
	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
		int n = 0;
		for(Lugar l: lugares) {
			n+=1;
		}
		this.ruta.setNumLugares(n);
	}
	public List<Trayectoria> getTrayectorias() {
		return trayectorias;
	}
	public void setTrayectorias(List<Trayectoria> trayectorias) {
		this.trayectorias = trayectorias;
	}
	
	public void insertarLugar(Lugar lugar) {
		
	}
	
	public void agregarLugar(Lugar lugar) {
		
	}
	
	public Lugar obtenerLugar(int idLugar) {
		Lugar lugar = new Lugar();
		return lugar;
	}
	
	public void modificarLugar(Lugar lugar) {
		
	}
	
	public void eliminarLugar(Lugar lugar) {
		
	}
	
	public void insertarTrayectoria(Trayectoria trayectoria) {
		
	}
	
	public void agregarTrayectoria(Trayectoria trayectoria) {
		
	}
	
	public Trayectoria obtenerTrayectoria(int idTrayectoria) {
		List<Trayectoria> trayectorias = this.trayectorias;
		
		for(int i = 0; i < trayectorias.size(); i++) {
			if(trayectorias.get(i).getId() == idTrayectoria) {
				return trayectorias.get(i);
			}
		}
		
		return new Trayectoria();
	}
	
	public void modificarTrayectoria(Trayectoria trayectoria) {
		
	}
	
	public void eliminarTrayectoria(Trayectoria trayectoria) {
		
	}
	
	public void actualizarRuta() {
		List<Lugar> lugares = this.lugares;
		List<Trayectoria> trayectorias = this.trayectorias;
		trayectorias.clear();
		for(int i = 0; i< lugares.size()-1; i++) {
			List<String> valores = new ArrayList<String>();
			valores = GoogleMapsUtil.getTimeDistSteps(lugares.get(i).getNombre() + " " + lugares.get(i).getDireccion(), lugares.get(i+1).getNombre() + " " + lugares.get(i).getDireccion());
			Trayectoria trayectoria = new Trayectoria();
			trayectoria.setOrigen(lugares.get(i).getNombre() + " " + lugares.get(i).getDireccion());
			trayectoria.setDestino(lugares.get(i+1).getNombre() + " " + lugares.get(i+1).getDireccion());
			trayectoria.setTiempo(Integer.valueOf(valores.get(0)));
			trayectoria.setDistancia(Integer.valueOf(valores.get(1)));
			trayectorias.add(trayectoria);
		}
	}
	
	public void calcularTiempoRuta() {
		int duracion = 0;
		List<Lugar> lugares = this.lugares;
		for(Lugar l: lugares) {
			duracion += l.getTiempoUsuario();
		}
		
		List<Trayectoria> trayectorias = this.trayectorias;
		for(Trayectoria t: trayectorias) {
			duracion += t.getTiempo();
		}
		
		this.ruta.setDuracion(duracion);
	}
	
	public void calcularDistanciaRuta() {
		int distancia = 0;
		List<Trayectoria> trayectorias = this.trayectorias;
		for(Trayectoria t: trayectorias) {
			distancia += t.getDistancia();
		}
		
		this.ruta.setDistancia(distancia);
	}
	
}
