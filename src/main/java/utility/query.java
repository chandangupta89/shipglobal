package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class query {

	Connection c;

	public static String getGstrate() {
		 String Gst_rate = null;
		Connection c;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			c = DriverManager.getConnection("jdbc:mysql://shipglobal-devteam.cq4ko0wbxziv.ap-south-1.rds.amazonaws.com:3306/qa1","qauser", "SK7bualr5mMy(X[s");

			Statement stmt = c.createStatement();

			ResultSet rs=	stmt.executeQuery(
					"SELECT j.start_date, j.tax_percentage FROM order_total_type CROSS JOIN JSON_TABLE(order_total_type.order_total_tax_rate,'$[*]' COLUMNS (start_date INT PATH '$.start_date', tax_percentage INT PATH '$.tax_percentage')) AS j WHERE order_total_type.order_total_code='LOGISTIC_FEE' AND j.start_date < NOW() ORDER BY j.start_date DESC LIMIT 1;");

			while (rs.next()) {
	               
                 Gst_rate = rs.getString("tax_percentage");
			
			}
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
		return Gst_rate;

	}
	public static List<String> getOrdersDetails(String tracking) {
		
		Connection c;
		List<String> obj = new ArrayList<String>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			c = DriverManager.getConnection("jdbc:mysql://shipglobal-devteam.cq4ko0wbxziv.ap-south-1.rds.amazonaws.com:3306/qa1","qauser", "SK7bualr5mMy(X[s");

			Statement stmt = c.createStatement();

			ResultSet rs=	stmt.executeQuery(
					"SELECT  partner_connect_id, partner_connect_awb,partner_lastmile_id,partner_lastmile_awb,lastmile_label,order_status_id,order_manifest_id from orders WHERE tracking = '" + tracking + "'");

			while (rs.next()) {
	               
              String partner_connectId = rs.getString("partner_connect_id");
              String partner_connectId_awb = rs.getString("partner_connect_awb");
              String partner_lastmile_id = rs.getString("partner_lastmile_id");
              String partner_connect_awb = rs.getString("partner_connect_awb");
              String lastmile_label = rs.getString("lastmile_label");
              String orderstatusid = rs.getString("order_status_id");
              String order_manifest_id = rs.getString("order_manifest_id");
            
              
              obj.add(partner_connectId);
              obj.add( partner_connectId_awb);
              obj.add(partner_lastmile_id);
              obj.add( partner_connect_awb);
              obj.add( lastmile_label);
              obj.add(orderstatusid);
              obj.add(order_manifest_id);
              
              
              
              
              
             
			}
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
		return obj;
	
	
	}
	
	public static List<String> getorderStatusId(String tracking) {
		
		Connection c;
		List<String> obj = new ArrayList<String>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			c = DriverManager.getConnection("jdbc:mysql://shipglobal-devteam.cq4ko0wbxziv.ap-south-1.rds.amazonaws.com:3306/qa1","qauser", "SK7bualr5mMy(X[s");

			Statement stmt = c.createStatement();

			ResultSet rs=	stmt.executeQuery(
					"SELECT  order_status_id from orders WHERE tracking = '" + tracking + "'");

			while (rs.next()) {
	               
              String order_status_id = rs.getString("order_status_id");
             
              
              obj.add(order_status_id);
             
             
			}
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
		return obj;

	}
	
public static List<String> getManifestDetails() {
		
		Connection c;
		List<String> obj = new ArrayList<String>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			c = DriverManager.getConnection("jdbc:mysql://shipglobal-devteam.cq4ko0wbxziv.ap-south-1.rds.amazonaws.com:3306/qa1","qauser", "SK7bualr5mMy(X[s");

			Statement stmt = c.createStatement();

			ResultSet rs=	stmt.executeQuery(
					"SELECT * FROM manifest order by manifest_id DESC limit 1;");

			while (rs.next()) {
	               
              String manifest_id = rs.getString("manifest_id");
              String manifest_code = rs.getString("manifest_code");
              String manifest_count = rs.getString("manifest_count");
              String manifest_status = rs.getString("manifest_status");
             
              
              obj.add(manifest_id);
              obj.add( manifest_code);
              obj.add(manifest_count);
              obj.add( manifest_status);
           
              
              
              
              
              
              
             
			}
			
			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
		return obj;
	
	
	}
	
public static List<String> getorderId() {
	
	Connection c;
	List<String> obj = new ArrayList<String>();

	try {

		Class.forName("com.mysql.jdbc.Driver");

		c = DriverManager.getConnection("jdbc:mysql://shipglobal-devteam.cq4ko0wbxziv.ap-south-1.rds.amazonaws.com:3306/qa1","qauser", "SK7bualr5mMy(X[s");

		Statement stmt = c.createStatement();

		ResultSet rs=	stmt.executeQuery("SELECT * FROM orders order by order_id  desc limit 1;");

		while (rs.next()) {
               
          String order_id = rs.getString("order_id");
          String order_status_id = rs.getString("order_status_id");
          String tracking = rs.getString("tracking");
          
          obj.add(order_id);
          obj.add(order_status_id);
          obj.add(tracking);
         
		}
		
		c.close();

	} catch (Exception e) {

		e.printStackTrace();

		System.err.println(e.getClass().getName() + ": " + e.getMessage());

	}
	return obj;

}



	
}
