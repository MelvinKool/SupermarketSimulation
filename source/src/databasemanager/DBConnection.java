package databasemanager;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

public class DBConnection {
	public static Connection con = null;
	/**
	 * Opens the database connection
	 * @return operation successful
	 */
	public static boolean open(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			DBConnection.con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/supermarket","supermarketuser","V3ry$tr0ng-P@$$w0rd");
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
	public static boolean close(){
		try {
			if(DBConnection.con != null || !DBConnection.con.isClosed()){
				//test.close();
				DBConnection.con.close();
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
	public static boolean isClosed() throws SQLException{
		return DBConnection.con.isClosed();
	}
}