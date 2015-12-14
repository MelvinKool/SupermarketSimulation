package supermarket;

import javax.swing.SwingUtilities;
import supermarket.view.SupermarketFrame;

public class Main {

	public static void main(String[] args) {
		//db connection open the whole time for performance
		SwingUtilities.invokeLater (() -> new SupermarketFrame());
	}
}
