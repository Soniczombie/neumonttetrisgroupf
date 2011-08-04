package Model;

import java.awt.*;

import Controllers.GameArea;

public class ZStructure extends Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle topLeft, topMiddle, bottomMiddle, bottomRight;
	public final int HORIZONTAL = 0, VERTICAL = 1;

	public ZStructure(int x, int y, Container c) {
		super();
		setBounds(x, y, c.getWidth() / 4, c.getWidth() / 6);
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		topLeft = new Rectangle(0, 0, getWidth() / 3, getHeight() / 2);
		topMiddle = new Rectangle(getWidth() / 3, 0, getWidth() / 3,
				getHeight() / 2);
		bottomMiddle = new Rectangle(getWidth() / 3, getHeight() / 2,
				getWidth() / 3, getHeight() / 2);
		bottomRight = new Rectangle(2 * (getWidth() / 3), getHeight() / 2,
				getWidth() / 3, getHeight() / 2);
		topLeft.setBackground(Color.yellow);
		topMiddle.setBackground(Color.yellow);
		bottomMiddle.setBackground(Color.yellow);
		bottomRight.setBackground(Color.yellow);
		add(topLeft, 0);
		add(topMiddle, 0);
		add(bottomMiddle, 0);
		add(bottomRight, 0);
	}

	public void turnVertical() {
		setBounds(getX(), getY(), topLeft.getWidth() * 2,
				topLeft.getHeight() * 3);
		topLeft.setLocation(getWidth() / 2, 0);
		topMiddle.setLocation(getWidth() / 2, getHeight() / 3);
		bottomMiddle.setLocation(0, getHeight() / 3);
		bottomRight.setLocation(0, 2 * (getHeight() / 3));
		setPosition(VERTICAL);
		repaint();
	}

	public void turnHorizontal() {
		setBounds(getX(), getY(), topLeft.getWidth() * 3,
				topLeft.getHeight() * 2);
		topLeft.setLocation(0, 0);
		topMiddle.setLocation(getWidth() / 3, 0);
		bottomMiddle.setLocation(getWidth() / 3, getHeight() / 2);
		bottomRight.setLocation(2 * (getWidth() / 3), getHeight() / 2);
		setPosition(HORIZONTAL);
		repaint();
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
		if (container.canTurn(this)) {
			switch (getPosition()) {
			case HORIZONTAL:
				turnVertical();
				break;
			default:
				turnHorizontal();
			}
		}
	}

	public boolean checkCollision(Rectangle background) {
		if (this.getPosition() == this.HORIZONTAL) {
			return checkCollisionHorizontal(background);
		} else {
			return checkCollisionVertical(background);
		}
	}

	private boolean checkCollisionVertical(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkCollisionHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()
				+ (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (this.getHeight() / 2)) != this) {
			return true;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 3),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 3),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 3)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else {
			return false;
		}
	}

	public void reachedBottom() {
		super.reachedBottom();
		if (this.getPosition() == this.HORIZONTAL) {
			reachedBottomHorizontal();
		} else {
			reachedBottomVertical();
		}
	}

	private void reachedBottomVertical() {
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY(),
				this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY()
				+ (this.getHeight() / 3), this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 3),
				this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX(), this.getY()
				+ (2 * (this.getHeight() / 3)), this.getWidth() / 2,
				this.getHeight() / 3);
	}

	private void reachedBottomHorizontal() {
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth() / 3,
				this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY(),
				this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY()
				+ (this.getHeight() / 2), this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (2 * (this.getWidth() / 3)),
				this.getY() + (this.getHeight() / 2), this.getWidth() / 3,
				this.getHeight() / 2);
	}

	public boolean canTurn(Rectangle background) {
		
		if (this.getPosition() == this.HORIZONTAL) {
			return canTurnFromHorizontal(background);
		} else {
			return canTurnFromVertical(background);
		}
	}

	private boolean canTurnFromVertical(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (2 * (this.getHeight() / 3))) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canTurnFromHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()
				+ (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (this.getHeight() / 2)) != this) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 3)), this.getY()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 3)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		
		if (this.getPosition() == this.HORIZONTAL) {
			return canMoveLeftHorizontal(background);
		} else {
			return canMoveLeftVertical(background);
		}
	}

	private boolean canMoveLeftVertical(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 2),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 2),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY() + (2 * (this.getHeight() / 3))) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveLeftHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()
				+ (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (this.getHeight() / 2)) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 3),
				this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 3),
						this.getY()) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		
		if (this.getPosition() == this.HORIZONTAL) {
			return canMoveRightHorizontal(background);
		} else {
			return canMoveRightVertical(background);
		}
	}

	private boolean canMoveRightVertical(Rectangle background) {
		if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()
				+ (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveRightHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX() + (2 * (this.getWidth() / 3)),
				this.getY()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 2)) != container) {
			return false;
		} else {
			return true;
		}
	}

}
