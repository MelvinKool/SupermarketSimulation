package databasemanager;

import java.sql.*;
import java.util.Vector;

public class DBStatement {
	private static Vector<Vector> getStock(String query) throws SQLException{
		Vector<Vector> departmentStock = null;
		Statement statement = null;
		ResultSet rs = null;
		final String getDepartmentStockQuery = query;
		try{
			Connection con = DBConnection.getConnection();
			if(con == null)
				return null;
			statement = con.createStatement();
			rs = statement.executeQuery(getDepartmentStockQuery);
			departmentStock = new Vector<Vector>();
			Vector temp;
			while(rs.next()){
				temp = new Vector();
				int productid,weightPA,weightM,totalWeight;
				String productName,departmentName;
				productid = rs.getInt(1);
				productName = rs.getString(2);
				departmentName = rs.getString(3);
				weightPA = rs.getInt(4);
				weightM = rs.getInt(5);
				totalWeight = rs.getInt(6);
				temp.add(productid);
				temp.add(productName);
				temp.add(departmentName);
				temp.add(weightPA);
				temp.add(weightM);
				temp.add(totalWeight);
				departmentStock.add(temp);
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			if(statement != null)
				statement.close();
			if(rs != null)
				rs.close();
		}
		return departmentStock;
	}
	
	public static Vector<Vector> getDepartmentStock(){
		try {
			return getStock("SELECT P.productid, P.naam, A.naam, PA.gewicht, M.gewicht, PA.gewicht + M.gewicht "+
					"FROM Product AS P "+ 
					"INNER JOIN Product_Opgesteld_Afdeling AS PA ON P.productid = PA.Productid "+
					"INNER JOIN Afdeling AS A ON PA.afdelingid = A.afdelingid "+
					"INNER JOIN Magazijn AS M ON M.productid = P.productid;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Vector<Vector> getPathStock(){
		try {
			return getStock("SELECT P.productid, P.naam, A.naam, PA.aantal, M.aantal, PA.aantal + M.aantal "+
								"FROM Product AS P "+ 
								"INNER JOIN Product_Opgesteld_Pad AS PA ON P.productid = PA.Productid "+
								"INNER JOIN Pad AS A ON PA.padid = A.padid "+
								"INNER JOIN Magazijn AS M ON M.productid = P.productid;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
