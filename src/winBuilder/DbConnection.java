package winBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection{

	
	public Connection getDbConnection() throws SQLException{
		try {
			String connect = "jdbc:mysql://localhost:3306/kkps";
			String username = "root";
			String password = "";
			
			Connection con =  DriverManager.getConnection(connect, username,password);
			return con;
			
		}catch(SQLException e) {
			throw e;	
		}
	}
}