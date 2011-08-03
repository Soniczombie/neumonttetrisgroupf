package Model;

import java.awt.*;

import Controllers.GameArea;

public class LRightStructure extends PositionableStructure {
	private static final long serialVersionUID = 1L;
	private Rectangle top, right, middle, bottom;
	public final int STRAIGHT = 0, RIGHT = 1, UPSIDEDOWN = 2, LEFT = 3;

	public LRightStructure(int x, int y, Container c) {
		super();
		container = (GameArea) c;
		setBounds(x, y, c.getWidth() / 6, c.getWidth() / 4);
		createRectangles();
	}

	private void createRectangles() {
		top = new Rectangle(0, 0, getWidth() / 2, getHeight() / 3);
		right = new Rectangle(getWidth() / 2, 0, getWidth() / 2,
				getHeight() / 3);
		middle = new Rectangle(0, getHeight() / 3, getWidth() / 2,
				getHeight() / 3);
		bottom = new Rectangle(0, 2 * (getHeight() / 3), getWidth() / 2,
				getHeight() / 3);
		top.setBackground(Color.green);
		right.setBackground(Color.green);
		middle.setBackground(Color.green);
		bottom.setBackground(Color.green);
		add(top, 0);
		add(right, 0);
		add(middle, 0);
		add(bottom, 0);
	}

	public void turnRight() {
		setBounds(getX(), getY(), top.getWidth() * 3, top.getHeight() * 2);
		top.setLocation(2 * (getWidth() / 3), 0);
		right.setLocation(2 * (getWidth() / 3), getHeight() / 2);
		middle.setLocation(getWidth() / 3, 0);
		bottom.setLocation(0, 0);
		setPosition(RIGHT);
		repaint();
	}

	public void turnUpsideDown() {
		setBounds(getX(), getY(), top.getWidth() * 2, top.getHeight() * 3);
		top.setLocation(getWidth() / 2, 2 * (getHeight() / 3));
		right.setLocation(0, 2 * (getHeight() / 3));
		middle.setLocation(getWidth() / 2, getHeight() / 3);
		bottom.setLocation(getWidth() / 2, 0);
		setPosition(UPSIDEDOWN);
		repaint();
	}

	public void turnLeft() {
		setBounds(getX(), getY(), top.getWidth() * 3, top.getHeight() * 2);
		top.setLocation(0, getHeight() / 2);
		right.setLocation(0, 0);
		middle.setLocation(getWidth() / 3, getHeight() / 2);
		bottom.setLocation(2 * (getWidth() / 3), getHeight() / 2);
		setPosition(LEFT);
		repaint();
	}

	public void turnStraight() {
		setBounds(getX(), getY(), top.getWidth() * 2, top.getHeight() * 3);
		top.setLocation(0, 0);
		right.setLocation(getWidth() / 2, 0);
		middle.setLocation(0, getHeight() / 3);
		bottom.setLocation(0, 2 * (getHeight() / 3));
		setPosition(STRAIGHT);
		repaint();
	}
	
	public void moveDown() {
		if (container.collided(this)) {
			container.removeMe(this);
			container.reachedBottom(this);
		} else {
			setLocation(getX(), getY() + top.getHeight());
		}
	}

	public void moveRight() {
		if (container.canMoveToTheRight(this)) {
			setLocation(getX() + top.getWidth(), getY());
		}
	}

	public void moveLeft() {
		if (container.canMoveToTheLeft(this)) {
			setLocation(getX() - top.getWidth(), getY());
		}
	}

	public void turn() {
		if (container.canTurn(this)) {
			switch (getPosition()) {
			case STRAIGHT:
				turnRight();
				break;
			case RIGHT:
				turnUpsideDown();
				break;
			case UPSIDEDOWN:
				turnLeft();
				break;
			default:
				turnStraight();
			}
		}
	}

	public boolean checkCollision(Rectangle background) {
		LRightStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (s.getHeight() / 3)) != s) {
				return true;
			} else {
				return false;
			}
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX(), s.getY()
					+ (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 2)) != s) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + (s.getHeight() / 2)) != s) {
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
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
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
		}
	}

	public void reachedBottom() {
		LRightStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 2,
					s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY(),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 3),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
		} else if (s.getPosition() == s.RIGHT) {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 3,
					s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY(),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY(), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY() + (s.getHeight() / 2), s.getWidth() / 3,
					s.getHeight() / 2);
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY(),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (s.getHeight() / 3), s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
		} else {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 3,
					s.getHeight() / 2);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 2),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY()
					+ (s.getHeight() / 2), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY() + (s.getHeight() / 2), s.getWidth() / 3,
					s.getHeight() / 2);
		}
	}

	public boolean canTurn(Rectangle background) {
		LRightStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			if (container.getComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.getComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.getComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.getComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + (s.getHeight() / 2)) != s) {
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
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 3)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (s.checkCollision(background)) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY()) != s) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		LRightStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
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
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != container) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 3)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY() + (s.getHeight() / 2)) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		LRightStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			if (container.getComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.getComponentAt(s.getX() + s.getWidth(), s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (s.getHeight() / 3)) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 2)) != container) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
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
		} else {
			if (container.findComponentAt(s.getX() + (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 3),
							s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 2)) != container) {
				return false;
			} else {
				return true;
			}
		}
	}
}
