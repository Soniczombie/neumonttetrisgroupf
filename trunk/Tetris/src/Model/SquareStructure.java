package Model;

import java.awt.*;

import Controllers.GameArea;

public class SquareStructure extends Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle topLeft, topRight, bottomLeft, bottomRight;

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
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container
						.findComponentAt(this.getX(), this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else {
			return false;
		}
	}

	public void reachedBottom() {
		super.reachedBottom();
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth() / 2,
				this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY(),
				this.getWidth() / 2, this.getHeight() / 2);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 2),
				this.getWidth() / 2, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2),
				this.getY() + (this.getHeight() / 2), this.getWidth() / 2,
				this.getHeight() / 2);
	}

	public boolean canMoveToLeft(Rectangle background) {
		if (container.findComponentAt(this.getX() - (this.getWidth() / 2), this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 2),
				this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY() + (this.getHeight() / 2)) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(), this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()
				+ (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(), this.getY()
						+ (this.getHeight() / 2)) != container) {
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
