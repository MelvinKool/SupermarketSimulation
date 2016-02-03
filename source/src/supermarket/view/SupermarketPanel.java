package supermarket.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public static BufferedImage cheese,flesh,beer,captainMorgan,chips,nonFood,corn,pizza,sale,spam;
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
	}
	
	/**
	 * Paints the supermarket
	 * @param g
	 */
	private void paintDefaultSupermarket(Graphics g){
		paintImageRepeatedInRegion(g, cheese, 0, 0, (int)(6 * CELLSIZEX), (int)(6 * CELLSIZEY));
		paintImageRepeatedInRegion(g, pizza, (int)(10 * CELLSIZEX), (int)(2 * CELLSIZEY), (int)(22 * CELLSIZEX), (int)(6 * CELLSIZEY));
		paintImageRepeatedInRegion(g, flesh, (int)(25 * CELLSIZEX), (int)(0 * CELLSIZEY), (int)(30 * CELLSIZEX), (int)(6 * CELLSIZEY));
		paintImageRepeatedInRegion(g, nonFood, (int)(0 * CELLSIZEX), (int)(10 * CELLSIZEY), (int)(6 * CELLSIZEX), (int)(20 * CELLSIZEY));
		paintImageRepeatedInRegion(g, corn, (int)(10 * CELLSIZEX), (int)(12 * CELLSIZEY), (int)(15* CELLSIZEX), (int)(20 * CELLSIZEY));
		paintImageRepeatedInRegion(g, sale, (int)(10 * CELLSIZEX), (int)(10 * CELLSIZEY), (int)(15* CELLSIZEX), (int)(12 * CELLSIZEY));
		paintImageRepeatedInRegion(g, chips, (int)(17 * CELLSIZEX), (int)(12 * CELLSIZEY), (int)(22 * CELLSIZEX), (int)(20 * CELLSIZEY));
		paintImageRepeatedInRegion(g, sale, (int)(17 * CELLSIZEX), (int)(10 * CELLSIZEY), (int)(22 * CELLSIZEX), (int)(12 * CELLSIZEY));
		paintImageRepeatedInRegion(g, beer, (int)(25 * CELLSIZEX), (int)(10 * CELLSIZEY), (int)(30 * CELLSIZEX), (int)(20 * CELLSIZEY));
		//paintImageRepeatedInRegion(g, pizza, (int)(10 * CELLSIZEX), (int)(2 * CELLSIZEY), (int)(22 * CELLSIZEX), (int)(6 * CELLSIZEY));
		//paintImageRepeatedInRegion(g, sale, (int)(10 * CELLSIZEX), y1, x2, y2);
//		paintImageRepeatedInRegion(g, flesh, 0, 0, (int)(6 * CELLSIZEX), (int)(6 * CELLSIZEY));
//		paintImageRepeatedInRegion(g, beer, 50, 50, 200, 500);
		//draw a line on the y axis
		int outerX, outerY;
		outerX = calculatePosX(simulator.NUMCELLSX);
		outerY = calculatePosY(simulator.NUMCELLSY);
		//vertical line
		g.drawLine(outerX,0, outerX,outerY);
		//horizontal line
		g.drawLine(0, outerY, outerX, outerY);
		for(int j = 0; j < simulator.NUMCELLSY; j++){
			for(int i = 0; i < simulator.NUMCELLSX; i++){
				if(simulator.occupiedCells[j][i]){
					g.fillRect(calculatePosX(i), calculatePosY(j), (int)CELLSIZEX, (int)CELLSIZEY);
				}
			}
		}
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
			cheese = toBufferedImage(ImageIO.read(new File("Content/Images/cheese-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			flesh = toBufferedImage(ImageIO.read(new File("Content/Images/Bacon-Sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			beer = toBufferedImage(ImageIO.read(new File("Content/Images/beer-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			captainMorgan = toBufferedImage(ImageIO.read(new File("Content/Images/captain-morgan.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			chips = toBufferedImage(ImageIO.read(new File("Content/Images/Chips - Lays-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			nonFood = toBufferedImage(ImageIO.read(new File("Content/Images/cleaning-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			corn = toBufferedImage(ImageIO.read(new File("Content/Images/crispy_mais-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			pizza = toBufferedImage(ImageIO.read(new File("Content/Images/pizza-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			//sale = toBufferedImage(ImageIO.read(new File("Content/Images/sale.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			spam = toBufferedImage(ImageIO.read(new File("Content/Images/spam-sprite.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
			sale = toBufferedImage(ImageIO.read(new File("Content/Images/money.png")).getScaledInstance((int)(CELLSIZEX * 2), (int)(CELLSIZEY * 2), Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(this), img.getHeight(this), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, this);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
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
