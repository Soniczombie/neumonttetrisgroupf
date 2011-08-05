package Model;

import javax.swing.ImageIcon;

public enum Squares {
	BLUE(new ImageIcon("images/blueblock.jpg")),
	CYAN(new ImageIcon("images/cyanblock.jpg")),
	GREEN(new ImageIcon("images/greenblock.jpg")),
	ORANGE(new ImageIcon("images/orangeblock.jpg")),
	PURPLE(new ImageIcon("images/purpleblock.jpg")),
	RED(new ImageIcon("images/redblock.jpg")),
	YELLOW(new ImageIcon("images/yellowblock.jpg"));
	
	public ImageIcon image;
	
	private Squares(ImageIcon image){
		this.image = image;
	}
}
