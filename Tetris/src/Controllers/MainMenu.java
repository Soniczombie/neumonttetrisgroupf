/**
 * 
 */
package Controllers;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Rectangle;


/**
 * @author mestrada
 *
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {
	
	private JButton playGame, highScores, Instructions;
	public JFrame window;
	public Driver game;

	/**
	 * Main Menu
	 */
	public MainMenu()
	{
		//Frame
		game = Driver.window;
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setBounds(500, 500, 320, 385);
		window.setBackground(Color.BLACK);
		window.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/tetris.png");
		window.setIconImage(icon);
		MainMenu.Background background = new Background(new ImageIcon("images/tetris_logo.jpg"));
		window.add(background);
		PlayGame();
		HighScores();
		Instructions();
		window.setVisible(true);
	}
	
	/**
	 * Play Game Button
	 */
	public void PlayGame()
	{
		playGame = new JButton();
		playGame.setText("Play Game");
		playGame.setBounds(90, 150, 140, 20);
		playGame.setBackground(Color.black);
		playGame.setForeground(Color.blue);
		playGame.setActionCommand("Play");
		playGame.addActionListener(this);
		playGame.setVisible(true);
		window.add(playGame,0);
	}
	
	/**
	 * High Score Button
	 */
	public void HighScores()
	{
		highScores = new JButton();
		highScores.setBounds(90, 180, 140, 20);
		highScores.setText("High Scores");
		highScores.setBackground(Color.black);
		highScores.setForeground(Color.blue);
		highScores.setActionCommand("HighScore");
		highScores.addActionListener(this);
		highScores.setVisible(true);
		window.add(highScores,0);
	}
	
	/**
	 * Instructions Button
	 */
	public void Instructions()
	{
		Instructions = new JButton();
		Instructions.setBounds(90, 210, 140, 20);
		Instructions.setText("Instructions");
		Instructions.setBackground(Color.black);
		Instructions.setForeground(Color.blue);
		Instructions.setActionCommand("Instructions");
		Instructions.addActionListener(this);
		Instructions.setVisible(true);
		window.add(Instructions,0);
	}

	/**
	 * The Overridden method that handles the actions performed. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String Action;
	    Action = e.getActionCommand ();
	    if (Action.equals ("Play")) {
	    	WindowEvent wev = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
	    	Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	    	game.createWindow();
	    	game.startGame();
	    } 
	    else if(Action.equals("HighScore")){
	    	game = new Driver();
	    	game.HighScores();
	    } 
	    else if(Action.equals("Instructions")){
	    	game = new Driver();
	    	game.Instructions();
	    }
	}
	
	class Background extends Rectangle{

		public Background(ImageIcon ic){
			super(0,0,window.getWidth(),window.getHeight(),ic);
		}
	}
}