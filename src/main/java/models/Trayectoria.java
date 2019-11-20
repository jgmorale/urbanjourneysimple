package models;

public class Trayectoria {
	private int id;
	private int idRuta;
	private String origen;
	private String destino;
	private int tiempo;
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
