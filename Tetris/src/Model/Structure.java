package Model;

import java.awt.Container;

import javax.swing.ImageIcon;

import Controllers.GameArea;

public abstract class Structure extends Container{

	private static final long serialVersionUID = 1L;
	public int id;
	private int position;
	protected GameArea container;
	protected ImageIcon image;
	
	protected Structure(ImageIcon image) {
		this.image = image;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
	
	public abstract void turn();

	public abstract void moveDown();

	public abstract void moveRight();

	public abstract void moveLeft();

	public void reachedBottom(){
		
	}

	public abstract boolean checkCollision(Rectangle background);

	public abstract boolean canMoveToLeft(Rectangle background);

	public abstract boolean canMoveToRight(Rectangle backgroun);

	public abstract boolean canTurn(Rectangle background);

	
}
