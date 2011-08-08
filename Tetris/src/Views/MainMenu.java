/**
 * 
 */
package Views;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Controllers.Driver;

/**
 * @author mestrada
 *
 */
@SuppressWarnings("serial")
public class MainMenu extends Container implements ActionListener {
	
	private JButton playGame, highScores, Instructions;
	private JFrame window;
	public Driver game;

	/**
	 * Main Menu
	 */
	public MainMenu()
	{
		//Frame
		window = new JFrame();
		window.setBounds(500, 500, 320, 385);
		window.setBackground(Color.BLACK);
		window.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/tetris.png");
		window.setIconImage(icon);
		ImagePanel image = new ImagePanel("images/tetris_logo.jpg");
		window.add(image);
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
	    	game = new Driver();
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
}

/**
 * 
 * @author mestrada
 *Class for creating an image and setting it to the frame.
 */
@SuppressWarnings("serial")
class ImagePanel extends JPanel {
	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}
