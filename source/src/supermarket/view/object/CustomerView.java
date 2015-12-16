package supermarket.view.object;

import java.awt.Graphics;

import supermarket.model.customer.Customer;

public class CustomerView extends PaintableObject{
	Customer customer;
	
	public CustomerView(Customer customer){
		this.customer = customer;
	}
	
	@Override
	public void paintObject(Graphics g,int x, int y) {
		
	}

}
