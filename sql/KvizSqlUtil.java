package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class KvizSqlUtil {

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ignore) {				
			}
		}
		return;
	}

	public static void close(Statement stm) {
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException ignore) {				
			}
		}
		return;
	}

	public static void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException ignore) {				
			}
		}
		return;
	}
	
}
