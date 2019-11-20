package resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Ruta;

@Path("rutasguardadas")
public class ObtenerRutasResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String obtenerRutas() {
		
		
		return "";
	}
}
