package resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import models.Lugar;
import models.Ruta;
import models.RutaWrapper;
import models.Trayectoria;

@Path("rutasguardadas")
public class ObtenerRutasResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerRutas() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		int codigo = -1;
		String mensaje = "";
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Ruta> rutas = null;
		List<Lugar> lugares = null;
		List<Trayectoria> trayectorias = null;
		List<RutaWrapper> rutasWrapper = new ArrayList<RutaWrapper>();
		try {
			
			StandardServiceRegistry registry = 
					new StandardServiceRegistryBuilder()
					.configure()
					.build();
			
			sessionFactory = new MetadataSources(registry)
					.buildMetadata().buildSessionFactory();
			
			session = sessionFactory.openSession();
			
			Query queryRt = session.createQuery("FROM ruta" , Ruta.class);
			rutas = queryRt.getResultList();
			
			for(int i = 0; i < rutas.size(); i++) {
				rutasWrapper.add(new RutaWrapper());
				rutasWrapper.get(i).setRuta(rutas.get(i));
			}
			
			for(int i = 0; i < rutas.size(); i++) {
				int idRuta = rutas.get(i).getId();
				Query queryPl = session.createQuery("FROM lugar where id_ruta = :idRuta", Lugar.class);
				queryPl.setParameter("idRuta", idRuta);
				lugares = queryPl.getResultList();
				rutasWrapper.get(i).setLugares(lugares);
			}
			
			for(int i = 0; i < rutas.size(); i++) {
				int idRuta = rutas.get(i).getId();
				Query queryTr = session.createQuery("FROM trayectoria where id_ruta = :idRuta", Trayectoria.class);
				queryTr.setParameter("idRuta", idRuta);
				trayectorias = queryTr.getResultList();
				rutasWrapper.get(i).setTrayectorias(trayectorias);
			}
			
			codigo = Response.Status.OK.getStatusCode();
			mensaje = "Se obtuvieron los datos";
		}catch (Exception e) {
			e.printStackTrace();
			codigo = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
			mensaje = e.getMessage();
		}finally {
			if(session != null) {
				session.close();
			}
			if(sessionFactory != null) {
				sessionFactory.close();
			}
		}
		int i = 0;
		response.put("rutas", rutasWrapper);
		response.put("mensaje", mensaje);
		
		return Response.status(codigo).entity(response).build();
	}
}
