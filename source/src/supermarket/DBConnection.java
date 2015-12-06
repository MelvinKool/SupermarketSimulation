
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
	Connection con = null;
	public void testConnection(){
		Statement test = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb:127.0.0.1:3306/SupermarktDb","b5d57a05b9073a","511f3833");
//			System.out.println("succes");
			test = con.createStatement();
			test.executeQuery("INSERT INTO supermarktdb.pad(locatie) VALUES('34,35');");
			System.out.println("Succes");
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException ex){
			System.out.println("sqlexception");
			ex.printStackTrace();
		}
		finally{
			try {
				if(con != null || !con.isClosed()){
					test.close();
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}