package databasemanager;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
	Connection con = null;
	
	/**
	 * Opens the database connection
	 * @return operation successful
	 */
	public boolean open(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/supermarket","supermarketuser","V3ry$tr0ng-P@$$w0rd");
			return true;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException ex){
			System.out.println("sqlexception");
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Closes the connection
	 * @return operation successful
	 */
	public boolean close(){
		try {
			if(con != null || !con.isClosed()){
				//test.close();
				con.close();
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Checks whether the connection is closed.
	 * @return whether the connection is closed
	 * @throws SQLException 
	 */
	public boolean isClosed() throws SQLException{
		return con.isClosed();
	}
}