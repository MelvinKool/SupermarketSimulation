package supermarket.model.person;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import supermarket.model.Simulator;
import supermarket.model.cashdesk.CashDesk;
import supermarket.model.product.NoRemainingProductException;
import supermarket.model.product.ProductLocation;
import supermarket.model.product.ProductModel;
import supermarket.model.product.ProductType;
import supermarket.model.product.ShoppingListItem;

public abstract class Customer extends Person{
	List<ShoppingListItem> shoppingList;
	List<ShoppingListItem> shoppingCart;
	boolean leavingSuperMarket;
	boolean goingToCheckout;
	public Customer(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
		this.shoppingList = new ArrayList<ShoppingListItem>();
		this.shoppingCart = new ArrayList<ShoppingListItem>();
		makeShoppingList();
		try {
			setRoute(routeToNextProduct());
		} catch (NoRemainingProductException e) {
			e.printStackTrace();
		}
		System.out.println("spawned new customer...");
	}
	
	/**
	 * Makes the customer do a checkout
	 */
	public void checkout(){
		wait(5000);
		//do the checkout
		System.out.println("doing the checkout...");
	}
	
	@Override
	protected void onWayPointReach(){
		System.out.println("Customer reached final destination");
		//buy product, do a checkout, leave the supermarket?
		List<Point> newRoute = null;
		if(shoppingList.size() > 0){
			buyProduct();
			//if the shoppinglist is still not empty, walk to the next product
			if(shoppingList.size() > 0){
				//new product route
				try {
					newRoute = routeToNextProduct();
				} catch (NoRemainingProductException e) {
					e.printStackTrace();
				}
			}
			else{
				goingToCheckout = true;
				//cash desk route
				newRoute = walkToCashDesk();
				System.out.println("done...");
			}
		}
		else if(goingToCheckout){
			//do a checkout
			System.out.println("Do a checkout");
			checkout();
			leavingSuperMarket = true;
			//exit route
			newRoute = astar.computeShortestPath(new Point((int)x, (int)y), new Point(2,29));
		}
		else if(leavingSuperMarket){
			System.out.println("Leave");
			//remove this customer from the list of customers
			simulator.customers.remove(this);
		}
		//go to new waypoint
		if(newRoute != null)
			setRoute(newRoute);
	}
	
	private void buyProduct(){
		//wait
		wait(5000);
		//buy product
		shoppingCart.add(shoppingList.get(0));
		shoppingList.remove(0);
	}
	
	private List<Point> routeToNextProduct() throws NoRemainingProductException{
		List<Point> productRoute = null;
		if(shoppingList.size() > 0){
			Random r = new Random();
			List<ProductLocation> productLocations = shoppingList.get(0).product.productLocations;
			int prodLocIndex = r.nextInt(productLocations.size());
			ProductLocation nextProductLocation = productLocations.get(prodLocIndex);
			productRoute = astar.computeShortestPath(new Point((int)x, (int)y), new Point((int)nextProductLocation.x,(int)nextProductLocation.y));
		}
		else{
			throw new NoRemainingProductException();
		}
		return productRoute;
	}
	
	protected void makeShoppingList(){
		List<ProductModel> preferredProducts = getPrefferedProducts();
		for(int i = 0; i < preferredProducts.size(); i++){
			ProductModel randomProduct = getRandomProduct(preferredProducts);
			if(!shoppingList.stream().anyMatch(p->p.product == randomProduct)){
				Random r = new Random();
				int amount; 
				if(randomProduct.productLocations.stream().anyMatch(p->p.getProductType() == ProductType.PATH))
					amount = r.nextInt(4);
				else
					amount = r.nextInt(500);
				shoppingList.add(new ShoppingListItem(randomProduct, amount));
				System.out.println("adding product...");
			}
		}
	}
	
	/**
	 * get a random product this customer prefers
	 * @return a random preferred product
	 */
	protected ProductModel getRandomProduct(List<ProductModel> products){
		ProductModel product = null;
		Random random = new Random();
		int randomProdIndex = random.nextInt(products.size());
		product = products.get(randomProdIndex);
		return product;
	}
	
	private List<ProductModel> getPrefferedProducts(){
		return simulator.getProducts().stream().filter(p -> p.getPreferredByGroups().contains(getCustomerGroup())).collect(Collectors.toList());
	}
	
	private Group getCustomerGroup(){
		Group group = null;
		if(this instanceof Alcoholic){
			group = Group.ALCOHOLIC;
		}
		else if(this instanceof Mother){
			group =  Group.MOTHER;
		}
		else if(this instanceof Podge){
			group =  Group.PODGE;
		}
		else if(this instanceof Student){
			group = Group.STUDENT;
		}
		return group;
	}
	
	private List<Point> walkToCashDesk(){
		List<Point> cashDeskRoute = null;
		List<CashDesk> openCashDesks = simulator.cashDesks.stream().filter(c -> c.isOpen()).collect(Collectors.toList());
		if(openCashDesks.size() > 0){
			Random r = new Random();
			int goToCashDesk = r.nextInt(openCashDesks.size());
			CashDesk goalCashDesk = openCashDesks.get(goToCashDesk);
			cashDeskRoute = astar.computeShortestPath(new Point((int)x, (int)y), new Point(goalCashDesk.x,goalCashDesk.y));
		}
		return cashDeskRoute;
	}
}
