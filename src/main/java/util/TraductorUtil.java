package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Ruta;

public class TraductorUtil {
	
	public static Ruta JsonARuta(String jsonString) {
		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		Ruta ruta = new Ruta();
				
		try {
			// read JSON like DOM Parser
			JsonNode rootNode = objectMapper.readTree(jsonString);
					
			JsonNode nombreRutaNode = rootNode.path("nombreRuta");
			System.out.println(nombreRutaNode.toString());
					
			JsonNode nombreLugaresNode = rootNode.path("nombreLugares");
			Iterator<JsonNode> nombreLugaresElements =  nombreLugaresNode.elements();
					
			while(nombreLugaresElements.hasNext()) {
				JsonNode nombreLugares = nombreLugaresElements.next();
				System.out.println(nombreLugares.toString());
			}
					
			JsonNode coordLongNode = rootNode.path("coordLong");
			Iterator<JsonNode> coordLongElements =  coordLongNode.elements();
					
			while(coordLongElements.hasNext()) {
				JsonNode coordLong = coordLongElements.next();
				System.out.println(coordLong.toString());
			}
					
			JsonNode coordLatNode = rootNode.path("coordLat");
			Iterator<JsonNode> coordLatElements =  coordLatNode.elements();
					
			while(coordLatElements.hasNext()) {
				JsonNode coordLat = coordLatElements.next();
				System.out.println(coordLat.toString());
			}
					
			JsonNode direccionesNode = rootNode.path("direcciones");
			Iterator<JsonNode> direccionesElements =  direccionesNode.elements();
					
			while(direccionesElements.hasNext()) {
				JsonNode direcciones = direccionesElements.next();
				System.out.println(direcciones.toString());
			}
					
			JsonNode tiempoUsuarioNode = rootNode.path("tiempoUsuario");
			Iterator<JsonNode> tiempoUsuarioElements =  tiempoUsuarioNode.elements();
					
			while(tiempoUsuarioElements.hasNext()) {
				JsonNode tiempoUsuario = tiempoUsuarioElements.next();
				System.out.println(tiempoUsuario.toString());
			}
					
			JsonNode tiempoTrayectoriasNode = rootNode.path("tiempoTrayectorias");
			Iterator<JsonNode> tiempoTrayectoriasElements =  tiempoTrayectoriasNode.elements();
					
			while(tiempoTrayectoriasElements.hasNext()) {
				JsonNode tiempoTrayectorias = tiempoTrayectoriasElements.next();
				System.out.println(tiempoTrayectorias.toString());
			}
					
			JsonNode distanciaTrayectoriasNode = rootNode.path("distanciaTrayectorias");
			Iterator<JsonNode> distanciaTrayectoriasElements =  distanciaTrayectoriasNode.elements();
					
			while(distanciaTrayectoriasElements.hasNext()) {
				JsonNode distanciaTrayectorias = distanciaTrayectoriasElements.next();
				System.out.println(distanciaTrayectorias.toString());
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ruta;
	}
	
	public static String RutaAJson(Ruta ruta) {
		
		String jsonResponse = "";
		
		return jsonResponse;
	}
	
	public static String RutasAJson(List<Ruta> rutas) {
		List<String> jsonRutas = new ArrayList<String>();
		for(Ruta r: rutas) {
			jsonRutas.add(RutaAJson(r));
		}
		
		String jsonResponse = "";
		
		return jsonResponse;
	}
	
}
