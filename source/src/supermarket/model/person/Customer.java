package supermarket.model.person;

public abstract class Customer extends Person{
	public Customer(double spawnX, double spawnY){
		super(spawnX, spawnY);
	}
	
	/**
	 * Makes the customer do a checkout
	 */
	public void checkout(){
		
	}
	
	@Override
	protected void onWayPointReach(){
		System.out.println("Customer reached final destination");
		//buy product, do a checkout, leave the supermarket?
	}
}
