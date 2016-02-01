package databasemanager.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import databasemanager.DBConnection;
import supermarket.model.product.ProductModel;

public class ProductService {
	
	public List<ProductModel> getProducts(){
		List<ProductModel> products = new ArrayList<ProductModel>();
		Statement statement = null;
		ResultSet rs = null;
		String getProductsQuery = "SELECT P.productid, P.naam, P.prijs,GVP.groepid FROM Product AS P LEFT JOIN Groep_Voorkeur_Product AS GVP ON P.productid = GVP.productid;";
		try{
			Connection con = DBConnection.getConnection();
			if(con == null)
				return null;
			statement = con.createStatement();
			rs = statement.executeQuery(getProductsQuery);
			int lastIndex = -1;
			while(rs.next()){
				/*example: 
				 *  |         6 | Wasbenzine          |     1 |    NULL |
					|         7 | Gootsteenontstopper |     2 |    NULL |
					|         8 | Wasverzachter       |     5 |    NULL |
					|         9 | Ham-Kaas chips      |     2 |       1 |
					|         9 | Ham-Kaas chips      |     2 |       4 |

				 */
				int currentIndex = rs.getInt(1);
				if(currentIndex > lastIndex)
				{
					//insert new product
					ProductModel product = new ProductModel();
					products.add(product);
					lastIndex = currentIndex;
					//ToDo
				}
				else if(currentIndex == lastIndex){
					//insert group to last product
					if(products.size() > 0){
						int groupId = rs.getInt(4);
						if(groupId != 0)
							products.get(products.size() - 1).addGroupToProduct(groupId);
					}
				}
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			if(statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return products;
	}
	
}
