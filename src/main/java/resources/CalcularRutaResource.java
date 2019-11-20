package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import models.Ruta;
import util.TraductorUtil;

@Path("calcularruta")
public class CalcularRutaResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String calcularRuta(String jsonString) {
		Ruta ruta = new Ruta();
		ruta = TraductorUtil.JsonARuta(jsonString);
		ruta.actualizarRuta();
		ruta.calcularDistanciaRuta();
		ruta.calcularTiempoRuta();
		String jsonResponse = "";
		jsonResponse = TraductorUtil.RutaAJson(ruta);
		
		return jsonResponse;
        
	}
}
