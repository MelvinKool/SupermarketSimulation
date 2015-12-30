package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

import supermarket.model.person.Customer;
import supermarket.view.SupermarketPanel;

public class AlcoholicView extends CustomerView{

	public AlcoholicView(Customer customer, SupermarketPanel panel) {
		super(customer,panel,15,15, Color.red);
	}
}
