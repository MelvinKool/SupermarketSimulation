package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.customer.Customer;

public abstract class CustomerView extends PaintableObject{
	Customer customer;
	
	public CustomerView(Customer customer){
		super(15,15);
		this.customer = customer;
	}
	
	protected CustomerView(Customer customer, int width, int height){
		super(width,height);
		this.customer = customer;
	}

//	@Override
//	public void paintObject(Graphics g,int x, int y, Color color) {
////		g.fillOval(x, y, width, height);
//	}
	
	@Override
	protected void paintObject(Graphics g, int x, int y, int height, int width, Color color) {
		// TODO Auto-generated method stub
		
	}

}
