package hr.kviz.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConn {
	private static Connection conn = null;

	public static Connection dajKonekciju() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/kviz";
		String user = "postgres";
		String pass = "fesb";
		try {
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (Exception e) {
			System.out.println("dajKonekciju, err=" + e.getMessage());
			throw new SQLException("Connection error, " + e.getMessage());
		}
		return conn;
	}
	
}
