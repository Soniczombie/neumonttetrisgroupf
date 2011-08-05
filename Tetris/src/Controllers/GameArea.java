package Controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Model.LLeftStructure;
import Model.LRightStructure;
import Model.Rectangle;
import Model.SStructure;
import Model.SquareStructure;
import Model.StickStructure;
import Model.Structure;
import Model.TStructure;
import Model.ZStructure;

public class GameArea extends Rectangle implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Rectangle background;
	private Timer timer;
	private Timer effectTimer;
	private Structure structure;
	private int rowRemoverYPosition;
	private int rowsRemoved;
	private Rectangle effect;
	private Color levelColor;
	public Sound s;
	private boolean pieceDropped = false;

	public GameArea(int x, int y, int w, int h) {
		super(x, y, w, h);
		background = new Rectangle(0, 0, w, h);
		background.setBackground(Color.black);
		timer = new Timer(1000, this);
		effectTimer = new Timer(30, this);
		effect = new Rectangle(0, rowRemoverYPosition, 0, getWidth() / 12);
		effect.setBackground(Color.lightGray);
		setLevelColor();
		timer.start();
		playMusic();
		
		
	}
	private void setLevelColor() {
		int x = Driver.scores.getLevel() % 10;
		switch (x) {
		case 0:
			levelColor = Color.red;
			break;
		case 1:
			levelColor = Color.white;
			break;
		case 2:
			levelColor = Color.pink;
			break;
		case 3:
			levelColor = Color.gray;
			break;
		case 4:
			levelColor = Color.blue;
			break;
		case 5:
			levelColor = Color.green;
			break;
		case 6:
			levelColor = Color.orange;
			break;
		case 7:
			levelColor = Color.cyan;
			break;
		case 8:
			levelColor = Color.yellow;
			break;
		default:
			levelColor = new Color(204, 153, 255);
		}
	}

	public boolean collided(Structure structure) {
		boolean collided = structure.checkCollision(background);
		return collided;
	}

	public void reachedBottom(Structure structure) {
		structure.reachedBottom();
		if (!gameOver((Container) structure)) {
			cutSlacks();
			addNewStructure();
			if(rowsRemoved > 0)
				CalculateScore();
		}
		pieceDropped = true;
	}

	private boolean gameOver(Container structure) {
		if (structure.getY() <= (getWidth() / 12) * 2) {
			timer.stop();
			JOptionPane.showMessageDialog(null, "Game Over");
			if (Driver.scores.getScore() > Driver.scores.getHighestScore()) {
				JOptionPane.showMessageDialog(null,
						"Congratulations! You have broken a new record!");
				Driver.scores.updateHighestScore();
			}
			return true;
		} else {
			return false;
		}
	}

	public void putRectangleAt(int x, int y, int w, int h) {
		Rectangle rectangle = new Rectangle(x, y, w, h);
		rectangle.setBackground(levelColor);
		add(rectangle, 0);
		repaint();
	}

	public void removeMe(Container c) {
		remove(c);
		repaint();
	}

	public boolean canTurn(Structure s) {
		boolean authorization = s.canTurn(background);
		return authorization;
	}

	public boolean canMoveToTheRight(Structure s) {
		boolean authorization = s.canMoveToRight(background);
		return authorization;
	}

	public boolean canMoveToTheLeft(Structure s) {
		boolean authorization = s.canMoveToLeft(background);
		return authorization;
	}

	private Structure getRandomStructure() {
		Random gen = new Random();
		int x = gen.nextInt(7);
		switch (x) {
		case 0:
			return new TStructure(getWidth() / 3, 0, this);
		case 1:
			return new SquareStructure(getWidth() / 3, 0, this);
		case 2:
			return new StickStructure(getWidth() / 3, 0, this);
		case 3:
			return new ZStructure(getWidth() / 3, 0, this);
		case 4:
			return new SStructure(getWidth() / 3, 0, this);
		case 5:
			return new LRightStructure(getWidth() / 3, 0, this);
		default:
			return new LLeftStructure(getWidth() / 3, 0, this);
		}
	}

	public void addNewStructure() {
		structure = getRandomStructure();
		add((Component) structure, -1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			structure.moveDown();
		} else if (e.getSource() == effectTimer) {
			JLabel score = new JLabel("" + Driver.scores.getScore());
			if (effect.getY() != rowRemoverYPosition) {
				effect.setLocation(0, rowRemoverYPosition);
			}
			if (effect.getWidth() == 0) {
				add(effect, 0);
			}
			if (effect.getWidth() >= getWidth()) {
				effect.setSize(0, effect.getHeight());
				remove(effect);
				removeRow(rowRemoverYPosition);
				effectTimer.stop();
				timer.start();
				cutSlacks();
			} else {
				effect.setSize(effect.getWidth() + (getWidth() / 24),
						effect.getHeight());
				score.setBounds(effect.getWidth() / 2, 0, effect.getWidth(),
						effect.getHeight());
				effect.removeAll();
				effect.add(score, 0);
			}
		}
		Driver.window.repaint();
	}
	public void playMusic()
	{
		s = new Sound("Tetris-TypeA.wav");
		s.playSound();
	}
	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == 10) {
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		} else if (timer.isRunning()) {
			if (k.getKeyCode() == 37) {
				structure.moveLeft();
			} else if (k.getKeyCode() == 40) {
				structure.moveDown();
			} else if (k.getKeyCode() == 39) {
				structure.moveRight();
			} else if (k.getKeyCode() == KeyEvent.VK_SPACE) {
				DropPiece();
			}else if (k.getKeyCode() == 38) {
				structure.turn();
			} else if(k.getKeyCode() == 192){
				s.changeTrack();
			}
			Driver.window.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	private void DropPiece()
	{
		while(!pieceDropped)
		{
			structure.moveDown();
		}
		pieceDropped = false;
	}

	private void cutSlacks() {
		boolean[] spot = new boolean[12];
		for (int y = (getWidth() / 12) * 5; y < getHeight(); y += getWidth() / 12) {
			for (int x = 0, z = 0; x < getWidth(); x += getWidth() / 12, z++) {
				if (findComponentAt(x, y) != background
						&& findComponentAt(x, y) != this) {
					spot[z] = true;
				}
			}
			checkRow(spot, y);
			for (int a = 0; a < spot.length; a++) {
				spot[a] = false;
			}
			
		}
	}

	private void CalculateScore() {
		int score = 0;
		switch(rowsRemoved)
		{
		case 1:
			score = 40;
			rowsRemoved = 0;
			break;
		case 2:
			score = 100;
			rowsRemoved = -1;
			break;
		case 3:
			score = 300;
			rowsRemoved = -3;
			break;
		case 4:
			score = 1200;
			rowsRemoved = -6;
			break;
		}
		Driver.scores.updateScore(Driver.scores.getScore() + (score*Driver.scores.getLevel()));
		//rowsRemoved = 0;
	}

	private void checkRow(boolean[] spot, int y) {
		boolean remove = true;
		for (int z = 0; z < spot.length && remove; z++) {
			if (!spot[z]) {
				remove = false;
			}
		}
		if (remove) {
			doEffect(y);
			rowsRemoved++;
		}
	}

	private void doEffect(int y) {
		timer.stop();
		rowRemoverYPosition = y;
		effectTimer.start();
	}

	private void removeRow(int y) {
		for (int x = 0; x < getWidth(); x += getWidth() / 12) {
			remove(findComponentAt(x, y));
		}
		pullPiecesDown(y);
		Driver.window.repaint();
	}

	private void pullPiecesDown(int z) {
		for (int y = z; y > (getWidth() / 12) * 5; y -= getWidth() / 12) {
			for (int x = 0; x < getWidth(); x += getWidth() / 12) {
				if (findComponentAt(x, y) == this
						&& findComponentAt(x, y - getWidth() / 12) != this) {
					Component s = findComponentAt(x, y - getWidth() / 12);
					s.setLocation(s.getX(), s.getY() + s.getHeight());
				}
			}
		}
	}

	public void updatePiecesColors() {
		setLevelColor();
		for (int y = (getWidth() / 12) * 5; y < getHeight(); y += getWidth() / 12) {
			for (int x = 0, z = 0; x < getWidth(); x += getWidth() / 12, z++) {
				if (findComponentAt(x, y) != background
						&& findComponentAt(x, y) != this) {
					findComponentAt(x, y).setBackground(levelColor);
				}
			}
		}
		updateSpeed();
		Driver.window.repaint();
	}

	private void updateSpeed() {
		if (timer.getDelay() - 50 > 100)
			timer.setDelay(timer.getDelay() - 50);
	}
}
