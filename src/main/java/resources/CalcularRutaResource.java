package resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Ruta;
import models.RutaWrapper;

@Path("calcularruta")
public class CalcularRutaResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RutaWrapper calcularRuta(RutaWrapper ruta) {
		
		//ruta.actualizarRuta();
		ruta.calcularDistanciaRuta();
		ruta.calcularTiempoRuta();
		
		return ruta;
        
	}
}
