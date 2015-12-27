package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;
import supermarket.view.SupermarketPanel;

import supermarket.model.person.Employee;

public class EmployeeView extends PersonView{
	Employee employee;
	
	public EmployeeView(Employee employee, SupermarketPanel panel, int width, int height){
		super(employee,panel,width,height);
		this.employee = employee;
	}

	
	

}
