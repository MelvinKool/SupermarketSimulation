package supermarket.model.person;

public class Employee extends Person{

	public Employee(double spawnX, double spawnY){
		super(spawnX, spawnY);
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
}
