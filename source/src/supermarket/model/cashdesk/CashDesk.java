package supermarket.model.cashdesk;

import supermarket.model.person.Employee;

public class CashDesk {
	public int x, y;
	public int employeeX, employeeY;
	boolean isOpen;
	public boolean employeeComing;
	Employee employee;
	public CashDesk(int x, int y, int employeeX, int employeeY){
		this.x = x;
		this.y = y;
		this.employeeX = employeeX;
		this.employeeY = employeeY;
	}
	
	public void setOpen(boolean state, Employee employee){
		isOpen = state;
		employee = employee;
	}
	
	public boolean isOpen(){
		return isOpen;
	}
	
	public Employee getEmployee(){
		return employee;
	}
}
