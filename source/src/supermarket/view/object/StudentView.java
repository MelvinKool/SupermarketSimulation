package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.customer.Customer;

public class StudentView extends CustomerView{

	public StudentView(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintObject(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.fillOval(x, y, C, height);
	}

	@Override
	protected void paintObject(Graphics g, int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}

}
