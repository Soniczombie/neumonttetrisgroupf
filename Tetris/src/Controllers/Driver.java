package Controllers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Views.HighScoresTable;
import Views.MainMenu;
import Views.ScoreBoard;

public class Driver {
	public static JFrame window;
	private static GameArea gameArea;
	public static ScoreBoard scores;
	public MainMenu mainMenu;
	public Sound s;

	public Driver() {
	}
	
	public void startGame()
	{
		scores = new ScoreBoard(450, 0, 150, 100, this);
		gameArea = new GameArea(0, 0, 600, 1045, this);
		gameArea.setBounds(0, 0, 600, 1045);
		window.addKeyListener(gameArea);
		gameArea.addNewStructure();
		Image icon = Toolkit.getDefaultToolkit().getImage("images/tetris.png");
		window.setIconImage(icon);
		window.add(gameArea);
		window.add(scores, 0);
		window.setVisible(true);
		window.repaint();
		//playMusic();
	}
	public void playMusic()
	{
		s = new Sound("Tetris-TypeA.wav");
		s.playSound();
	}
	public void updatePiecesColors() {
		gameArea.updatePiecesColors();
	}

	public void CloseWindow()
	{
		this.window.dispose();
	}
	
	public void createWindow() {
		window = new JFrame();
		window.setBounds(450, 100, 620, 1090);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Instructions()
	{
		window = new JFrame();
		window.setBounds(600, 200, 410, 340);
		window.setBackground(Color.black);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/tetris.png");
		window.setIconImage(icon);
		
		//Label
		JLabel title = new JLabel();
		title.setText("Instructions");
		title.setBounds(165, 10, 100, 15);
		title.setBackground(Color.white);
		title.setVisible(true);
		
		//Text Area
		JTextArea instructions = new JTextArea(5,20);
		instructions.setBounds(5, 30, 382, 265);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setEditable(false);
		instructions.setText("For the very few people who don't know how to play Tetris: blocks of different shapes drop from the top of the screen into a box. " +
				"Each block is made up of four small squares arranged to make a larger square, an L-shape or a column. As the blocks fall they can be rotated or " +
				"moved horizontally so that every space in the box is filled. When a horizontal line is completed, that line is 'destroyed' giving you more points " +
				"and moving the rest of the placed pieces down by one square. If a line remains incomplete, another line must be finished above it. The more " +
				"lines that stand incomplete, the higher the blocks above them stack, reducing the space in which falling shapes can be manipulated. " +
				"Eventually the blocks reach the top of the screen and the game ends. The statistics box at the left of the screen shows the number of shapes " +
				"of different colours that have been positioned, and another box at the bottom right shows what shape of block will appear next. There are ten " +
		"skill levels; the higher the level, the faster the blocks fall.");
		instructions.setVisible(true);
		
		//Scroll Pane
		JScrollPane scrollPane = new JScrollPane(instructions); 
		scrollPane.setPreferredSize(new Dimension(400, 500));
		scrollPane.setVisible(true);
		
		window.add(title);
		window.add(instructions);
		window.add(scrollPane);
		window.setVisible(true);
		window.repaint();
	}
	
	/**
	 * Sets up and displays the High Scores Screen. 
	 */
	public void HighScores()
	{
		window = new JFrame();
		window.setBounds(600, 200, 410, 340);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/tetris.png");
		window.setIconImage(icon);
		
		//Label
		JLabel title = new JLabel();
		title.setForeground(Color.white);
		title.setText("High Scores");
		title.setBounds(165, 10, 100, 15);
		title.setBackground(Color.white);
		title.setVisible(true);
		window.add(title);
		HighScoresTable highScoresContainer = new HighScoresTable();
		highScoresContainer.setBounds(0, 15, 410, 325);
		window.add(highScoresContainer);
		
		
		window.setVisible(true);
		window.repaint();
	}

	public static void main(String[] s) {
		MainMenu main = new MainMenu();
	}


}
