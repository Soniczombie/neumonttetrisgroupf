package Model;

import java.awt.*;

import Controllers.GameArea;

public class SStructure extends Container implements Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle topRight, topMiddle, bottomMiddle, bottomLeft;
	public final int HORIZONTAL = 0, VERTICAL = 1;
	private int position;
	private GameArea container;

	public SStructure(int x, int y, Container c) {
		super();
		setBounds(x, y, c.getWidth() / 4, c.getWidth() / 6);
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		topRight = new Rectangle(2 * (getWidth() / 3), 0, getWidth() / 3,
				getHeight() / 2);
		topMiddle = new Rectangle(getWidth() / 3, 0, getWidth() / 3,
				getHeight() / 2);
		bottomMiddle = new Rectangle(getWidth() / 3, getHeight() / 2,
				getWidth() / 3, getHeight() / 2);
		bottomLeft = new Rectangle(0, getHeight() / 2, getWidth() / 3,
				getHeight() / 2);
		topRight.setBackground(Color.pink);
		topMiddle.setBackground(Color.pink);
		bottomMiddle.setBackground(Color.pink);
		bottomLeft.setBackground(Color.pink);
		add(topRight, 0);
		add(topMiddle, 0);
		add(bottomMiddle, 0);
		add(bottomLeft, 0);
	}

	public void turnVertical() {
		setBounds(getX(), getY(), topRight.getWidth() * 2,
				topRight.getHeight() * 3);
		topRight.setLocation(getWidth() / 2, 2 * (getHeight() / 3));
		topMiddle.setLocation(getWidth() / 2, getHeight() / 3);
		bottomMiddle.setLocation(0, getHeight() / 3);
		bottomLeft.setLocation(0, 0);
		updatePosition(VERTICAL);
		repaint();
	}

	public void turnHorizontal() {
		setBounds(getX(), getY(), topRight.getWidth() * 3,
				topRight.getHeight() * 2);
		topRight.setLocation(2 * (getWidth() / 3), 0);
		topMiddle.setLocation(getWidth() / 3, 0);
		bottomMiddle.setLocation(getWidth() / 3, getHeight() / 2);
		bottomLeft.setLocation(0, getHeight() / 2);
		updatePosition(HORIZONTAL);
		repaint();
	}

	private void updatePosition(int x) {
		position = x;
	}

	public int getPosition() {
		return position;
	}

	public void moveDown() {
		if (container.collided(this)) {
			container.removeMe(this);
			container.reachedBottom(this);
		} else {
			setLocation(getX(), getY() + topRight.getHeight());
		}
	}

	public void moveRight() {
		if (container.canMoveToTheRight(this)) {
			setLocation(getX() + topRight.getWidth(), getY());
		}
	}

	public void moveLeft() {
		if (container.canMoveToTheLeft(this)) {
			setLocation(getX() - topRight.getWidth(), getY());
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
		SStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + (s.getHeight() / 2)) != s) {
				return true;
			} else {
				return false;
			}
		} else {
			if (container.findComponentAt(s.getX(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
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
	}

	public void reachedBottom() {
		SStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY(), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY(),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY()
					+ (s.getHeight() / 2), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 2),
					s.getWidth() / 3, s.getHeight() / 2);
		} else {
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (s.getHeight() / 3), s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 3),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 2,
					s.getHeight() / 3);
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		SStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY() + (s.getHeight() / 2)) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		SStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
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

	public boolean canTurn(Rectangle background) {
		SStructure s = this;
		if (s.getPosition() == s.HORIZONTAL) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
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
			if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (2 * (s.getHeight() / 3))) != container) {
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
