package supermarket.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import supermarket.model.Simulator;
import supermarket.model.person.*;
import supermarket.view.object.*;

public class SupermarketPanel extends JPanel{
	public final double CELLSIZEX, CELLSIZEY;
	public static BufferedImage flesh,beer,captainMorgan,chips,nonFood,corn,pizza,sale,spam;
	Simulator simulator;
	//private final Graphics DEFAULTPANELCANVAS;
	public SupermarketPanel(Simulator simulator, int panelSizeX, int panelSizeY){
		this.simulator = simulator;
		CELLSIZEX = (double)panelSizeX / (double)simulator.NUMCELLSX;
		CELLSIZEY = (double)panelSizeY / (double)simulator.NUMCELLSY;
		System.out.println("CELLSIZE : " + CELLSIZEX + "  " + CELLSIZEY);
		super.setBackground(Color.WHITE);
		initImages();
		//DEFAULTPANELCANVAS = drawDefaultSupermarket(g);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintDefaultSupermarket(g);
		paintPersons(g);
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
//				i++;/home/melvin/workspace
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
	
	/**
	 * Paints the supermarket
	 * @param g
	 */
	private void paintDefaultSupermarket(Graphics g){
		paintImageRepeatedInRegion(g, SupermarketPanel.beer, 50, 50, 200, 500);
		//draw a line on the y axis
		int outerX, outerY;
		outerX = calculatePosX(simulator.NUMCELLSX);
		outerY = calculatePosY(simulator.NUMCELLSY);
		//vertical line
		g.drawLine(outerX,0, outerX,outerY);
		//horizontal line
		g.drawLine(0, outerY, outerX, outerY);
//		for(int j = 0; j < simulator.NUMCELLSY; j++){
//			for(int i = 0; i < simulator.NUMCELLSX; i++){
//				if(simulator.occupiedCells[j][i]){
//					g.fillRect(calculatePosX(i), calculatePosY(j), (int)CELLSIZEX, (int)CELLSIZEY);
//				}
//			}
//		}
	}
	
	/**
	 * Paints all persons on the panel
	 * @param g
	 */
	private void paintPersons(Graphics g){
		CustomerView customerView;
		for(Customer customer : simulator.customers){
//			customerView = (CustomerView)determineView(customer);
			//paint customer
			customerView = (CustomerView)determineView(customer);
			if(customerView == null)
				continue;
			customerView.paintObject(g);
//			customerView.paintObject(g, (int)customer.x * CELLSIZEX, (int)customer.y * CELLSIZEY);
		}
		EmployeeView employeeView;
		for(Employee employee : simulator.employees){
			//paint employee
			employeeView = (EmployeeView)determineView(employee);
			if(employeeView == null)
				continue;
			employeeView.paintObject(g);
		}
	}
	
	private void paintImageRepeatedInRegion(Graphics g, BufferedImage img,int x1,int y1,int x2,int y2){
		int imgHeight = img.getWidth(this);
		int imgWidth = img.getWidth(this);
		int remainingWidth = x2 - x1;
		int remainingHeight = y2 - y1;
		Image subImageX = null, subImageY = null,subImageXY;
		boolean subImageCreatedX = false, subImageCreatedY = false;
		if(imgWidth > 0 && imgHeight > 0)
			for(int y = y1; y < y2; y += y2 - y > imgHeight ? imgHeight : remainingHeight){
				remainingHeight = y2 -y;
				for(int x = x1; x < x2; x += x2 - x > imgWidth ? imgWidth : remainingWidth){
					remainingWidth = x2 - x;
					if(remainingWidth < imgWidth && remainingHeight < imgHeight){
						subImageXY = img.getSubimage(0, 0, remainingWidth, remainingHeight);
						g.drawImage(subImageXY, x, y, remainingWidth, remainingHeight, this);
					}
					else if(remainingWidth < imgWidth){
						if(!subImageCreatedX){
							subImageX = img.getSubimage(0, 0, remainingWidth, imgHeight);
							subImageCreatedX = true;
						}
						g.drawImage(subImageX, x, y, remainingWidth, imgHeight, this);
					}
					else if(remainingHeight < imgHeight){
						if(!subImageCreatedY){
							subImageY = img.getSubimage(0, 0, imgWidth, remainingHeight);
							subImageCreatedY = true;
						}
						g.drawImage(subImageY, x, y, imgWidth, remainingHeight, this);
					}
					else{
						g.drawImage(img, x, y, imgWidth, imgHeight, this);
					}
//					g.drawImage(img, x, y, imgWidth < remainingWidth ? imgWidth : remainingWidth, imgHeight < remainingHeight ? imgHeight : remainingHeight, 0, 0, imgWidth < remainingWidth ? imgWidth : remainingWidth, imgHeight < remainingHeight ? imgHeight : remainingHeight, this);
				}
			}
	}
	/**
	 * Determines which view to return
	 * @param person
	 * @return the view of the person
	 */
	private PaintableObject determineView(Person person){
		if(person instanceof Alcoholic)
			return new AlcoholicView((Alcoholic)person,this);
		else if(person instanceof Employee)
			return new EmployeeView((Employee) person, this);
		else if(person instanceof Mother)
			return new MotherView((Mother) person, this);
		else if(person instanceof Podge)
			return new PodgeView((Podge) person, this);
		else if(person instanceof Student)
			return new StudentView((Student)person,this);
		return null;
	}
	
	private void initImages(){
		try {
//			System.out.println(System.getProperty("user.dir"));
			flesh = ImageIO.read(new File("Content/Images/Bacon-Sprite.png"));
			beer = ImageIO.read(new File("Content/Images/beer-sprite.png"));
			captainMorgan = ImageIO.read(new File("Content/Images/captain-morgan.png"));
			chips = ImageIO.read(new File("Content/Images/Chips - Lays-sprite.png"));
			nonFood = ImageIO.read(new File("Content/Images/cleaning-sprite.png"));
			corn = ImageIO.read(new File("Content/Images/crispy_mais-sprite.png"));
			pizza = ImageIO.read(new File("Content/Images/pizza-sprite.png"));
			sale = ImageIO.read(new File("Content/Images/sale.png"));
			spam = ImageIO.read(new File("Content/Images/spam-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Calculates the x position of the cell index
	 * @param x
	 * @return
	 */
	public int calculatePosX(double x){
		return (int)(x * CELLSIZEX);
	}
	
	/**
	 * Calculates the y position of the cell index
	 * @param y
	 * @return
	 */
	public int calculatePosY(double y){
		return (int)(y * CELLSIZEY);
	}
	
	public void mouseClicked(MouseEvent e) {
		//do something with the panel
	}
}
