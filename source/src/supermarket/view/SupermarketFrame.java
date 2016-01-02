package supermarket.view;

/*              GUI              */
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
//////////////////////////////////

import supermarket.controller.SupermarketController;
import supermarket.model.Checkout;
import supermarket.model.Simulator;

public class SupermarketFrame extends JFrame{
	public JList<String> salesSlipList;
	DefaultListModel<String> salesSlipListModel;
	public SupermarketFrame(){
		System.out.println("Building gui...");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Supermarket simulation v1.0");
		//make a simulation
		Simulator simulator = new Simulator(null, this);
		SupermarketPanel supermarketPanel = new SupermarketPanel(simulator,800,800);
		simulator.setPanel(supermarketPanel);
		//add the controller 
		SupermarketController controller = new SupermarketController(supermarketPanel,this,simulator);
		supermarketPanel.addMouseListener(controller);
//		solvingPanel.setSize(350,350);
		JPanel statisticsPanel = new JPanel(new BorderLayout());
		salesSlipListModel = new DefaultListModel<String>();
		salesSlipList = new JList<String>(salesSlipListModel);
		salesSlipList.addMouseListener(controller);
//		salesSlipList.setSize(salesSlipList.getMaximumSize());
		//solutionPanel.add(solutionList);
		statisticsPanel.add(new JLabel("Checkouts:"),BorderLayout.NORTH);
		statisticsPanel.add(new JScrollPane(salesSlipList), BorderLayout.CENTER);
		JButton showStockBtn = new JButton("Show stock");
		showStockBtn.addActionListener (ae -> controller.actionPerformedShowStock(ae));
		statisticsPanel.add(showStockBtn, BorderLayout.SOUTH);
		JSplitPane simulationPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,statisticsPanel,supermarketPanel);
//		solutionPane.setSize(1000,700);
//		this.add(simulationPane,BorderLayout.CENTER);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Simulation", simulationPane);
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.setAlignmentX(CENTER_ALIGNMENT);
		controlPanel.setBorder (BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JButton startBtn = new JButton("Start");
//		startBtn.setEnabled(false);
		startBtn.addActionListener (ae -> controller.actionPerformedStart (ae));
		controlPanel.add(startBtn);
		JButton pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener (ae -> controller.actionPerformedPause (ae));
		controlPanel.add(pauseBtn);
		JButton stopBtn = new JButton("Stop");
		stopBtn.addActionListener (ae -> controller.actionPerformedStop(ae));
		controlPanel.add(stopBtn);
		StockPanel stockPanel = new StockPanel();
		tabbedPane.add("Stock", stockPanel);
		this.add(tabbedPane,BorderLayout.CENTER);
		this.add(controlPanel,BorderLayout.SOUTH);
//		this.pack();
		this.setSize(1086, 866);
		System.out.println("Building gui done!");
	}
	
	public void addSalesSlipToJList(){
		salesSlipListModel.addElement("Checkout " + salesSlipListModel.size());
	}
	
	public void showSalesSlip(Checkout checkout){
		JOptionPane.showMessageDialog(this, checkout);
	}
}
