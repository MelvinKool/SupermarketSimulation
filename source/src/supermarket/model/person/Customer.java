package supermarket.model.person;

import java.awt.Point;
import java.util.List;

import supermarket.model.Simulator;

public abstract class Customer extends Person{
	public Customer(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
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
		if(true){
			buyProduct();
		}
//		else if(shoulddoacheckout){
//			//do a checkout
//		}
		//go to new waypoint
		List<Point> shortestRoute = astar.computeShortestPath(new Point((int)x, (int)y), new Point(7,0));
		setRoute(shortestRoute);
	}
	
	private void buyProduct(){
		//wait
		wait(5000);
		//buy product
		
	}
}
