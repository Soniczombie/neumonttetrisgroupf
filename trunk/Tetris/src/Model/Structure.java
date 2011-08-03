package Model;

public interface Structure {
	
	public void turn();

	public void moveDown();

	public void moveRight();

	public void moveLeft();

	public void reachedBottom();

	public boolean checkCollision(Rectangle background);

	public boolean canMoveToLeft(Rectangle background);

	public boolean canMoveToRight(Rectangle backgroun);

	public boolean canTurn(Rectangle background);

}
