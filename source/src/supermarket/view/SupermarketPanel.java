package supermarket.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import supermarket.model.Simulator;

public class SupermarketPanel extends JPanel{
	public final int CELLSIZEX, CELLSIZEY;
	Simulator simulator;
	//private final Graphics DEFAULTPANELCANVAS;
	public SupermarketPanel(Simulator simulator, int panelSizeX, int panelSizeY){
		this.simulator = simulator;
		CELLSIZEX = panelSizeX / simulator.NUMCELLSX;
		CELLSIZEY = panelSizeY / simulator.NUMCELLSY;
		super.setBackground(Color.WHITE);
		//DEFAULTPANELCANVAS = drawDefaultSupermarket(g);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawDefaultSupermarket(g);
		//paint the default supermarket
//		if(paintSolutionIndex == null){
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
////				System.out.printlcleaning_materials_2__n();
//			}
//			return;
//		}
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
	
	private void drawDefaultSupermarket(Graphics g){
		BufferedImage image;
		//g.drawImage(img, x, y, observer);
		//draw a line on the y axis
		g.drawLine(CELLSIZEX * simulator.NUMCELLSX,CELLSIZEY * simulator.NUMCELLSY, CELLSIZEX * simulator.NUMCELLSX,0);
		//draw a line on the x axis
		g.drawLine(0, CELLSIZEY * simulator.NUMCELLSY, CELLSIZEX * simulator.NUMCELLSX, CELLSIZEY * simulator.NUMCELLSY);
		for(int j = 0; j < simulator.NUMCELLSY; j++){
			for(int i = 0; i < simulator.NUMCELLSX; i++){
				if(simulator.occupiedCells[j][i]){
					g.fillRect(i*CELLSIZEX, j*CELLSIZEY, CELLSIZEX, CELLSIZEY);
				}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {
		//do something with the panel
	}
	
//	/**
//	 * Draws the default graphics object of the supermarket
//	 * for drawing optimalisation
//	 * @return
//	 */
//	private Graphics drawDefaultSupermarketObject(){
//		Graphics g = null;
//		g.draw
//		return g;
//	}
//	public void placePole(int x, int y){
//		Point p = new Point(x,y);
//		if(!placedPoles.contains(p)){
//			placedPoles.add(p);
//			//draw on board
//		}
//	}
}
