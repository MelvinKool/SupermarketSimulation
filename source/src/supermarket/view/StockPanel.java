package supermarket.view;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StockPanel extends JPanel{
	JTable stockDataDepartment, stockDataPath;
	Object rowData[][];
	public StockPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//id, name ,department name, weight department, weight warehouse, total weight
		String[] columnNamesDepartment = {"Productid","Name","Department name","Weight department","Weight warehouse","Total weight"};
		Vector<Vector> rowData = new Vector<Vector>();
//		stockDataDepartment = new JTable(rowData,columnNamesDepartment);
		this.add(stockDataDepartment);
//		String[] columnNamesPath = {"Productid","Name","Department name","Weight department","Weight warehouse","Total weight"};
//		stockDataPath = new JTable(columnNamesPath);
		
	}
	public void refreshstockDataDepartment(){
		
	}
	public void refreshstockDataPath(){
		
	}
}
