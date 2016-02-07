package supermarket.model;

import java.awt.Frame;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.zip.CheckedOutputStream;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import databasemanager.DBConnection;
import databasemanager.DBSetup;
import databasemanager.services.ProductService;
import supermarket.view.SupermarketFrame;
import supermarket.view.SupermarketPanel;
import supermarket.model.astar.AStar;
import supermarket.model.cashdesk.CashDesk;
import supermarket.model.person.*;
import supermarket.model.product.ProductModel;

public class Simulator extends SwingWorker<Void,Void>{
	SupermarketPanel panel;
	SupermarketFrame frame;
	boolean simulationRunning;
	long lastFpsTime;
	int fps;
	public final int NUMCELLSX, NUMCELLSY;
	public boolean[][] occupiedCells;
	public List<Customer> customers;
	public List<Employee> employees;
	public List<Checkout> checkouts;
	public List<CashDesk> cashDesks;
	List<ProductModel> products;
	boolean supermarketOpen;
	
	public Simulator(SupermarketPanel panel, SupermarketFrame frame){
		simulationRunning = false;
		this.panel = panel;
		this.frame = frame;
		NUMCELLSX = 30;
		NUMCELLSY = 30;
		occupiedCells = new boolean[NUMCELLSY][NUMCELLSX];
		customers = new ArrayList<Customer>();
		employees = new ArrayList<Employee>();
		checkouts = new ArrayList<Checkout>();
		products = new ArrayList<ProductModel>();
		cashDesks = new ArrayList<CashDesk>();
	}
	
