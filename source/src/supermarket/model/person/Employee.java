package supermarket.model.person;

import java.awt.Point;
import java.util.List;

import supermarket.model.Simulator;
import supermarket.model.cashdesk.CashDesk;

public class Employee extends Person{
	boolean walkingToCashDesk, isFilling, sittingAtCashDesk;
	CashDesk cashDesk;
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
		if(walkingToCashDesk){
			openCashDesk(cashDesk);
		}
	}
	
	public boolean isBusy(){
		return currentlyFilling() || walkingToCashDesk || sittingAtCashDesk;
	}
	
	public void startFilling(){
		walkingToCashDesk = false;
		cashDesk = null;
		//start filling
	}
	
	private boolean currentlyFilling(){
		return isFilling;
	}
	
	public void goToCashDesk(CashDesk cashdesk){
		List<Point> routeToCashDesk = astar.computeShortestPath(new Point((int)x, (int)y), new Point(cashdesk.employeeX,cashdesk.employeeY));
		setRoute(routeToCashDesk);
		walkingToCashDesk = true;
		cashdesk.employeeComing = true;
		this.cashDesk = cashdesk;
	}
	
	private void openCashDesk(CashDesk cashdesk){
		cashdesk.setOpen(true, this);
		walkingToCashDesk = false;
		sittingAtCashDesk = true;
		cashdesk.employeeComing = false;
		if(!simulator.supermarketIsOpen()){
			simulator.openSupermarket();
		}
	}
}
