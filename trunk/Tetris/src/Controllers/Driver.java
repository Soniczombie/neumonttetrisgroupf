package Controllers;

import javax.swing.*;

import Views.ScoreBoard;

public class Driver {
	public static JFrame window;
	private static GameArea gameArea;
	public static ScoreBoard scores;

	public Driver() {
		createWindow();
		scores = new ScoreBoard(450, 0, 150, 100, this);
		gameArea = new GameArea(0, 0, 600, 1045);
		window.addKeyListener(gameArea);
		gameArea.addNewStructure();
		window.add(gameArea);
		window.add(scores, 0);
		window.setVisible(true);
		window.repaint();
	}

	public void updatePiecesColors() {
		gameArea.updatePiecesColors();
	}

	private void createWindow() {
		window = new JFrame();
		window.setBounds(400, 0, 620, 1100);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] s) {
		new Driver();
	}

}
