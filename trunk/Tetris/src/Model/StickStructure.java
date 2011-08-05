package Model;

import java.awt.Container;

import Controllers.GameArea;

public class StickStructure extends Structure {
	private static final long serialVersionUID = 1L;
	private Rectangle first, second, third, fourth;
	public final int VERTICAL = 0, HORIZONTAL = 1;

	public StickStructure(int x, int y, Container c) {
		super(Squares.BLUE.image);
		setBounds(x, y, c.getWidth() / 12, 4 * (c.getWidth() / 12));
		container = (GameArea) c;
		createRectangles();
	}

	private void createRectangles() {
		first = new Rectangle(0, 0, getHeight() / 4, getHeight() / 4, image);
		second = new Rectangle(0, getHeight() / 4, getHeight() / 4,
				getHeight() / 4, image);
		third = new Rectangle(0, 2 * (getHeight() / 4), getHeight() / 4,
				getHeight() / 4, image);
		fourth = new Rectangle(0, 3 * (getHeight() / 4), getHeight() / 4,
				getHeight() / 4, image);
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
		setPosition(HORIZONTAL);
		repaint();
	}

	public void turnVertical() {
		setBounds(getX(), getY(), first.getWidth(), 4 * first.getHeight());
		first.setLocation(0, 0);
		second.setLocation(0, getHeight() / 4);
		third.setLocation(0, 2 * (getHeight() / 4));
		fourth.setLocation(0, 3 * (getHeight() / 4));
		setPosition(VERTICAL);
		repaint();
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
		if (this.getPosition() == this.VERTICAL) {
			return checkCollisionVertical(background);
		} else {
			return checkCollisionHorizontal(background);
		}
	}

	private boolean checkCollisionHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 4),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 4),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 4)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 4)),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else if (container.findComponentAt(this.getX()
				+ (3 * (this.getWidth() / 4)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (3 * (this.getWidth() / 4)),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkCollisionVertical(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else {
			return false;
		}
	}

	public void reachedBottom() {
		super.reachedBottom();
		if (this.getPosition() == this.VERTICAL) {
			reachedBottomVertical();
		} else {
			reachedBottomHorizontal();
		}
	}

	private void reachedBottomHorizontal() {
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth() / 4,
				this.getHeight());
		container.putRectangleAt(this.getX() + (this.getWidth() / 4), this.getY(),
				this.getWidth() / 4, this.getHeight());
		container.putRectangleAt(this.getX() + (2 * (this.getWidth() / 4)),
				this.getY(), this.getWidth() / 4, this.getHeight());
		container.putRectangleAt(this.getX() + (3 * (this.getWidth() / 4)),
				this.getY(), this.getWidth() / 4, this.getHeight());
	}

	private void reachedBottomVertical() {
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth(),
				this.getHeight() / 4);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 4),
				this.getWidth(), this.getHeight() / 4);
		container.putRectangleAt(this.getX(), this.getY()
				+ (2 * (this.getHeight() / 4)), this.getWidth(),
				this.getHeight() / 4);
		container.putRectangleAt(this.getX(), this.getY()
				+ (3 * (this.getHeight() / 4)), this.getWidth(),
				this.getHeight() / 4);
	}

	public boolean canMoveToLeft(Rectangle background) {
		
		if (this.getPosition() == this.VERTICAL) {
			return canMoveLeftVertical(background);
		} else {
			return canMoveLeftHorizontal(background);
		}
	}

	private boolean canMoveLeftHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX() - (this.getWidth() / 4),
				this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 4),
						this.getY()) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveLeftVertical(Rectangle background) {
		if (container.findComponentAt(this.getX() - (this.getWidth()), this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth()),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth()),
				this.getY() + (this.getHeight() / 4)) != background
				&& container.findComponentAt(this.getX() - (this.getWidth()),
						this.getY() + (this.getHeight() / 4)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth()),
				this.getY() + (2 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() - (this.getWidth()),
						this.getY() + (2 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth()),
				this.getY() + (3 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() - (this.getWidth()),
						this.getY() + (3 * (this.getHeight() / 4))) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToRight(Rectangle background) {
		
		if (this.getPosition() == this.VERTICAL) {
			return canMoveRightVertical(background);
		} else {
			return canMoveRightHorizontal(background);
		}
	}

	private boolean canMoveRightHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX() + (this.getWidth()), this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveRightVertical(Rectangle background) {
		if (container.findComponentAt(this.getX() + (this.getWidth()), this.getY()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth()),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth()),
				this.getY() + (this.getHeight() / 4)) != background
				&& container.findComponentAt(this.getX() + (this.getWidth()),
						this.getY() + (this.getHeight() / 4)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth()),
				this.getY() + (2 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth()),
						this.getY() + (2 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth()),
				this.getY() + (3 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth()),
						this.getY() + (3 * (this.getHeight() / 4))) != container) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canTurn(Rectangle background) {
		
		if (this.getPosition() == this.VERTICAL) {
			return canTurnRightVertical(background);
		} else {
			return canTurnRightHorizontal(background);
		}

	}

	private boolean canTurnRightHorizontal(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + (2 * this.getHeight())) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (2 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + (3 * this.getHeight())) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (3 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 4),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 4),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 4),
				this.getY() + (2 * this.getHeight())) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 4),
						this.getY() + (2 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 4),
				this.getY() + (3 * this.getHeight())) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 4),
						this.getY() + (3 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 4)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 4)),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 4)), this.getY() + (2 * this.getHeight())) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 4)),
						this.getY() + (2 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 4)), this.getY() + (3 * this.getHeight())) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 4)),
						this.getY() + (3 * this.getHeight())) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (3 * (this.getWidth() / 4)), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX()
						+ (3 * (this.getWidth() / 4)),
						this.getY() + this.getHeight()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (3 * (this.getWidth() / 4)), this.getY() + (2 * this.getHeight())) != background
				&& container.findComponentAt(this.getX()
						+ (3 * (this.getWidth() / 4)),
						this.getY() + (2 * this.getHeight())) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canTurnRightVertical(Rectangle background) {
		if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (2 * this.getWidth()),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + (2 * this.getWidth()),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (3 * this.getWidth()),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + (3 * this.getWidth()),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 4)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 4)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (2 * this.getWidth()),
				this.getY() + (this.getHeight() / 4)) != background
				&& container.findComponentAt(this.getX() + (2 * this.getWidth()),
						this.getY() + (this.getHeight() / 4)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (3 * this.getWidth()),
				this.getY() + (this.getHeight() / 4)) != background
				&& container.findComponentAt(this.getX() + (3 * this.getWidth()),
						this.getY() + (this.getHeight() / 4)) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (2 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (2 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (2 * this.getWidth()),
				this.getY() + (2 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + (2 * this.getWidth()),
						this.getY() + (2 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (3 * this.getWidth()),
				this.getY() + (2 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + (3 * this.getWidth()),
						this.getY() + (2 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (3 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (3 * (this.getHeight() / 4))) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + (2 * this.getWidth()),
				this.getY() + (3 * (this.getHeight() / 4))) != background
				&& container.findComponentAt(this.getX() + (2 * this.getWidth()),
						this.getY() + (3 * (this.getHeight() / 4))) != container) {
			return false;
		} else {
			return true;
		}
	}

}
