package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.person.Customer;
import supermarket.view.SupermarketPanel;

public abstract class CustomerView extends PersonView{
	Customer customer;
	
	protected CustomerView(Customer customer, SupermarketPanel panel,int width, int height, Color backgroundColor){
		super(customer,panel,width,height, backgroundColor);
		this.customer = customer;
	}
}
