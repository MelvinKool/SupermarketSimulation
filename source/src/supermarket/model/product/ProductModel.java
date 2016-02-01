package supermarket.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
	int productId;
	String productName;
	double productPrice;
	List<Integer> preferredByGroup;
	
	public ProductModel(){
		preferredByGroup = new ArrayList<Integer>();
	}
	
	public void addGroupToProduct(int groupId){
		preferredByGroup.add(groupId);
	}
}
