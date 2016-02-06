package supermarket.model.product;

public class ShoppingListItem {
	public ProductModel product;
	public int amount;

	public ShoppingListItem(ProductModel product, int amount){
		this.product = product;
		this.amount = amount;
	}
}
