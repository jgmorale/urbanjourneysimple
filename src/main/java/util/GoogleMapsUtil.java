package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleMapsUtil {
	// TODO Logic already completed. Need to test the module.
	private static final String URL = "https://maps.googleapis.com/maps/api/directions/json?";
	private static String apiKey = "";
	
	static {
		try {
			File file = new File("C:\\Users\\Jesus\\Desktop\\API_KEY.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			apiKey = br.readLine();
			br.close();
		} catch (IOException e) {
			System.out.println("No se encontró el archivo de la API KEY");
			e.printStackTrace();
		}
	}
	
	public static List<String> getTimeDistSteps(String origen, String destino) {
		List<String> lista = new ArrayList<String>();
		String url_final = URL + "origin=" + origen + "&destination" + destino + "&key=" + apiKey;
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url_final);
		Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
		Response response = request.get();
		String jsonResponse = response.readEntity(String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(jsonResponse);
			JsonNode nameNode = rootNode.path("nombre");
			System.out.println(nameNode.toString());
					
			JsonNode callesNode = rootNode.path("calles");
			Iterator<JsonNode> elements =  callesNode.elements();
					
			while(elements.hasNext()) {
				JsonNode calle = elements.next();
				System.out.println(calle.toString());
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
