package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String SERVER_HOST = "localhost";
	private static final String SERVER_PORT = "3306";
	private static final String DATABASE_NAME = "urbanjourney";
	private static final String DATABASE_USER ="root";
	private static String databasePass = "";
	
	static {
		try {
			File file = new File("C:\\Users\\Jesus\\Desktop\\DATABASE_PASS.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			databasePass = br.readLine();
			br.close();
		} catch (IOException e) {
			System.out.println("No se encontró el archivo del psswd de la base de datos");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = String.format(URL_FORMAT, SERVER_HOST, SERVER_PORT, DATABASE_NAME);
			conn = DriverManager.getConnection(url, DATABASE_USER, databasePass);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo algo al realizar la conexion");
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("La conexion no se cerro correctamente");
		}
	}
	
}
