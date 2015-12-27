package supermarket.model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import databasemanager.DBConnection;
import databasemanager.DBSetup;
import supermarket.view.SupermarketPanel;
import supermarket.model.astar.AStar;
import supermarket.model.person.*;

public class Simulator extends SwingWorker<Void,Void>{
	SupermarketPanel panel;
	boolean simulationRunning;
	long lastFpsTime;
	int fps;
	public final int NUMCELLSX, NUMCELLSY;
	public boolean[][] occupiedCells;
	public List<Customer> customers;
	public List<Employee> employees;
	//maps a product id to a location
	HashMap<Integer,Point[]> productLocations;
	
	public Simulator(SupermarketPanel panel){
		simulationRunning = false;
		this.panel = panel;
		NUMCELLSX = 30;
		NUMCELLSY = 30;
		occupiedCells = new boolean[NUMCELLSY][NUMCELLSX];
		customers = new ArrayList<Customer>();
		employees = new ArrayList<Employee>();
	}
	
	/**
	 * Background thread for processing, called by the execute method
	 */
	public Void doInBackground(){
		simpleInitApp();
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
		// keep looping around till the game ends
		while (simulationRunning){
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			// update the frame counter
			lastFpsTime += updateLength;
			fps++;
			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000){
				System.out.println("(FPS: "+fps+")");
				lastFpsTime = 0;
				fps = 0;
			}
			// update the game logic
			simpleUpdate(delta);
			// draw everything
			simpleRender();
			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give 
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
			try{
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DBConnection.close();
		return null;
	}
	
	public void stop(){
		simulationRunning = false;
	}

	private void simpleInitApp(){
		System.out.println("simulation started");
		simulationRunning = true;
		System.out.println("Starting database connection...");
		DBConnection.open();
		System.out.println("Setting up a clean database...");
		DBSetup setup = new DBSetup();
		setup.setupNewDatabase();
		System.out.println("Setting up database succesful!");
		//occupy cells with objects standing on it
		//cheese
		for(int y = 0; y < 6; y ++){
			for(int x = 0; x < 6; x++){
				occupiedCells[y][x] = true;
			}
		}
		//freezer
		for(int y = 2; y < 6; y ++){
			for(int x = 10; x < 22; x++){
				occupiedCells[y][x] = true;
			}
		}
		//flesh
		for(int y = 0; y < 6; y ++){
			for(int x = 25; x < 30; x++){
				occupiedCells[y][x] = true;
			}
		}
		//non-food
		for(int y = 10; y < 21; y ++){
			for(int x = 0; x < 6; x++){
				occupiedCells[y][x] = true;
			}
		}
		//conserves
		for(int y = 10; y < 21; y ++){
			for(int x = 10; x < 15; x++){
				occupiedCells[y][x] = true;
			}
		}
		//chips
		for(int y = 10; y < 21; y ++){
			for(int x = 17; x < 22; x++){
				occupiedCells[y][x] = true;
			}
		}
		//alcohol
		for(int y = 10; y < 21; y ++){
			for(int x = 25; x < 30; x++){
				occupiedCells[y][x] = true;
			}
		}
		//cash desks
		for(int y = 0; y < 6; y ++)
			for(int x = 0; x < 6; x++)
				occupiedCells[y][x] = true;
		//ports
		for(int y = 0; y < 6; y ++)
			for(int x = 0; x < 6; x++)
				occupiedCells[y][x] = true;
//		List<Point> allpoints = new ArrayList<Point>();
//		for(int y = 0; y < NUMCELLSY; y ++)
//			for(int x = 0; x < NUMCELLSX; x++)
//				allpoints.add(new Point(x,y));
		customers.add(new Student(8, 8));
		customers.add(new Podge(8, 12));
//		AStar astar = new AStar(this);
//		System.out.println(astar.computeShortestPath(new Point(16,0), new Point(16,22)));
//		for(int y = 0; y < NUMCELLSY; y ++)
//			for(int x = 0; x < NUMCELLSX; x++)
//				for(Point p : allpoints){
//					AStar astar = new AStar(this);
//					List<Point> shortestPath = astar.computeShortestPath(new Point(x,y), new Point(p.x,p.y));
//					System.out.println(shortestPath);
//				}
//		AStar astar = new AStar(this);
//		System.out.println("Started calculating astar");
////		List<Point> shortestPath = astar.computeShortestPath(new Point(25,28), new Point(6,7));
//		System.out.println("occupied?" + occupiedCells[6][6] + "and " + occupiedCells[0][24]);
//		List<Point> shortestPath = astar.computeShortestPath(new Point(24,0), new Point(6,6));
//		System.out.println("calculating astar done....");
//		System.out.println("SOLUTION =======================");
//		System.out.println(shortestPath);
////		for(Point p : shortestPath){
////			System.out.println("X = " + p.x + " Y = " + p.y);
////		}
//		System.out.println("<<<<<<<<<<<<SOLUTION =======================>>>>>>>>>>>>");
	}
	
	/**
	 * Does the frequent updates
	 * @param delta how far the entities should move
	 * This is used for compensating the difference 
	 * of time between updates.
	 */
	private void simpleUpdate(double delta){
		//frequent updates
		//check if product paths or departments need refilling
		//check if a new cash desk needs to be opened
		//check if products need to be ordered
	}
	
	/**
	 * Draws everything
	 */
	private void simpleRender(){
		//draw everything
		panel.repaint();
	}
	
	public void setPanel(SupermarketPanel panel){
		this.panel = panel;
	}
}
