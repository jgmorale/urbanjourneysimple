package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Lugar;
import models.Ruta;
import models.Trayectoria;

public class DatabaseManager {
	private Connection conn;
	
	public DatabaseManager(Connection conn) {
		this.conn = conn;
	}
	
	public void insertarRuta(Ruta ruta) {
		String query = "INSERT INTO ruta(nombre, num_lugares, duracion, distancia) VALUES (?, ?, ?, ?)";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			
			stmnt.setString(1, ruta.getNombre());
			stmnt.setInt(2, ruta.getNumLugares());
			stmnt.setInt(3, ruta.getDuracion());
			stmnt.setInt(4, ruta.getDistancia());
			
			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		ruta.setId(obtenerIdUltimaRutaInsertada());
		insertarLugares(ruta.getLugares(), ruta.getId());
		insertarTrayectorias(ruta.getTrayectorias(), ruta.getId());
	}
	
	public int obtenerIdUltimaRutaInsertada() {
		int idRuta = 0;
		String query = "SELECT * FROM ruta order by id desc limit 1";
		Statement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(query);
			idRuta = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		return idRuta;
	}
	
	public Ruta obtenerRuta(int idRuta) {
		Ruta ruta = new Ruta();
		String query = "SELECT * FROM ruta WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			rs = stmnt.executeQuery();
			
			ruta.setId(rs.getInt(1));
			ruta.setNombre(rs.getString(2));
			ruta.setNumLugares(rs.getInt(3));
			ruta.setDuracion(rs.getInt(4));
			ruta.setDistancia(rs.getInt(5));
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		ruta.setLugares(obtenerLugaresPorIdRuta(ruta.getId()));
		ruta.setTrayectorias(obtenerTrayectoriasPorIdRuta(ruta.getId()));
		
		return ruta;
	}
	
