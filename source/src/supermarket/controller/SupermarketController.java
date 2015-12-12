package supermarket.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import supermarket.model.Simulator;
import supermarket.view.SupermarketFrame;
import supermarket.view.SupermarketPanel;

public class SupermarketController implements MouseListener{
	SupermarketPanel board;
	SupermarketFrame frame;
	Simulator simulation;
	public SupermarketController(SupermarketPanel board, SupermarketFrame frame, Simulator simulation) {
		//add the board panel for setting focus etc.
		this.board = board;
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
		if(simulation.getState() == StateValue.STARTED){
			try {
				simulation.wait(999999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	//stop button
	public void actionPerformedStop (ActionEvent ae){
		System.out.println("Stop button clicked");
		simulation.stop();
	}
	
	//board panel clicked
	public void boardClicked(MouseEvent e){
		System.out.println("Board clicked");
		//determine weather a pole could be placed
		//calculate x and y coordinates of pole
		int x = ((e.getX() - board.CELLSIZEX / 2) / board.CELLSIZEX > 0) ? (e.getX() - board.CELLSIZEX / 2) / board.CELLSIZEX : 0;
		int y = ((e.getY() - board.CELLSIZEY / 2) / board.CELLSIZEY > 0) ? (e.getY() - board.CELLSIZEY  /2)/ board.CELLSIZEY : 0;
		System.out.println("x = " + x + "y = "+ y);
		Point p = new Point(x,y);
		if(x < simulation.NUMCELLSX && y < simulation.NUMCELLSY){
			//place pole on the board and draw pole
			//draw the pole
			board.repaint();
		}
	}
	
	//solution list clicked
	public void solutionlistClicked(MouseEvent e){
		System.out.println("Solution list clicked");
		//show the selected solution
//		JList templist = (JList)e.getSource();
		//board.paintSolutionIndex = frame.solutionList.getSelectedIndex();
		board.repaint();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
