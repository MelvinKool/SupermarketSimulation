package supermarket.model.person;

import supermarket.model.Simulator;

public class Employee extends Person{

	public Employee(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Employee";
	}
	
	@Override
	protected void onWayPointReach(){
		System.out.println("Employee reached final destination");
		//fill product?
	}
	
	public void startFilling(){
		
	}
	
	public void sitAtCashDesk(){
		
	}
	
}
