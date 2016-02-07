package databasemanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import databasemanager.DBConnection;
import supermarket.model.person.Group;
import supermarket.model.product.ProductLocation;
import supermarket.model.product.ProductModel;
import supermarket.model.product.ProductType;

public class ProductService {
	
	public List<ProductModel> getProducts(){
		List<ProductModel> products = new ArrayList<ProductModel>();
		Statement statement = null;
		PreparedStatement prodLocDep = null, prodLocPath = null;
		ResultSet rs = null;
		ResultSet rsProdLocDep = null, rsProdLocPath = null;
		String getProductsQuery = "SELECT P.productid, P.naam, P.prijs,GVP.groepid FROM Product AS P LEFT JOIN Groep_Voorkeur_Product AS GVP ON P.productid = GVP.productid;";
		String getProdLocDep = "SELECT locatie FROM Product_Opgesteld_Pad WHERE productid = ?";
		String getProdLocPath = "SELECT locatie FROM Product_Opgesteld_Afdeling WHERE productid = ?";
		try{
			Connection con = DBConnection.getConnection();
			if(con == null)
				return null;
			statement = con.createStatement();
			rs = statement.executeQuery(getProductsQuery);
			int lastIndex = -1;
			while(rs.next()){
				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double productPrice = rs.getDouble(3);
				if(productId > lastIndex)
				{
					//insert new product
					ProductModel product = new ProductModel(productId,productName,productPrice);
					products.add(product);
					
					//map product to locations
					prodLocDep = con.prepareStatement(getProdLocDep);
					prodLocDep.setInt(1, productId);
					rsProdLocDep = prodLocDep.executeQuery();
					while(rsProdLocDep.next()){
						//add department location to product
						String productLocation = rsProdLocDep.getString(1);
						String[] productCoords = productLocation.split(",", -1);
						int x, y;
						if(productCoords.length > 0){
							x = Integer.parseInt(productCoords[0]);
							y = Integer.parseInt(productCoords[1]);
							product.productLocations.add(new ProductLocation(x,y, ProductType.DEPARTMENT));
						}
					}
					prodLocPath = con.prepareStatement(getProdLocPath);
					prodLocPath.setInt(1, productId);
					rsProdLocPath = prodLocPath.executeQuery();
					while(rsProdLocPath.next()){
						//add path location to product
						String productLocation = rsProdLocPath.getString(1);
						String[] productCoords = productLocation.split(",", -1);
						double x, y;
						if(productCoords.length > 0){
							x = Double.parseDouble(productCoords[0]);
							y = Double.parseDouble(productCoords[1]);
							product.productLocations.add(new ProductLocation(x,y, ProductType.PATH));
						}
					}
					lastIndex = productId;
				}
				//insert group in product
				if(products.size() > 0){
					int groupId = rs.getInt(4);
					Group[] groups = Group.values();
					if(groupId != 0 && groupId <= groups.length){
						Group group = groups[groupId - 1];
						products.get(products.size() - 1).addGroupToProduct(group);
					}
				}
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			//close resources
			if(prodLocDep != null){
				try{
					prodLocDep.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(prodLocPath != null){
				try{
					prodLocPath.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rsProdLocDep != null){
				try{
					rsProdLocDep.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(rsProdLocPath != null){
				try{
					rsProdLocPath.close();	
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return products;
	}
}
