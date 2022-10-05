package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBconnection {
	//static reference to itself
	private static DBconnection instance = new DBconnection();
	public  final String URL = "jdbc:mysql://localhost:3306/library";
	public  final String USER = "root";
	public  final String PASSWORD = "1424";
	public  final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	static Connection conn;
	
	//private constructor
	private DBconnection() {
		try {
			//Step 2: Load MySQL Java driver
			Class.forName(SQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		
		Connection connection = null;
		try {
			//Step 3: Establish Java MySQL connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
	