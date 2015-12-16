package supermarket.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingWorker.StateValue;

import supermarket.model.Simulator;
import supermarket.view.SupermarketFrame;
import supermarket.view.SupermarketPanel;

public class SupermarketController implements MouseListener{
	SupermarketPanel supermarketView;
	SupermarketFrame frame;
	Simulator simulation;
	public SupermarketController(SupermarketPanel board, SupermarketFrame frame, Simulator simulation) {
		this.supermarketView = board;
		this.frame = frame;
		this.simulation = simulation;
	}
	
	//start button
	public void actionPerformedSolve (ActionEvent ae){
		System.out.println("Start button clicked");
		//start the supermarket
		//simulation = new Simulator();
		if(simulation.isDone()){
			System.out.println("Run another time???");
		}
		else if(simulation.getState() == StateValue.STARTED){
			System.out.println("Simulation is running, close and start a new one?");
		}
		else{
			simulation.execute();
		}
	}
	
	//pause button
	public void actionPerformedPause (ActionEvent ae){
		System.out.println("Pause button clicked");
//		if(simulation.getState() == StateValue.STARTED)
		simulation.b = !simulation.b;
		System.out.println(simulation.b);
	}
		
	//stop button
	public void actionPerformedStop (ActionEvent ae){
		System.out.println("Stop button clicked");
		simulation.stop();
	}
	
	//board panel clicked
	public void boardClicked(MouseEvent e){
		System.out.println("Board clicked");
		//calculate y and x
		int x = e.getX() / supermarketView.CELLSIZEX;
		int y = e.getY() / supermarketView.CELLSIZEY;
		System.out.println("X = " + x + " Y = " + y);
	}
	
	//solution list clicked
	public void solutionlistClicked(MouseEvent e){
		System.out.println("Checkout list clicked");
		//view the sales slip
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof javax.swing.JPanel)
			boardClicked(e);
		else if(e.getSource() instanceof javax.swing.JList<?>)
			solutionlistClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
