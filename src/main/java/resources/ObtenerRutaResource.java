package resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Ruta;
import util.DatabaseManager;
import util.DatabaseUtil;
import util.TraductorUtil;

@Path("rutaguardada")
public class ObtenerRutaResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String obtenerRuta(@FormParam("idRuta") int idRuta) {
		Ruta ruta = new Ruta();
		Connection conn = DatabaseUtil.getConnection();
		DatabaseManager dbManager = new DatabaseManager(conn);
		ruta = dbManager.obtenerRuta(idRuta);
		String jsonResponse = "";
		jsonResponse = TraductorUtil.RutaAJson(ruta);
		
		return jsonResponse;
	}
}
