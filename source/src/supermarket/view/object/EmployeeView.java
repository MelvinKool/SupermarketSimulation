package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.customer.Employee;

public class EmployeeView extends PaintableObject{
	Employee employee;
	
	public EmployeeView(Employee employee){
		this.employee = employee;
	}
	
	@Override
	public void paintObject(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintObject(Graphics g, int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintObject(Graphics g, int x, int y, int height, int width, Color color) {
		// TODO Auto-generated method stub
		
	}

}
