package supermarket.model.product;

public class ProductLocation {
	public double x, y;
	ProductType productType;
	public ProductLocation(double x, double y, ProductType productType){
		this.x = x;
		this.y = y;
		this.productType = productType;
	}
	
	public ProductType getProductType(){
		return productType;
	}
}
