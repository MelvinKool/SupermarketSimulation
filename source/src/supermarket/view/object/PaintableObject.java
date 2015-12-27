package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;
import supermarket.view.SupermarketPanel;

public abstract class PaintableObject {
	protected int width, height;
	SupermarketPanel panel;
	public PaintableObject(SupermarketPanel panel, int width, int height){
		this.panel = panel;
		this.width = width;
		this.height = height;
	}
	public abstract void paintObject(Graphics g);
}
