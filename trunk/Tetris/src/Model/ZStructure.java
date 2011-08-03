package Model;

import java.awt.*;

import Controllers.GameArea;

public class ZStructure extends PositionableStructure {
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
		ZStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY()
					+ (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 2)) != s) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else {
				return false;
			}
		} else {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void reachedBottom() {
		ZStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 3,
					s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY(),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY()
					+ (s.getHeight() / 2), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY() + (s.getHeight() / 2), s.getWidth() / 3,
					s.getHeight() / 2);
		} else {
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY(),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (s.getHeight() / 3), s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 3),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
		}
	}

	public boolean canTurn(Rectangle background) {
		ZStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY()
					+ (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (2 * (s.getHeight() / 3))) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		ZStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY()
					+ (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY()) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		ZStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 2)) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()
					+ (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

}
