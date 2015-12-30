package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;
import supermarket.view.SupermarketPanel;

public abstract class PaintableObject {
	protected int width, height;
	protected Color backgroundColor;
	SupermarketPanel panel;
	public PaintableObject(SupermarketPanel panel, int width, int height, Color backgroundColor){
		this.panel = panel;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
	}
	public abstract void paintObject(Graphics g);
}
