package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.person.Customer;
import supermarket.view.SupermarketPanel;

/**
 * A podge is bigger than a default customer
 * @author melvin
 */
public class PodgeView extends CustomerView{
	
	public PodgeView(Customer customer,SupermarketPanel panel){
		super(customer,panel,20,20);
		
	}
}
