package supermarket.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import supermarket.controller.SupermarketController;

public class StockPanel extends JPanel{
	final Vector<String> columnNamesDepartment = new Vector<String>(Arrays.asList(new String[] {"Productid","Name","Department name","Weight department","Weight warehouse","Total weight"}));
	final Vector<String> columnNamesPath = new Vector<String>(Arrays.asList(new String[] {"Productid","Name","Path name","Amount Path","Amount warehouse","Total amount"}));
	JTable stockDataDepartmentTable, stockDataPathTable;
	DefaultTableModel stockDataDepartmentModel, stockDataPathModel;
	Object rowDataDepartment[][];
	Object rowDataPath[][];
	public StockPanel(SupermarketController controller){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//id, name ,department name, weight department, weight warehouse, total weight
		stockDataDepartmentModel = new DefaultTableModel(columnNamesDepartment, 0);
		stockDataDepartmentTable = new JTable(stockDataDepartmentModel);
		stockDataDepartmentTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		stockDataDepartmentTable.setFillsViewportHeight(true);
		JScrollPane departmentScrollPane = new JScrollPane(stockDataDepartmentTable);
		JPanel departmentPanel = new JPanel(new BorderLayout());
		departmentPanel.add(departmentScrollPane,BorderLayout.CENTER);
		JButton updateDepartmentStockBtn = new JButton("Update department stock");
		updateDepartmentStockBtn.addActionListener (ae -> controller.actionPerformedLoadStockDepartment(ae));
		departmentPanel.add(updateDepartmentStockBtn,BorderLayout.SOUTH);
		this.add(departmentPanel);
		stockDataPathModel = new DefaultTableModel(columnNamesPath, 0);
		stockDataPathTable = new JTable(stockDataPathModel);
		stockDataPathTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		stockDataPathTable.setFillsViewportHeight(true);
		JScrollPane pathScrollPane = new JScrollPane(stockDataPathTable);
		JPanel pathStockPanel = new JPanel(new BorderLayout());
		pathStockPanel.add(pathScrollPane,BorderLayout.CENTER);
		JButton updatePathStockBtn = new JButton("Update path stock");
		updatePathStockBtn.addActionListener (ae -> controller.actionPerformedLoadStockPath(ae));
		pathStockPanel.add(updatePathStockBtn,BorderLayout.SOUTH);
		this.add(pathStockPanel);
//		stockDataPath = new JTable(columnNamesPath);
	}
	
	public void refreshStockDataDepartment(Vector<Vector> data){
		stockDataDepartmentModel.setDataVector(data, columnNamesDepartment);
	}
	
	public void refreshStockDataPath(Vector<Vector> data){
		stockDataPathModel.setDataVector(data, columnNamesPath);
	}
}
