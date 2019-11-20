package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import models.Ruta;

@Path("rutaguardada")
public class ObtenerRutaResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerRuta(@QueryParam("idRuta") int idRuta) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		int codigo = -1;
		String mensaje = "";
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Ruta> rutas = null;
		try {
			
			StandardServiceRegistry registry = 
					new StandardServiceRegistryBuilder()
					.configure()
					.build();
			
			sessionFactory = new MetadataSources(registry)
					.buildMetadata().buildSessionFactory();
			
			session = sessionFactory.openSession();
			
			Query query = session.createQuery("FROM ruta where id = :idRuta", Ruta.class);
			query.setParameter("idRuta", idRuta);
			rutas = query.getResultList();
			
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
		
		response.put("ruta", rutas);
		response.put("mensaje", mensaje);
		
		return Response.status(codigo).entity(response).build();

	}
}
