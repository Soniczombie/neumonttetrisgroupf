package Model;

import java.awt.*;

import Controllers.GameArea;

public class StickStructure extends Container implements Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle first, second, third, fourth;
	public final int VERTICAL = 0, HORIZONTAL = 1;
	private int position;
	private GameArea container;

	public StickStructure(int x, int y, Container c) {
		super();
		setBounds(x, y, c.getWidth() / 12, 4 * (c.getWidth() / 12));
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		first = new Rectangle(0, 0, getHeight() / 4, getHeight() / 4);
		second = new Rectangle(0, getHeight() / 4, getHeight() / 4,
				getHeight() / 4);
		third = new Rectangle(0, 2 * (getHeight() / 4), getHeight() / 4,
				getHeight() / 4);
		fourth = new Rectangle(0, 3 * (getHeight() / 4), getHeight() / 4,
				getHeight() / 4);
		first.setBackground(Color.blue);
		second.setBackground(Color.blue);
		third.setBackground(Color.blue);
		fourth.setBackground(Color.blue);
		add(first, 0);
		add(second, 0);
		add(third, 0);
		add(fourth, 0);
	}

	public void turnHorizontal() {
		setBounds(getX(), getY(), first.getWidth() * 4, first.getHeight());
		first.setLocation(0, 0);
		second.setLocation(getWidth() / 4, 0);
		third.setLocation(2 * (getWidth() / 4), 0);
		fourth.setLocation(3 * (getWidth() / 4), 0);
		updatePosition(HORIZONTAL);
		repaint();
	}

	public void turnVertical() {
		setBounds(getX(), getY(), first.getWidth(), 4 * first.getHeight());
		first.setLocation(0, 0);
		second.setLocation(0, getHeight() / 4);
		third.setLocation(0, 2 * (getHeight() / 4));
		fourth.setLocation(0, 3 * (getHeight() / 4));
		updatePosition(VERTICAL);
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
			setLocation(getX(), getY() + first.getHeight());
		}
	}

	public void moveRight() {
		if (container.canMoveToTheRight(this)) {
			setLocation(getX() + first.getWidth(), getY());
		}
	}

	public void moveLeft() {
		if (container.canMoveToTheLeft(this)) {
			setLocation(getX() - first.getWidth(), getY());
		}
	}

	public void turn() {
		if (container.canTurn(this)) {
			switch (getPosition()) {
			case VERTICAL:
				turnHorizontal();
				break;
			default:
				turnVertical();
			}
		}
	}

	public boolean checkCollision(Rectangle background) {
		StickStructure s = this;
		if (s.getPosition() == s.VERTICAL) {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
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
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 4),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 4),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 4)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 4)),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else if (container.findComponentAt(s.getX()
					+ (3 * (s.getWidth() / 4)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (3 * (s.getWidth() / 4)),
							s.getY() + s.getHeight()) != container) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void reachedBottom() {
		StickStructure s = this;
		if (s.getPosition() == s.VERTICAL) {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth(),
					s.getHeight() / 4);
			container.putRectangleAt(s.getX(), s.getY() + (s.getHeight() / 4),
					s.getWidth(), s.getHeight() / 4);
			container.putRectangleAt(s.getX(), s.getY()
					+ (2 * (s.getHeight() / 4)), s.getWidth(),
					s.getHeight() / 4);
			container.putRectangleAt(s.getX(), s.getY()
					+ (3 * (s.getHeight() / 4)), s.getWidth(),
					s.getHeight() / 4);
		} else {
			container.putRectangleAt(s.getX(), s.getY(), s.getWidth() / 4,
					s.getHeight());
			container.putRectangleAt(s.getX() + (s.getWidth() / 4), s.getY(),
					s.getWidth() / 4, s.getHeight());
			container.putRectangleAt(s.getX() + (2 * (s.getWidth() / 4)),
					s.getY(), s.getWidth() / 4, s.getHeight());
			container.putRectangleAt(s.getX() + (3 * (s.getWidth() / 4)),
					s.getY(), s.getWidth() / 4, s.getHeight());
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		StickStructure s = this;
		if (s.getPosition() == s.VERTICAL) {
			if (container.findComponentAt(s.getX() - (s.getWidth()), s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth()),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth()),
					s.getY() + (s.getHeight() / 4)) != background
					&& container.findComponentAt(s.getX() - (s.getWidth()),
							s.getY() + (s.getHeight() / 4)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth()),
					s.getY() + (2 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() - (s.getWidth()),
							s.getY() + (2 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() - (s.getWidth()),
					s.getY() + (3 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() - (s.getWidth()),
							s.getY() + (3 * (s.getHeight() / 4))) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() - (s.getWidth() / 4),
					s.getY()) != background
					&& container.findComponentAt(s.getX() - (s.getWidth() / 4),
							s.getY()) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		StickStructure s = this;
		if (s.getPosition() == s.VERTICAL) {
			if (container.findComponentAt(s.getX() + (s.getWidth()), s.getY()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth()),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth()),
					s.getY() + (s.getHeight() / 4)) != background
					&& container.findComponentAt(s.getX() + (s.getWidth()),
							s.getY() + (s.getHeight() / 4)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth()),
					s.getY() + (2 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth()),
							s.getY() + (2 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth()),
					s.getY() + (3 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + (s.getWidth()),
							s.getY() + (3 * (s.getHeight() / 4))) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX() + (s.getWidth()), s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean canTurn(Rectangle background) {
		StickStructure s = this;
		if (s.getPosition() == s.VERTICAL) {
			if (container.findComponentAt(s.getX() + s.getWidth(), s.getY()) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (2 * s.getWidth()),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (2 * s.getWidth()),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (3 * s.getWidth()),
					s.getY()) != background
					&& container.findComponentAt(s.getX() + (3 * s.getWidth()),
							s.getY()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (s.getHeight() / 4)) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (s.getHeight() / 4)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (2 * s.getWidth()),
					s.getY() + (s.getHeight() / 4)) != background
					&& container.findComponentAt(s.getX() + (2 * s.getWidth()),
							s.getY() + (s.getHeight() / 4)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (3 * s.getWidth()),
					s.getY() + (s.getHeight() / 4)) != background
					&& container.findComponentAt(s.getX() + (3 * s.getWidth()),
							s.getY() + (s.getHeight() / 4)) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (2 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (2 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (2 * s.getWidth()),
					s.getY() + (2 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + (2 * s.getWidth()),
							s.getY() + (2 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (3 * s.getWidth()),
					s.getY() + (2 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + (3 * s.getWidth()),
							s.getY() + (2 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + s.getWidth(),
					s.getY() + (3 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + s.getWidth(),
							s.getY() + (3 * (s.getHeight() / 4))) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (2 * s.getWidth()),
					s.getY() + (3 * (s.getHeight() / 4))) != background
					&& container.findComponentAt(s.getX() + (2 * s.getWidth()),
							s.getY() + (3 * (s.getHeight() / 4))) != container) {
				return false;
			} else {
				return true;
			}
		} else {
			if (container.findComponentAt(s.getX(), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (2 * s.getHeight())) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (2 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX(),
					s.getY() + (3 * s.getHeight())) != background
					&& container.findComponentAt(s.getX(),
							s.getY() + (3 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 4),
					s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 4),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 4),
					s.getY() + (2 * s.getHeight())) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 4),
							s.getY() + (2 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX() + (s.getWidth() / 4),
					s.getY() + (3 * s.getHeight())) != background
					&& container.findComponentAt(s.getX() + (s.getWidth() / 4),
							s.getY() + (3 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 4)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 4)),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 4)), s.getY() + (2 * s.getHeight())) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 4)),
							s.getY() + (2 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (2 * (s.getWidth() / 4)), s.getY() + (3 * s.getHeight())) != background
					&& container.findComponentAt(s.getX()
							+ (2 * (s.getWidth() / 4)),
							s.getY() + (3 * s.getHeight())) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (3 * (s.getWidth() / 4)), s.getY() + s.getHeight()) != background
					&& container.findComponentAt(s.getX()
							+ (3 * (s.getWidth() / 4)),
							s.getY() + s.getHeight()) != container) {
				return false;
			} else if (container.findComponentAt(s.getX()
					+ (3 * (s.getWidth() / 4)), s.getY() + (2 * s.getHeight())) != background
					&& container.findComponentAt(s.getX()
							+ (3 * (s.getWidth() / 4)),
							s.getY() + (2 * s.getHeight())) != container) {
				return false;
			} else {
				return true;
			}
		}

	}

}
