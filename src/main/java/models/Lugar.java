package models;

public class Lugar {
	private int id;
	private int idRuta;
	private String nombre;
	private String direccion;
	private float coordLong;
	private float coordLat;
	private int tiempoUsuario;
	
	public Lugar() {}
	
	public Lugar(int id, int idRuta, String nombre, String direccion, float coordLong, float coordLat, int tiempoUsuario) {
		this.id = id;
		this.idRuta = idRuta;
		this.nombre = nombre;
		this.direccion = direccion;
		this.coordLong = coordLong;
		this.coordLat = coordLat;
		this.tiempoUsuario = tiempoUsuario;
	}
	
	public int getTiempoUsuario() {
		return tiempoUsuario;
	}

	public void setTiempoUsuario(int tiempoUsuario) {
		this.tiempoUsuario = tiempoUsuario;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getCoordLong() {
		return coordLong;
	}

	public void setCoordLong(float coordLong) {
		this.coordLong = coordLong;
	}

	public float getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(float coordLat) {
		this.coordLat = coordLat;
	}
	
}
