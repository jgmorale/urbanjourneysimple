package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ruta")
public class Ruta {
	@Id
	// Generar automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// Una columna de la tabla
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "num_lugares")
	private int numLugares;
	@Column(name = "duracion")
	private int duracion;
	@Column(name = "distancia")
	private int distancia;
	
	public Ruta() {}

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

}
