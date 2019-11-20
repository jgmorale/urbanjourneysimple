package resources;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Ruta;
import util.DatabaseManager;
import util.DatabaseUtil;
import util.TraductorUtil;

@Path("salvarruta")
public class GuardarRutaResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String guardarRuta(String jsonString) {
		Ruta ruta = new Ruta();
		ruta = TraductorUtil.JsonARuta(jsonString);
		Connection conn = DatabaseUtil.getConnection();
		DatabaseManager dbManager = new DatabaseManager(conn);
		
		dbManager.insertarRuta(ruta);
		
		return "Si le pego a este recurso";
        
	}

}
