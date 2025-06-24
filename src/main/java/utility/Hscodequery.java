package utility;
	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.BindValue;
import com.mysql.cj.Query;
import com.mysql.cj.jdbc.JdbcStatement;


	
	
	

	public class Hscodequery {
		
		static Connection c;

		   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys";
		    private static final String JDBC_USER = "root";
		    private static final String JDBC_PASSWORD = "root";

		    public static void saveHsCodeDetails(String countryName, String chapterNo, String chapterName, String chapterCode, String chapterCodeName, String cnCode, String descriptions) {
		        String insertSQL = "INSERT INTO hs_code (country_name, chapter_no, chapter_name, chapter_code, chapter_code_name, cn_code, descriptions) VALUES (?, ?, ?, ?, ?, ?, ?)";

		        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

		            // Set the parameters for the PreparedStatement
		            preparedStatement.setString(1, countryName);
		            preparedStatement.setString(2, chapterNo);
		            preparedStatement.setString(3, chapterName);
		            preparedStatement.setString(4, chapterCode);
		            preparedStatement.setString(5, chapterCodeName);
		            preparedStatement.setString(6, cnCode);
		            preparedStatement.setString(7, descriptions);

		            // Execute the prepared statement
		            preparedStatement.execute();
		            System.out.println("HS Code details saved successfully.");

		        } catch (SQLException e) {
		            e.printStackTrace();
		            System.err.println("Error while saving HS Code details: " + e.getMessage());
		        }
		    }
  // second table 
		    
		    
		    
		    
		    public static void saveHsCodeDetails1(String countryName,  String chapterCode, String cnCode, String descriptions) {
		        String insertSQL = "INSERT INTO hs_codes (country_name,  chapter_code, cn_code, descriptions) VALUES (?, ?, ?, ?)";

		        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

		            // Set the parameters for the PreparedStatement
		            preparedStatement.setString(1, countryName);
		          
		            preparedStatement.setString(2, chapterCode);
		          
		            preparedStatement.setString(3, cnCode);
		            preparedStatement.setString(4, descriptions);

		            if (preparedStatement.execute()) {
		            	System.out.println("saving");
					}
		            else {
//		            	 BindValue sql = ((JdbcStatement)preparedStatement).getQueryAttributesBindings().getAttributeValue(0);
		            	System.out.println("error in saving"+preparedStatement.toString());
		            }
						

		        } catch (SQLException e) {
		            e.printStackTrace();
		            System.err.println("Error while saving HS Code details: " + e.getMessage());
		            System.err.println("SQL error occurred:");
		            System.err.println("Error Code: " + e.getErrorCode());
		            System.err.println("SQL State: " + e.getSQLState());
		            System.err.println("Message: " + e.getMessage());
		        }
		    }


		    
			

			
			
			 public static void saveHsCodeDetails2(String country_name,String chapter_code,String cn_code,String descriptions) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				 c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			try {
				
				
				Statement stmt = c.createStatement();

				 stmt.execute(
					"insert into hs_codes (country_name,chapter_code, cn_code,descriptions) values('" 
							 + country_name +"','"+chapter_code+ "','" + cn_code+ "','"+ descriptions +"')");
				
				
						
						
				
				
				
				
				
			

				// closing connection
				c.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
			 }
			
	
	
	}
