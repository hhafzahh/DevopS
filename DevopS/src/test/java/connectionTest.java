import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;

public class connectionTest {
	// Connection object
	static Connection con = null;
	// Statement object
	private static Statement stmt;
	// Constant for Database URL
	public static String DB_URL = "jdbc:mysql://localhost:3306/userdetails";
	//Database Username
	public static String DB_USER = "root";
	// Database Password
	public static String DB_PASSWORD = "password";
	
	
	
  @BeforeTest
  public void beforeTest() {
	  
	  try{
		// Database connection
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass);
		// Get connection to DB
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = con.createStatement();
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
  }

  @AfterTest
  public void afterTest() {
	// Close DB connection
	  if (con != null) {
	  try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
	}
  


  @Test
  public void getConnectionTest() {
	  try{
		  String query = "select * from userdetails";
		  // Get the contents of userinfo table from DB
		  ResultSet res = stmt.executeQuery(query);
		  // Print the result untill all the records are printed
		  // res.next() returns true if there is any next record else returns false
		  while (res.next())
		  {
		  System.out.print(res.getString(1));
		  System.out.print(" " + res.getString(2));
		  System.out.print(" " + res.getString(3));
		  System.out.println(" " + res.getString(4));
		  }
		  }
		  catch(Exception e)
		  {
		  e.printStackTrace();
		  }
  }
}
