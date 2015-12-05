package supermarket;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SupermarketPanel extends JPanel{
	Integer paintSolutionIndex;
	private final int PANELSIZE;
	public final int AMOUNT_BLOCKS;
	public final int CELLSIZE;
	public SupermarketPanel(int panelSize,int numBlocks){
		PANELSIZE = panelSize;
		AMOUNT_BLOCKS = numBlocks;
		CELLSIZE = panelSize/numBlocks;
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(paintSolutionIndex == null){
			//paint pin holes
//			g.setColor(new Color(102,51,0));
//			for(int[] pin : solver.pinPositions){
//				Point p = new Point(pin[1],pin[0]);
//				if(!(placedPole == null))
//					if(placedPole.x == p.x && placedPole.y == p.y)
//						g.setColor(Color.BLACK);
//				g.fillOval(CELLSIZE * pin[1] + CELLSIZE / 2, CELLSIZE * pin[0] + CELLSIZE /2, CELLSIZE / 2, CELLSIZE / 2);
////				if(placedPole == p)
//				g.setColor(new Color(102,51,0));
//			}
//			for(int i = 0; i < PANELSIZE; i = i + CELLSIZE){
//				for(int j = 0; j < PANELSIZE - CELLSIZE; j = j + CELLSIZE){
//					g.fillOval(j + CELLSIZE / 2, i + CELLSIZE /2, CELLSIZE / 2, CELLSIZE / 2);
//				}
//				i++;
////				System.out.println();
//			}
			return;
		}
		//paint blocks
//		g.setColor(Color.BLACK);
//		int x = 0, y = 0;
//		String hexvalue; 
//		for(int i = 0; i < PANELSIZE; i = i + CELLSIZE){
//			for(int j = 0; j < PANELSIZE; j = j + CELLSIZE){
////				System.out.println(x + " " + y);
//				int cellValue = solver.solutions.get(paintSolutionIndex)[y][x];
//				//hexvalue = Integer.toHexString(255/cellValue);
//				//g.setColor(Color.decode(hexvalue));
//				g.setColor(Color.getHSBColor(cellValue / 10f, 1, 0.9f));
////				System.out.print(cellValue + " ");
////				g.setColor(cellNumb/maxColorValue);
//				g.fillRect(j++, i, CELLSIZE, CELLSIZE);
//				x++;
//			}
//			i++;
//			x = 0;
//			y++;
//			System.out.println();
//		}
	}
	
	public void mouseClicked(MouseEvent e) {
		//do something with the panel
	}
//	public void placePole(int x, int y){
//		Point p = new Point(x,y);
//		if(!placedPoles.contains(p)){
//			placedPoles.add(p);
//			//draw on board
//		}
//	}
}
