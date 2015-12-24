package supermarket;

import javax.swing.SwingUtilities;
import supermarket.view.SupermarketFrame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater (() -> new SupermarketFrame());
	}
}
