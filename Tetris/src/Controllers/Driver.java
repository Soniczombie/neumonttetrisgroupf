package Controllers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.GamePanel;
import Model.NextPiecePanel;
import Model.Rectangle;
import Views.HighScoresTable;
import Views.ScoreBoard;

@SuppressWarnings("serial")
public class Driver extends JFrame{
	public static Driver window;
	public static GameArea gameArea;
	public static ScoreBoard scores;
	public Sound s;
	ImageIcon bg = new ImageIcon("backgrounds/NGC3021.jpg");
	public NextPiecePanel np;
	MainMenu menu;

	public Driver() {
		window = this;
		menu = new MainMenu();
	}

	public void updatePiecesColors() {
		gameArea.updatePiecesColors();
	}

	public void createWindow() {
		window.setBounds(400, 0, 818, 1080);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Background background = new Background(bg);
		background.setBounds(0,0,818,1080);
		this.add(background,0);
	}
	
	public void startGame(){
		createWindow();
		
		Rectangle divider = new Rectangle(600, 0, 2, this.getHeight(), null);
		divider.setBackground(Color.cyan);
		divider.setOpaque(true);
		this.add(divider,0);
		
		scores = new ScoreBoard(0, 200, 200, 200, this);		
		gameArea = new GameArea(0, 0, 600, 1045);
		GamePanel gp = new GamePanel();		
		gp.setBounds(gameArea.getWidth()+2,0,200,1085);
		
		np = new NextPiecePanel();
		np.addNewStructure();
		
		gp.add(np);
		gp.add(scores);
		
		gameArea.addNewStructure(np);
		gameArea.setOpaque(false);
		gp.setOpaque(false);
		
		this.addKeyListener(gameArea);
		this.add(gameArea,0);
		this.add(gp,0);
		gameArea.playMusic();
		this.setVisible(true);
	}
	
	/**
	 * Sets up and displays the High Scores Screen. 
	 */
	public void HighScores()
	{
		JFrame window = new JFrame();
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
	public void Instructions()
	{
		JFrame window = new JFrame();
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

	public static void main(String[] s) {
		new Driver();
		
	}

	class Background extends Rectangle{

		public Background(ImageIcon ic){
			super(0,0,818,1080,ic);
		}

//		protected void paintComponent(Graphics g){
//			super.paintComponent(g);
//			g.drawImage(ic.getImage(),0,0,this.getWidth(),this.getHeight(),null);
//			this.setSize(this.getParent().getSize());

//		}
	}

}
