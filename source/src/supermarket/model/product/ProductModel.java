package supermarket.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
	int productId;
	String productName;
	double productPrice;
	List<Integer> preferredByGroup;
	public List<ProductLocation> productLocations;
	
	public ProductModel(){
		preferredByGroup = new ArrayList<Integer>();
		productLocations = new ArrayList<ProductLocation>();
	}
	
	public void addGroupToProduct(int groupId){
		preferredByGroup.add(groupId);
	}
	
	public int getProductId(){
		return productId;
	}
}
