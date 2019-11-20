package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Lugar;
import models.Ruta;
import models.Trayectoria;

@Path("calcularruta")
public class CalcularRutaResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Ruta calcularRuta(Ruta ruta) {
		//ruta = TraductorUtil.JsonARuta(jsonString);
		//ruta.actualizarRuta();
		System.out.println("Si llego a aquí");
		List<Lugar> lugares = null;
		List<Trayectoria> trayectorias = null;
		System.out.println(ruta.getDistancia());
		System.out.println(ruta.getDuracion());
		//String jsonResponse = "";
		//jsonResponse = TraductorUtil.RutaAJson(ruta);
		
		return ruta;
        
	}
}
