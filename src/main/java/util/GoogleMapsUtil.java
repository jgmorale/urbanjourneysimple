package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
		// Crea la URI para pedir el recurso a Google Maps con base en el origen y el destino
		String url_final;
		try {
			url_final = URL + "origin=" + URLEncoder.encode(origen, "UTF-8") + "&destination=" + URLEncoder.encode(destino, "UTF-8") + "&key=" + apiKey;
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(url_final);
			Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
			Response response = request.get();
			String jsonResponse = response.readEntity(String.class);
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonResponse);
            JSONObject jb = (JSONObject) obj;

            //Read the JSON to get the info that we want
            JSONArray jsonObject1 = (JSONArray) jb.get("routes");
            JSONObject jsonObject2 = (JSONObject)jsonObject1.get(0);
            JSONArray jsonObject3 = (JSONArray)jsonObject2.get("legs");
            JSONObject jsonFinal = (JSONObject) jsonObject3.get(0);
            JSONObject jsonTime = (JSONObject) jsonFinal.get("duration");
            JSONObject jsonDist = (JSONObject) jsonFinal.get("distance");
            
            JSONArray jsonStepsArr = (JSONArray) jsonFinal.get("steps");
            String time = jsonTime.get("value").toString();
            String distance = jsonDist.get("value").toString();
            String steps = jsonStepsArr.toString();
            
            // Agrega la información que nos importa a la lista
            lista.add(time);
            lista.add(distance);
            //lista.add(steps);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
        // Regresa la lista
		return lista;
	}
}
