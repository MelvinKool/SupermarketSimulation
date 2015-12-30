package supermarket.model.person;

import java.awt.Point;
import java.util.List;

public abstract class Person {
	public double x,y;
	List<Point> route;
	public Person(double spawnX, double spawnY) {
		x = spawnX;
		y = spawnY;
	}
	public abstract String toString();
	
	/**
	 * Sets the route of a person
	 * @param route
	 */
	public void setRoute(List<Point> route){
		this.route = route;
	}
	
	public void move(double delta){
		if(route == null || route.isEmpty())
			return;
		Point nextPoint = route.get(0);
//		double diffX = (double)nextPoint.x - x;
//		double diffY = (double)nextPoint.y - y;
		int diffX = nextPoint.x - (int)x;
		int diffY = nextPoint.y - (int)y;
		boolean diagonal = diffX > 0 && diffY > 0;
		double movementSpeed = diagonal ? 25 * 1.4 : 25;
		x+= delta / movementSpeed * diffX;
		y+= delta / movementSpeed * diffY;
		//if on the next tile, remove
//		System.out.println((int)x + " " + nextPoint.x + " " + (int) y + " " + nextPoint.y);
		if((int)x == nextPoint.x && (int) y == nextPoint.y){
			route.remove(0);
		}
//		if(diffX != 0 && diffY != 0){
//			//move diagonal
//		}
//		else if(diffX != 0){
//			//move horizontal
//		}
//		else if(diffY != 0){
//			//move vertical
//		}
//		else if(diffX == 0 && diffY == 0){
			//already on the tile
			
//		}
		//delete the point if completed tile
	}
}
