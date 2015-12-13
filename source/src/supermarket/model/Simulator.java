package supermarket.model;

import javax.swing.SwingWorker;

import databasemanager.DBConnection;
import databasemanager.DBSetup;
import supermarket.view.SupermarketPanel;

public class Simulator extends SwingWorker<Void,Void>{
	SupermarketPanel panel;
	boolean simulationRunning, simulationPaused;
	long lastFpsTime;
	int fps;
	public final int NUMCELLSX = 100, NUMCELLSY = 100;
	public Simulator(SupermarketPanel panel){
		simulationRunning = false;
		this.panel = panel;
	}
	
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
	
	public void pause(){
		simulationPaused = true;
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
	}
	
	/**
	 * Does the frequent updates
	 * @param delta
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