	public List<Ruta> obtenerRutas(){
		List<Ruta> rutas = new ArrayList<Ruta>();
		String query = "SELECT id FROM ruta";
		Statement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(query);
			
			while(rs.next()) {
				int idRuta = rs.getInt(1);
				Ruta ruta = new Ruta();
				ruta = obtenerRuta(idRuta);
				rutas.add(ruta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return rutas;
	}
	
	public void modificarRuta(Ruta ruta) {	
		
		String query = "UPDATE ruta SET nombre=?, num_lugares=?, duracion=?, distancia=? WHERE id=?";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, ruta.getNombre());
			stmnt.setInt(2, ruta.getNumLugares());
			stmnt.setInt(3, ruta.getDuracion());
			stmnt.setInt(4, ruta.getDistancia());
			stmnt.setInt(5, ruta.getId());
					
			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		modificarLugares(ruta.getLugares(), ruta.getId());
		modificarTrayectorias(ruta.getTrayectorias(), ruta.getId());
	}
	
	public void eliminarRutaPorId(int idRuta) {
		String query = "DELETE FROM ruta WHERE id=?";
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		eliminarLugaresPorIdRuta(idRuta);
		eliminarTrayectoriasPorIdRuta(idRuta);
	}
	
	public void insertarTrayectoria(Trayectoria trayectoria, int idRuta) {
		String query = "INSERT INTO trayectoria(id_ruta, origen, destino, tiempo, distancia) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmnt = null;
		
		try {	
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			stmnt.setString(2, trayectoria.getOrigen());
			stmnt.setString(3, trayectoria.getDestino());
			stmnt.setInt(4, trayectoria.getTiempo());
			stmnt.setInt(5, trayectoria.getDistancia());
			
			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void insertarTrayectorias(List<Trayectoria> trayectorias, int idRuta) {
		for(Trayectoria t: trayectorias) {
			insertarTrayectoria(t,idRuta);
		}
	}
	
	public Trayectoria obtenerTrayectoria(int idTrayectoria) {
		Trayectoria trayectoria = new Trayectoria();
		String query = "SELECT * FROM trayectoria WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idTrayectoria);
			rs = stmnt.executeQuery();
			
			trayectoria.setId(rs.getInt(1));
			trayectoria.setIdRuta(rs.getInt(2));
			trayectoria.setOrigen(rs.getString(3));
			trayectoria.setDestino(rs.getString(4));
			trayectoria.setTiempo(rs.getInt(5));
			trayectoria.setDistancia(rs.getInt(6));
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		return trayectoria;
	}
	
	public List<Trayectoria> obtenerTrayectoriasPorIdRuta(int idRuta){
		List<Trayectoria> trayectorias = new ArrayList<Trayectoria>();
		String query = "SELECT * FROM trayectoria WHERE id_ruta=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			rs = stmnt.executeQuery();
			
			while(rs.next()) {
				Trayectoria trayectoria = new Trayectoria();
				
				trayectoria.setId(rs.getInt(1));
				trayectoria.setIdRuta(rs.getInt(2));
				trayectoria.setOrigen(rs.getString(3));
				trayectoria.setDestino(rs.getString(4));
				trayectoria.setTiempo(rs.getInt(5));
				trayectoria.setDistancia(rs.getInt(6));
				
				trayectorias.add(trayectoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return trayectorias;
	}
	
	public void modificarTrayectoria(Trayectoria trayectoria, int idRuta) {
		String query = "UPDATE trayectoria SET id_ruta=?, origen=?, destino=?, tiempo=?, distancia=? WHERE id=?";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			
			stmnt.setInt(1, idRuta);
			stmnt.setString(2, trayectoria.getOrigen());
			stmnt.setString(3, trayectoria.getDestino());
			stmnt.setInt(4, trayectoria.getTiempo());
			stmnt.setInt(5, trayectoria.getDistancia());
			stmnt.setInt(6, trayectoria.getId());
					
			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void modificarTrayectorias(List<Trayectoria> trayectorias, int idRuta) {
		for(Trayectoria t: trayectorias) {	
			modificarTrayectoria(t,idRuta);
		}
	}
	
	public void eliminarTrayectoriaPorId(int idTrayectoria) {
		String query = "DELETE FROM trayectoria WHERE id=?";
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idTrayectoria);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void eliminarTrayectoriasPorIdRuta(int idRuta) {
		String query = "DELETE FROM trayectoria WHERE id_ruta=?";
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void insertarLugar(Lugar lugar, int idRuta) {
		String query = "INSERT INTO lugar(id_ruta, nombre, direccion, coord_long, coord_lat, tiempo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
			
			stmnt.setInt(1, idRuta);
			stmnt.setString(2, lugar.getNombre());
			stmnt.setString(3, lugar.getDireccion());
			stmnt.setFloat(4, lugar.getCoordLong());
			stmnt.setFloat(5, lugar.getCoordLat());
			stmnt.setInt(6, lugar.getTiempoUsuario());
			
			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void insertarLugares(List<Lugar> lugares, int idRuta) {
		for(Lugar p: lugares) {
			insertarLugar(p, idRuta);
		}
	}
	
	public List<Integer> obtenerIdLugaresPorIdRuta(int idRuta){
		List<Integer> lugaresId = new ArrayList<Integer>();
		String query = "SELECT id FROM lugar WHERE id_ruta=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			rs = stmnt.executeQuery();
			
			while(rs.next()) {
				lugaresId.add(rs.getInt(1));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return lugaresId;
	}
	
	public Lugar obtenerLugarPorId(int idLugar) {
		Lugar lugar = new Lugar();
		String query = "SELECT * FROM lugar WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idLugar);
			rs = stmnt.executeQuery();
			
			lugar.setId(rs.getInt(1));
			lugar.setIdRuta(rs.getInt(2));
			lugar.setNombre(rs.getString(3));
			lugar.setDireccion(rs.getString(4));
			lugar.setCoordLong(rs.getFloat(5));
			lugar.setCoordLat(rs.getFloat(6));
			lugar.setTiempoUsuario(rs.getInt(7));
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return lugar;
	}
	
	public List<Lugar> obtenerLugaresPorIdRuta(int idRuta){
		List<Lugar> lugares = new ArrayList<Lugar>();
		
		String query = "SELECT * FROM lugar WHERE id_ruta=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			rs = stmnt.executeQuery();
			
			while (rs.next()) {
				Lugar lugar = new Lugar();
				
				lugar.setId(rs.getInt(1));
				lugar.setIdRuta(rs.getInt(2));
				lugar.setNombre(rs.getString(3));
				lugar.setDireccion(rs.getString(4));
				lugar.setCoordLong(rs.getFloat(5));
				lugar.setCoordLat(rs.getFloat(6));
				lugar.setTiempoUsuario(rs.getInt(7));
				
				lugares.add(lugar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return lugares;
	}
	
	public void modificarLugar(Lugar lugar, int idRuta) {
		String query = "UPDATE lugar SET id_ruta=?, nombre=?, direccion=?, coord_long=?, coord_lat=?, tiempo_usuario=? WHERE id=? ";
		PreparedStatement stmnt = null;
		try {
			stmnt = conn.prepareStatement(query);
					
			stmnt.setInt(1, idRuta);
			stmnt.setString(2, lugar.getNombre());
			stmnt.setString(3, lugar.getDireccion());
			stmnt.setFloat(4, lugar.getCoordLong());
			stmnt.setFloat(5, lugar.getCoordLat());
			stmnt.setInt(6, lugar.getTiempoUsuario());

			stmnt.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void modificarLugares(List<Lugar> lugares, int idRuta) {
		for(Lugar p: lugares) {
			modificarLugar(p, idRuta);
		}
	}
	
	public void eliminarLugarPorId(int idLugar) {
		String query = "DELETE FROM lugar WHERE id=?";
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idLugar);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	public void eliminarLugaresPorIdRuta(int idRuta) {
		String query = "DELETE FROM lugares WHERE id_ruta=?";
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idRuta);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
	}
}
