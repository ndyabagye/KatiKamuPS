package winBuilder;

import java.sql.*;

public class DBCreate {
	
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_URL_EXISTS = "jdbc:mysql://localhost:3306/kkps";
	
	static final String USER = "root";
	static final String PASS = "";

	
	public static boolean createDB() {
		if (checkDBExists("kkps") == true) {
			System.out.println("DB already exists");
			return true;
		} else {
			try {
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			    
				Statement stmt = conn.createStatement(); 		      
			    String sql = "CREATE DATABASE kkps";
			    stmt.executeUpdate(sql);
			    stmt.close();
			    
			    if(checkDBExists("kkps") == true) {
			    	System.out.println("Database created");
			    	try {
				    	Connection dbconn = DriverManager.getConnection(DB_URL_EXISTS, USER,PASS);
					    String students = "CREATE TABLE students ( "
					    		  +"id int NOT NULL AUTO_INCREMENT, "
					    		  +"firstName varchar(256) DEFAULT NULL, "
					    		  +"lastName varchar(256) DEFAULT NULL, "
					    		  +"stuAge int DEFAULT NULL, "
					    		  +"regNum varchar(255) DEFAULT NULL, "
					    		  +"stuGender varchar(100) DEFAULT NULL, "
					    		  +"stuClass varchar(256) DEFAULT NULL, "
					    		  +"Maths int DEFAULT NULL, "
					    		  +"Science int DEFAULT NULL, "
					    		  +"SST int DEFAULT NULL, "
					    		  +"English int DEFAULT NULL, "
					    		  +"PRIMARY KEY (ID), "
					    		  +"UNIQUE KEY regNum (regNum) 	"
					    		  +")";
				    
					    Statement stmt2 = dbconn.createStatement();
					    stmt2.executeUpdate(students);
					    stmt2.close();
					    
					    String teachers = "CREATE TABLE teachers ( "
					    	    +"id int NOT NULL AUTO_INCREMENT,"
					    	    +"firstName varchar(255) NOT NULL, "
					    	    +"lastName varchar(255) NOT NULL, "
					    	    +"email varchar(255) NOT NULL, "
					    	    +"password varchar(255) NOT NULL, "
					    	    +"PRIMARY KEY (id), "
					    	    +"UNIQUE KEY email (email) "
					    	+" )";
					    
					    Statement stmt3 = dbconn.createStatement();
					    stmt3.executeUpdate(teachers);
					    stmt3.close();
					    
					    String defaultTeacher = "INSERT INTO teachers VALUES (1,'Admin','Admin','admin@gmail.com','root')";
					    Statement stmt4 = dbconn.createStatement();
					    stmt4.executeUpdate(defaultTeacher);
					    stmt4.close();
					    
					    String timetable = "CREATE TABLE timetable ( "
										    +"id int NOT NULL AUTO_INCREMENT, "
										    +"class varchar(255) NOT NULL, "
										    +"Monday varchar(255) DEFAULT NULL, "
										    +"Tuesday varchar(255) DEFAULT NULL, "
										    +"Wednesday varchar(255) DEFAULT NULL, "
										    +"Thursday varchar(255) DEFAULT NULL, "
										    +"Friday varchar(255) DEFAULT NULL, "
										    +"PRIMARY KEY (id), "
										    +"UNIQUE KEY class (class) "
										    +") ";
					    Statement stmt5 = dbconn.createStatement();
					    stmt5.executeUpdate(timetable);
					    stmt5.close();
					    
					    return true;
					    
			    	}catch (SQLException e) {
			    		e.printStackTrace();
			    		return false;
			        }
			    	
			    }else {
			    	System.out.println("Database doesn't exist");
			    	return false;
			    }
	
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
			
		}
	}

	public static boolean checkDBExists(String dbName){

	    try {
	        
	        Connection conn = DriverManager.getConnection(DB_URL, USER,PASS); //Open a connection
	    
	        ResultSet resultSet = conn.getMetaData().getCatalogs();
	        
	        while (resultSet.next()) {
	        
	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(dbName)){
	                return true;
	            }
	        }
	        resultSet.close();

	    } catch (SQLSyntaxErrorException sq) {
	    	System.out.println("DB error");
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}
