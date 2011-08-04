package Model;

import java.awt.Container;

import Controllers.GameArea;

public abstract class Structure extends Container{

	private static final long serialVersionUID = 1L;
	private int position;
	protected GameArea container;

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
		//TODO: put reached bottom sound here
	}

	public abstract boolean checkCollision(Rectangle background);

	public abstract boolean canMoveToLeft(Rectangle background);

	public abstract boolean canMoveToRight(Rectangle backgroun);

	public abstract boolean canTurn(Rectangle background);

	
}
