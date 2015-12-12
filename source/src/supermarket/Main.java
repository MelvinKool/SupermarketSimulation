package supermarket;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import databasemanager.DBConnection;
import supermarket.view.SupermarketFrame;

public class Main {

	public static void main(String[] args) {
		//db connection open the whole time for performance
		DBConnection.open();
		SwingUtilities.invokeLater (() -> new SupermarketFrame());
		DBConnection.close();
	}
}
