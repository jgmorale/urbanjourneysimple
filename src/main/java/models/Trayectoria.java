package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "trayectoria")
public class Trayectoria {
	@Id
	// Generar automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "id_ruta")
	private int idRuta;
	@Column(name = "origen")
	private String origen;
	@Column(name = "destino")
	private String destino;
	@Column(name = "tiempo")
	private int tiempo;
	@Column(name = "distancia")
	private int distancia;
	
	public Trayectoria() {}

	public Trayectoria(int id, int idRuta, String origen, String destino, int tiempo, int distancia) {
		this.id = id;
		this.idRuta = idRuta;
		this.origen = origen;
		this.destino = destino;
		this.tiempo = tiempo;
		this.distancia = distancia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	
}	
