package resources;

import java.util.ArrayList;
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

import models.Lugar;
import models.Ruta;
import models.RutaWrapper;
import models.Trayectoria;
import util.HibernateUtil;

@Path("salvarruta")
public class GuardarRutaResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardarRuta(RutaWrapper rutaWrapper) {
		//rutaWrapper.actualizarRuta();
		rutaWrapper.calcularDistanciaRuta();
		rutaWrapper.calcularTiempoRuta();
		
		Ruta ruta = new Ruta();
		List<Lugar> lugares = new ArrayList<Lugar>();
		List<Trayectoria> trayectorias = new ArrayList<Trayectoria>();
		
		ruta = rutaWrapper.getRuta();
		lugares = rutaWrapper.getLugares();
		trayectorias = rutaWrapper.getTrayectorias();
		
		Session session = null;
		Transaction tx = null;
		
		Map<String, Object> response = null;
		
		// HTTP
		int codigo = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		// Interno, notificar algun error de la logica
		int codigoInt = 0;
		String mensaje = null;
		
		try {
			
			session = HibernateUtil.getSession();//1
			tx = session.beginTransaction();//2
		
			int idRuta = (Integer) session.save(ruta); 
			
			for(Lugar l: lugares) {
				l.setIdRuta(idRuta);
				session.save(l);
			}
			
			session.flush();
	        session.clear();
			
			for(Trayectoria t: trayectorias) {
				t.setIdRuta(idRuta);
				session.save(t);
			}
			
			session.flush();
	        session.clear();
			
			tx.commit();// 5
			
			// Asignar datos de respuesta
			codigo = Response.Status.CREATED.getStatusCode();
			codigoInt = 1; // La operacion sucedio
			mensaje = "Se almaceno la ruta";
			
		}catch (TransactionException e) {
			if(tx != null) {
				tx.rollback(); // deshacer la operacion con la bd
			}
			mensaje = "Fallo en la transacción";
		}catch (HibernateException e) {
			e.printStackTrace();
			mensaje = "Error en el servidor";
		}catch (Exception e) {
			e.printStackTrace();
			mensaje = "Error";
		} finally {
			if(session != null) {
				HibernateUtil.closeSession(session);
			}
		}
		
		response = new HashMap();
		response.put("codigo", codigoInt);
		response.put("mensaje", mensaje);
		//response.put("ruta", rutaWrapper);
		
		return Response.status(codigo).entity(response).build();
        
	}

}
