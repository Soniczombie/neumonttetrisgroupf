package Model;

import java.awt.*;

import Controllers.GameArea;

public class SquareStructure extends Container implements Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle topLeft, topRight, bottomLeft, bottomRight;
	private GameArea container;

	public SquareStructure(int x, int y, Container c) {
		super();
		setBounds(x, y, c.getWidth() / 6, c.getWidth() / 6);
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		topLeft = new Rectangle(0, 0, getWidth() / 2, getHeight() / 2);
		topRight = new Rectangle(getWidth() / 2, 0, getWidth() / 2,
				getHeight() / 2);
		bottomLeft = new Rectangle(0, getHeight() / 2, getWidth() / 2,
				getHeight() / 2);
		bottomRight = new Rectangle(getWidth() / 2, getHeight() / 2,
				getWidth() / 2, getHeight() / 2);
		topLeft.setBackground(Color.gray);
		topRight.setBackground(Color.gray);
		bottomLeft.setBackground(Color.gray);
		bottomRight.setBackground(Color.gray);
		add(topLeft, 0);
		add(topRight, 0);
		add(bottomLeft, 0);
		add(bottomRight, 0);
	}

	public void moveDown() {
		if (container.collided(this)) {
			container.removeMe(this);
			container.reachedBottom(this);
		} else {
			setLocation(getX(), getY() + topLeft.getHeight());
		}
	}

	public void moveRight() {
		if (container.canMoveToTheRight(this)) {
			setLocation(getX() + topLeft.getWidth(), getY());
		}
	}

	public void moveLeft() {
		if (container.canMoveToTheLeft(this)) {
			setLocation(getX() - topLeft.getWidth(), getY());
		}
	}

	public void turn() {
	}

	public boolean checkCollision(Rectangle background) {
		SquareStructure s = this;
		if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
				&& container
						.findComponentAt(s.getX(), s.getY() + s.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
				s.getY() + s.getHeight()) != background
				&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
						s.getY() + s.getHeight()) != container) {
			return true;
		} else {
			return false;
		}
	}

	public void reachedBottom() {
		SquareStructure s = this;
		container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 2,
				s.getHeight() / 2);
		container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY(),
				s.getWidth() / 2, s.getHeight() / 2);
		container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 2),
				s.getWidth() / 2, s.getHeight() / 2);
		container.putRectangleAt(s.getX() + (s.getWidth() / 2),
				s.getY() + (s.getHeight() / 2), s.getWidth() / 2,
				s.getHeight() / 2);
	}

	public boolean canMoveToLeft(Rectangle background) {
		SquareStructure s = this;
		if (container.findComponentAt(s.getX() - (s.getWidth() / 2), s.getY()) != background
				&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
						s.getY()) != container) {
			return false;
		} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
				s.getY() + (s.getHeight() / 2)) != background
				&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
						s.getY() + (s.getHeight() / 2)) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		SquareStructure s = this;
		if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != background
				&& container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != container) {
			return false;
		} else if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()
				+ (s.getHeight() / 2)) != background
				&& container.findComponentAt(s.getX() + s.getWidth(), s.getY()
						+ (s.getHeight() / 2)) != container) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean canTurn(Rectangle background) {
		return true;
	}
}
