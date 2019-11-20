package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Ruta;

@Path("salvarruta")
public class GuardarRutaResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarRuta(String jsonString) {
		Ruta ruta = new Ruta();
        
	}

}
