package desafiomv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionFactory {
	
	private static final String USERNAME = "system";
	private static final String PASSWORD = "12345678";
	private static final String DATABASE_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			System.out.println("Conectado");
//			PreparedStatement ps = conn.prepareStatement("DELIMITER $$\ncreate procedure verclientes\nBEGIN\nEND $$\nDELIMITER;");
//			ps.execute();
//			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
