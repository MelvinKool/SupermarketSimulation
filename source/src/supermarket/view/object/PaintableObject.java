package supermarket.view.object;

import java.awt.Color;
import java.awt.Graphics;

public abstract class PaintableObject {
	public int width, height;
	public PaintableObject(int width, int height){
		this.width = width;
		this.height = height;
	}
	public abstract void paintObject(Graphics g,int x, int y);
	protected abstract void paintObject(Graphics g,int x, int y, Color color);
	protected abstract void paintObject(Graphics g,int x, int y, int height, int width, Color color);
}
