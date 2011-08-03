package Model;

import java.awt.*;

import Controllers.GameArea;

public class TStructure extends PositionableStructure {
	private static final long serialVersionUID = 1L;
	private Rectangle left, middle, right, bottom;
	public final int STRAIGHT = 0, RIGHT = 1, UPSIDEDOWN = 2, LEFT = 3;

	public TStructure(int x, int y, Container c) {
		super();
		setBounds(x, y, c.getWidth() / 4, c.getWidth() / 6);
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		left = new Rectangle(0, 0, getWidth() / 3, getHeight() / 2);
		middle = new Rectangle(getWidth() / 3, 0, getWidth() / 3,
				getHeight() / 2);
		right = new Rectangle(2 * (getWidth() / 3), 0, getWidth() / 3,
				getHeight() / 2);
		bottom = new Rectangle(getWidth() / 3, getHeight() / 2, getWidth() / 3,
				getHeight() / 2);
		left.setBackground(Color.red);
		right.setBackground(Color.red);
		middle.setBackground(Color.red);
		bottom.setBackground(Color.red);
		add(left, 0);
		add(right, 0);
		add(middle, 0);
		add(bottom, 0);
	}

	public void turnRight() {
		setBounds(getX(), getY(), 2 * (left.getHeight()), 3 * (left.getWidth()));
		left.setLocation(getWidth() / 2, 0);
		middle.setLocation(getWidth() / 2, getHeight() / 3);
		right.setLocation(getWidth() / 2, 2 * (getHeight() / 3));
		bottom.setLocation(0, getHeight() / 3);
		setPosition(RIGHT);
		repaint();
	}

	public void turnUpsideDown() {
		setBounds(getX(), getY(), left.getWidth() * 3, left.getHeight() * 2);
		bottom.setLocation(getWidth() / 3, 0);
		left.setLocation(2 * (getWidth() / 3), getHeight() / 2);
		middle.setLocation(getWidth() / 3, getHeight() / 2);
		right.setLocation(0, getHeight() / 2);
		setPosition(UPSIDEDOWN);
		repaint();
	}

	public void turnLeft() {
		setBounds(getX(), getY(), 2 * (left.getHeight()), 3 * (left.getWidth()));
		right.setLocation(0, 0);
		middle.setLocation(0, getHeight() / 3);
		left.setLocation(0, 2 * (getHeight() / 3));
		bottom.setLocation(getWidth() / 2, getHeight() / 3);
		setPosition(LEFT);
		repaint();
	}

	public void turnStraight() {
		setBounds(getX(), getY(), left.getWidth() * 3, left.getHeight() * 2);
		left.setLocation(0, 0);
		middle.setLocation(getWidth() / 3, 0);
		right.setLocation(2 * (getWidth() / 3), 0);
		bottom.setLocation(getWidth() / 3, getHeight() / 2);
		setPosition(STRAIGHT);
		repaint();
	}

	public void moveDown() {
		if (container.collided(this)) {
			container.removeMe(this);
			container.reachedBottom(this);
		} else {
			setLocation(getX(), getY() + left.getHeight());
		}
	}

	public void moveRight() {
		if (container.canMoveToTheRight(this)) {
			setLocation(getX() + left.getWidth(), getY());
		}
	}

	public void moveLeft() {
		if (container.canMoveToTheLeft(this)) {
			setLocation(getX() - left.getWidth(), getY());
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
		TStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
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
					+ (2 * (s.getWidth() / 3)), s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + (s.getHeight() / 2)) != s) {
				return true;
			} else
				return false;
		} else if (s.getPosition() == s.RIGHT) {
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
			} else
				return false;
		} else if (s.getPosition() == s.UPSIDEDOWN) {
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
		TStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 3,
					s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY(),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY(), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY()
					+ (s.getHeight() / 2), s.getWidth() / 3, s.getHeight() / 2);
		} else if (s.getPosition() == s.RIGHT) {
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY(),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (s.getHeight() / 3), s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 3),
					s.getWidth() / 2, s.getHeight() / 3);
			container.putRectangleAt(s.getX() + (s.getWidth() / 2), s.getY()
					+ (2 * (s.getHeight() / 3)), s.getWidth() / 2,
					s.getHeight() / 3);
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY(),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 2),
					s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (s.getWidth() / 3), s.getY()
					+ (s.getHeight() / 2), s.getWidth() / 3, s.getHeight() / 2);
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 3)),
					s.getY() + (s.getHeight() / 2), s.getWidth() / 3,
					s.getHeight() / 2);
		} else {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 2,
					s.getHeight() / 3);
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
		TStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			return !s.checkCollision(background);
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
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
		} else if (s.getPosition() == s.UPSIDEDOWN) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)), s.getY()) != s) {
				return false;
			} else if (s.checkCollision(background)) {
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

	public boolean canMoveToRight(Rectangle background) {
		TStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 3)), s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 3)),
							s.getY() + (s.getHeight() / 2)) != s) {
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
		} else if (s.getPosition() == s.UPSIDEDOWN) {
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
			if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 2),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 2),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		TStructure s = this;
		if (s.getPosition() == s.STRAIGHT) {
			if (container.findComponentAt(s.getX() - (s.getWidth() / 3),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 3),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (s.getHeight() / 2)) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (s.getHeight() / 2)) != s) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.RIGHT) {
			if (container.findComponentAt(s.getX(), s.getY()) != background
					&& container.findComponentAt(s.getX(), s.getY()) != s) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (2 * (s.getHeight() / 3))) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (2 * (s.getHeight() / 3))) != s) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth() / 2),
					s.getY() + (s.getHeight() / 3)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 2),
							s.getY() + (s.getHeight() / 3)) != container) {
				return false;
			} else {
				return true;
			}
		} else if (s.getPosition() == s.UPSIDEDOWN) {
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
}