	/**
	 * Background thread for processing, called by the execute method
	 */
	public Void doInBackground(){
		System.out.println("before");
		simpleInitApp();
		System.out.println("after");
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
		//gameloop
		while (simulationRunning){
			//work out how long its been since the last update, this
			//will be used to calculate how far the entities should
			//move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			//update the frame counter
			lastFpsTime += updateLength;
			fps++;
			//update our FPS counter if a second has passed since
			//we last recorded
			if (lastFpsTime >= 1000000000){
				System.out.println("(FPS: "+fps+")");
				lastFpsTime = 0;
				fps = 0;
			}
			//update the game logic
			simpleUpdate(delta,updateLength);
			//draw everything
			simpleRender();
			//we want each frame to take 10 milliseconds, to do this
			//we've recorded when we started the frame. We add 10 milliseconds
			//to this and then factor in the current time to give 
			//us our final value to wait for
			//remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
			try{
				Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);
			} 
			catch (InterruptedException e) {
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
		for(int y = 10; y < 20; y ++){
			for(int x = 0; x < 6; x++){
				occupiedCells[y][x] = true;
			}
		}
		//conserves
		for(int y = 10; y < 20; y ++){
			for(int x = 10; x < 15; x++){
				occupiedCells[y][x] = true;
			}
		}
		//chips
		for(int y = 10; y < 20; y ++){
			for(int x = 17; x < 22; x++){
				occupiedCells[y][x] = true;
			}
		}
		//alcohol
		for(int y = 10; y < 20; y ++){
			for(int x = 25; x < 30; x++){
				occupiedCells[y][x] = true;
			}
		}
		//cash desks
		for(int y = 22; y < 27; y++){
			for(int x = 11; x < 13; x++){
				occupiedCells[y][x] = true;
			}
			for(int x = 14; x < 15; x++){
				occupiedCells[y][x] = true;
			}
			for(int x = 17; x < 19; x++){
				occupiedCells[y][x] = true;
			}
			for(int x = 20; x < 22; x++){
				occupiedCells[y][x] = true;
			}
			for(int x = 25; x < 27; x++){
				occupiedCells[y][x] = true;
			}
		}
		for(int y = 25; y < 27; y++){
			for(int x = 27; x < 30; x++){
				occupiedCells[y][x] = true;
			}
			occupiedCells[y][13] = true;
			occupiedCells[y][19] = true;
		}
		int wallY = 22;
		for(int x = 0; x < 6; x++){
			occupiedCells[wallY][x] = true;
		}
		occupiedCells[wallY][10] = true;
		occupiedCells[23][18] = false;
		occupiedCells[23][20] = false;
		ProductService productService = new ProductService();
		products = productService.getProducts();
		cashDesks.add(new CashDesk(15,23,13,23));
		cashDesks.add(new CashDesk(16,23,18,23));
		cashDesks.add(new CashDesk(22,23,20,23));
		cashDesks.add(new CashDesk(24,23,26,23));
//		//spawn employees
		employees.add(new Employee(this,29,6));
		employees.add(new Employee(this,29,7));
		employees.add(new Employee(this,29,8));
		employees.add(new Employee(this,29,9));
		employees.add(new Employee(this,28,8));
		employees.add(new Employee(this,28,9));
//		List<Point> allpoints = new ArrayList<Point>();
//		for(int y = 0; y < NUMCELLSY; y ++)
//			for(int x = 0; x < NUMCELLSX; x++)
//				allpoints.add(new Point(x,y));
		customers.add(new Student(this, 8, 8));
//		customers.add(new Podge(this, 8, 12));
//		AStar astar = new AStar(this);
//		Customer customer = customers.get(0);
//		List<Point> shortestRoute = astar.computeShortestPath(new Point((int)customer.x, (int)customer.y), new Point(24,24));
//		customer.setRoute(shortestRoute);
//		Customer customer2 = customers.get(1);
//		List<Point> shortestRoute2 = astar.computeShortestPath(new Point((int)customer2.x, (int)customer2.y), new Point(25,25));
//		customer2.setRoute(shortestRoute2);
//		addCheckout(new Checkout("ha bieeer"));
//		addCheckout(new Checkout("lekkuur"));
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
	private void simpleUpdate(double delta, long updateTime){
		if(supermarketOpen){
			//spawn customers
			possiblySpawnRandomCustomer(delta);
		}
		//move customers
		for(Customer customer : customers){
			customer.move(delta,updateTime);
		}
		//move employees
		for(Employee employee : employees){
			employee.move(delta,updateTime);
		}
		//check if product paths or departments need refilling
		
		//check if a new cash desk needs to be opened
		long openCashdesks = cashDesks.stream().filter(c->c.isOpen() || c.employeeComing).count();
		int goalOpenCashDesks = customers.size() / 5;
		if(openCashdesks < 1 || (openCashdesks < goalOpenCashDesks && goalOpenCashDesks < cashDesks.size())){
			//open new cash desk
			openCashDesk();
		}
	}
	
	/**
	 * Draws everything
	 */
	private void simpleRender(){
		//draw everything
		panel.repaint();
	}
	
	private void possiblySpawnRandomCustomer(double delta){
		double chance = delta / 500;
		Random r = new Random();
		double randomValue = delta * r.nextDouble();
		if(randomValue > chance)
			return;
		Point spawnPoint = getRandomSpawnLocation();
		//there is no place to spawn
		if(spawnPoint == null)
			return;
		//spawn random customer
		System.out.println("Possibly spawn random customer");
		Group[] groups = Group.values();
		Group customerType = groups[r.nextInt(groups.length)];
		Customer customer = null;
		switch(customerType){
			case ALCOHOLIC:
				customer = new Alcoholic(this, spawnPoint.x, spawnPoint.y);
				break;
			case MOTHER:
				customer = new Mother(this, spawnPoint.x, spawnPoint.y);
				break;
			case PODGE:
				customer = new Podge(this, spawnPoint.x, spawnPoint.y);
				break;
			case STUDENT:
				customer = new Student(this, spawnPoint.x, spawnPoint.y);
				break;
			default:
				return;
		}
		//add products to shopping list
		
		customers.add(customer);
	}
	
	private Point getRandomSpawnLocation(){
		for(int y = NUMCELLSY - 1; y >= 0; y--){
			for(int x = 0; x < NUMCELLSX; x++){
//				check if the place is free
				if(placeFree(x,y)){
					return new Point(x,y);
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks if a place is not occupied
	 * @param x
	 * @param y
	 * @return place isn't 
	 */
	public boolean placeFree(int x, int y){
		if(occupiedCells[y][x])
			return false;
		for(Customer customer : customers){
			if((int)customer.x == x && (int)customer.y == y)
				return false;
		}
		for(Employee employee : employees){
			if((int)employee.x == x && (int)employee.y == y)
				return false;
		}
		return true;
	}
	
	public void setPanel(SupermarketPanel panel){
		this.panel = panel;
	}
	
	private void addCheckout(Checkout checkout){
		checkouts.add(checkout);
		frame.addSalesSlipToJList();
	}
	
	public List<ProductModel> getProducts(){
		return products;
	}
	
	private void openCashDesk(){
		//find employee who is not busy
		try{
			CashDesk deskToOpen = cashDesks.stream().filter(c->!c.isOpen() && !c.employeeComing).findFirst().get();
			Employee notBusyEmployee = employees.stream().filter(e->!e.isBusy()).findAny().get();
			//send employee to the cashdesk
			notBusyEmployee.goToCashDesk(deskToOpen);
		}
		catch(NoSuchElementException e){
			e.printStackTrace();
		}
	}
	
	public boolean supermarketIsOpen(){
		return supermarketOpen;
	}
	
	public void openSupermarket(){
		supermarketOpen = true;
	}
	
//	public void loadCheckouts(){
//	}
}
