package Model;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Rectangle Supplier Class Author: David D. Riley Date: April, 2004
 */
@SuppressWarnings("serial")
public class Rectangle extends JPanel {
	ImageIcon backgroundImage = null;

	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}


	public void setBackgroundImage(ImageIcon backgroundImage) {
		this.backgroundImage = backgroundImage;
	}


	/**
	 * post: getX() == x and getY() == y and getWidth() == w and getHeight() ==
	 * h and getBackground() == Color.black
	 */
	public Rectangle(int x, int y, int w, int h, ImageIcon backgroundImage) {
		super();
		if(backgroundImage != null){
			this.backgroundImage = backgroundImage;
		}else{
			setBackground(null);
		}
		setBounds(x, y, w, h);
	}
	
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(backgroundImage != null){
			g2d.drawImage(backgroundImage.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		}
		else{
			super.paintComponent(g);
//			g.setColor(getBackground());
//			g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
//			paintChildren(g);
		}
	}

//	/**
//	 * post: this method draws a filled Rectangle and the upper left corner is
//	 * (getX(), getY()) and the rectangle's dimensions are getWidth() and
//	 * getHeight() and the rectangle's color is getBackground()
//	 */
//	public void paint(Graphics g) {
//			g.setColor(getBackground());
//			g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
//			paintChildren(g);
//	}

}