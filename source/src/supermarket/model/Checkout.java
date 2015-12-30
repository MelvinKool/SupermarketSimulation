package supermarket.model;

public class Checkout {
	public String productName;
	public Checkout(String productName){
		this.productName = productName;
	}
	public String toString(){
		return "Product name: "+ productName;
	}
}
