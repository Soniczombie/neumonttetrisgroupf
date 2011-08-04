package Model;

import java.awt.*;

import Controllers.GameArea;

public class TStructure extends Structure {
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
		if (this.getPosition() == this.STRAIGHT) {
			return checkCollisionStraight(background);
		} else if (this.getPosition() == this.RIGHT) {
			return checkCollisionRight(background);
		} else if (this.getPosition() == this.UPSIDEDOWN) {
			return checkCollisionUpsideDown(background);
		} else {
			return checkCollisionLeft(background);
		}
	}

	private boolean checkCollisionLeft(Rectangle background) {
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

	private boolean checkCollisionUpsideDown(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + this.getHeight()) != container) {
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

	private boolean checkCollisionRight(Rectangle background) {
		if (container.findComponentAt(this.getX(),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return true;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + this.getHeight()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + this.getHeight()) != container) {
			return true;
		} else
			return false;
	}

	private boolean checkCollisionStraight(Rectangle background) {
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
				+ (2 * (this.getWidth() / 3)), this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)),
						this.getY() + (this.getHeight() / 2)) != this) {
			return true;
		} else
			return false;
	}

	public void reachedBottom() {
		super.reachedBottom();
		if (this.getPosition() == this.STRAIGHT) {
			reachedBottomStraight();
		} else if (this.getPosition() == this.RIGHT) {
			reachedBottomRight();
		} else if (this.getPosition() == this.UPSIDEDOWN) {
			reachedBottomUpsideDown();
		} else {
			reachedBottomLeft();
		}
	}

	private void reachedBottomLeft() {
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth() / 2,
				this.getHeight() / 3);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY()
				+ (this.getHeight() / 3), this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 3),
				this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX(), this.getY()
				+ (2 * (this.getHeight() / 3)), this.getWidth() / 2,
				this.getHeight() / 3);
	}

	private void reachedBottomUpsideDown() {
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY(),
				this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 2),
				this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY()
				+ (this.getHeight() / 2), this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (2 * (this.getWidth() / 3)),
				this.getY() + (this.getHeight() / 2), this.getWidth() / 3,
				this.getHeight() / 2);
	}

	private void reachedBottomRight() {
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY(),
				this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY()
				+ (this.getHeight() / 3), this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX(), this.getY() + (this.getHeight() / 3),
				this.getWidth() / 2, this.getHeight() / 3);
		container.putRectangleAt(this.getX() + (this.getWidth() / 2), this.getY()
				+ (2 * (this.getHeight() / 3)), this.getWidth() / 2,
				this.getHeight() / 3);
	}

	private void reachedBottomStraight() {
		container.putRectangleAt(this.getX(), this.getY(), this.getWidth() / 3,
				this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY(),
				this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (2 * (this.getWidth() / 3)),
				this.getY(), this.getWidth() / 3, this.getHeight() / 2);
		container.putRectangleAt(this.getX() + (this.getWidth() / 3), this.getY()
				+ (this.getHeight() / 2), this.getWidth() / 3, this.getHeight() / 2);
	}

	public boolean canTurn(Rectangle background) {
		if (this.getPosition() == this.STRAIGHT) {
			return canTurnStraight(background);
		} else if (this.getPosition() == this.RIGHT) {
			return canTurnFromRight(background);
		} else if (this.getPosition() == this.UPSIDEDOWN) {
			return canTurnFromUpsideDown(background);
		} else {
			return canTurnFromLeft(background);
		}
	}

	private boolean canTurnFromLeft(Rectangle background) {
		if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY()) != this) {
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

	private boolean canTurnFromUpsideDown(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 3)), this.getY()) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)), this.getY()) != this) {
			return false;
		} else if (this.checkCollision(background)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canTurnFromRight(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canTurnStraight(Rectangle background) {
		return !this.checkCollision(background);
	}

	public boolean canMoveToRight(Rectangle background) {
		if (this.getPosition() == this.STRAIGHT) {
			return canMoveRightStraight(background);
		} else if (this.getPosition() == this.RIGHT) {
			return canMoveRightRight(background);
		} else if (this.getPosition() == this.UPSIDEDOWN) {
			return canMoveRightUpsideDown(background);
		} else {
			return canMoveRightLeft(background);
		}
	}

	private boolean canMoveRightLeft(Rectangle background) {
		if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY()) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + (this.getWidth() / 2),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX() + (this.getWidth() / 2),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveRightUpsideDown(Rectangle background) {
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

	private boolean canMoveRightRight(Rectangle background) {
		if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX() + this.getWidth(),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY() + (this.getHeight() / 3)) != container) {
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

	private boolean canMoveRightStraight(Rectangle background) {
		if (container.findComponentAt(this.getX() + this.getWidth(), this.getY()) != background
				&& container.findComponentAt(this.getX() + this.getWidth(),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX()
				+ (2 * (this.getWidth() / 3)), this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX()
						+ (2 * (this.getWidth() / 3)),
						this.getY() + (this.getHeight() / 2)) != this) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canMoveToLeft(Rectangle background) {
		
		if (this.getPosition() == this.STRAIGHT) {
			return canMoveLeftStraight(background);
		} else if (this.getPosition() == this.RIGHT) {
			return canMoveLeftRight(background);
		} else if (this.getPosition() == this.UPSIDEDOWN) {
			return canMoveLeftUpsideDown(background);
		} else {
			return canMoveLeftLeft(background);
		}
	}

	private boolean canMoveLeftLeft(Rectangle background) {
		if (container.findComponentAt(this.getX() - (this.getWidth() / 2),
				this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY()) != container) {
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

	private boolean canMoveLeftUpsideDown(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 3),
				this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 3),
						this.getY() + (this.getHeight() / 2)) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveLeftRight(Rectangle background) {
		if (container.findComponentAt(this.getX(), this.getY()) != background
				&& container.findComponentAt(this.getX(), this.getY()) != this) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + (2 * (this.getHeight() / 3))) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (2 * (this.getHeight() / 3))) != this) {
			return false;
		} else if (container.findComponentAt(this.getX() - (this.getWidth() / 2),
				this.getY() + (this.getHeight() / 3)) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 2),
						this.getY() + (this.getHeight() / 3)) != container) {
			return false;
		} else {
			return true;
		}
	}

	private boolean canMoveLeftStraight(Rectangle background) {
		if (container.findComponentAt(this.getX() - (this.getWidth() / 3),
				this.getY()) != background
				&& container.findComponentAt(this.getX() - (this.getWidth() / 3),
						this.getY()) != container) {
			return false;
		} else if (container.findComponentAt(this.getX(),
				this.getY() + (this.getHeight() / 2)) != background
				&& container.findComponentAt(this.getX(),
						this.getY() + (this.getHeight() / 2)) != this) {
			return false;
		} else {
			return true;
		}
	}
}
