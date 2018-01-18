package bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://127.0.0.0.1:5432/development?user=superPuperAdmin&password=superPuperAdminsPassword&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	private static final String DB_USERNAME = "";
	private static final String DB_PASSWORD = "";

	public Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL);
			System.out.println("Connection OK");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection ERROR");
		}

		return connection;

	}

}
