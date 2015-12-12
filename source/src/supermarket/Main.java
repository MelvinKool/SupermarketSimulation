package supermarket;

import javax.swing.SwingUtilities;

import databasemanager.DBConnection;
import supermarket.view.SupermarketFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater (() -> new SupermarketFrame());
		DBConnection conn = new DBConnection();
		conn.close();
	}
}
