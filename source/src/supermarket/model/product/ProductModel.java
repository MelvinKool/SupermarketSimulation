package supermarket.model.product;

import java.util.ArrayList;
import java.util.List;

import supermarket.model.person.Group;

public class ProductModel {
	int productId;
	String productName;
	double productPrice;
	List<Group> preferredByGroups;
	public List<ProductLocation> productLocations;
	
	public ProductModel(int productId, String productName, double productPrice){
		preferredByGroups = new ArrayList<Group>();
		productLocations = new ArrayList<ProductLocation>();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public void addGroupToProduct(Group groupId){
		preferredByGroups.add(groupId);
	}
	
	public List<Group> getPreferredByGroups(){
		return preferredByGroups;
	}
	
	public int getProductId(){
		return productId;
	}
}
