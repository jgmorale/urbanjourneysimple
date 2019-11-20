package resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Ruta;
import util.DatabaseManager;
import util.DatabaseUtil;
import util.TraductorUtil;

@Path("rutasguardadas")
public class ObtenerRutasResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String obtenerRutas() {
		List<Ruta> rutas = new ArrayList<Ruta>();
		Connection conn = DatabaseUtil.getConnection();
		DatabaseManager dbManager = new DatabaseManager(conn);
		rutas = dbManager.obtenerRutas();
		String jsonResponse = "";
		jsonResponse = TraductorUtil.RutasAJson(rutas);
		
		return jsonResponse;
	}
}
