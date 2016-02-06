package supermarket.model.person;

import java.awt.Point;
import java.util.List;

import supermarket.model.Simulator;
import supermarket.model.astar.AStar;

public abstract class Person {
	public double x,y;
	private int msWaitTime, lastMsSystemTime;
	private List<Point> route;
	protected Simulator simulator;
	protected AStar astar;
	
	public Person(Simulator simulator, double spawnX, double spawnY) {
		x = spawnX;
		y = spawnY;
		this.simulator = simulator;
		this.astar = new AStar(this.simulator);
	}
	
	public abstract String toString();
	
	/**
	 * Sets the route of a person
	 * @param route
	 */
	public void setRoute(List<Point> route){
		this.route = route;
	}
	
	public void move(double delta, long updateLength){
		if(route == null || route.isEmpty())
			return;
		if(msWaitTime > 0){
			msWaitTime -= updateLength / 1000000;
			return;
		}
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
		if((int)x == nextPoint.x && (int)y == nextPoint.y){
			route.remove(0);
		}
		//if the route is empty, the final waypoint has been reached
		if(route.isEmpty())
			onWayPointReach();
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
	
	/**
	 * Makes the person wait for an amount of milliseconds
	 * @param ms
	 */
	public void wait(int ms){
		msWaitTime += ms;
	}
	
	/**
	 * Do something if completed the route
	 */
	protected abstract void onWayPointReach();
}
