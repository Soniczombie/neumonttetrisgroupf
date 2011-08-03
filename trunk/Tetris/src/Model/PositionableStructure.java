package Model;

import java.awt.Container;

import Controllers.GameArea;

public abstract class PositionableStructure extends Container implements Structure{

	private static final long serialVersionUID = 1L;
	private int position;
	protected GameArea container;

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
}
