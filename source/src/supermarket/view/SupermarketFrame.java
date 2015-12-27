package supermarket.view;

/*              GUI              */
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
//////////////////////////////////

import supermarket.controller.SupermarketController;
import supermarket.model.Simulator;

public class SupermarketFrame extends JFrame{
	JList<String> solutionList;
	DefaultListModel<String> solutionListModel;
	public SupermarketFrame(){
		System.out.println("Building gui...");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Supermarket simulation v1.0");
		//make a simulation
		Simulator simulator = new Simulator(null);
		SupermarketPanel supermarketPanel = new SupermarketPanel(simulator,800,800);
		simulator.setPanel(supermarketPanel);
		//add the controller 
		SupermarketController controller = new SupermarketController(supermarketPanel,this,simulator);
		supermarketPanel.addMouseListener(controller);
//		solvingPanel.setSize(350,350);
		JPanel solutionPanel = new JPanel();
		solutionListModel = new DefaultListModel<String>();
		solutionList = new JList<String>(solutionListModel);
		solutionList.addMouseListener(controller);
		//solutionPanel.add(solutionList);
		solutionPanel.add(new JScrollPane(solutionList));
		JSplitPane solutionPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,solutionPanel,supermarketPanel);
//		solutionPane.setSize(1000,700);
		this.add(solutionPane,BorderLayout.CENTER);
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.setAlignmentX(CENTER_ALIGNMENT);
		controlPanel.setBorder (BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JButton startBtn = new JButton("Start");
//		startBtn.setEnabled(false);
		startBtn.addActionListener (ae -> controller.actionPerformedSolve (ae));
		controlPanel.add(startBtn);
		JButton pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener (ae -> controller.actionPerformedPause (ae));
		controlPanel.add(pauseBtn);
		JButton stopBtn = new JButton("Stop");
		stopBtn.addActionListener (ae -> controller.actionPerformedStop(ae));
		controlPanel.add(stopBtn);
		this.add(controlPanel,BorderLayout.SOUTH);
//		this.pack();
		this.setSize(1086, 866);
		System.out.println("Building gui done!");
	}
	
	public void addSolutionToJList(){
		solutionListModel.addElement("Solution " + solutionListModel.size());
	}
}
