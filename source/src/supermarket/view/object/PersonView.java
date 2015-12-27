package supermarket.view.object;

import java.awt.Graphics;

import supermarket.model.person.Person;
import supermarket.view.SupermarketPanel;

public abstract class PersonView extends PaintableObject{
	Person person;
	
	public PersonView(Person person, SupermarketPanel panel, int width, int height) {
		super(panel,width,height);
		this.person = person;
	}
	
	public void paintObject(Graphics g){
		g.fillOval(panel.calculatePosX(person.x), panel.calculatePosY(person.y), width, height);
	}
}
