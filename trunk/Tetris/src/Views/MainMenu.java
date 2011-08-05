/**
 * 
 */
package Views;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Model.Rectangle;

/**
 * @author mestrada
 *
 */
public class MainMenu {
	
	private JButton playGame, highScores, Instructions;
	private JLabel something;
	private JFrame mainMenu;
	private Rectangle r;

	/**
	 * 
	 */
	public MainMenu()
	{
		//Frame
		mainMenu = new JFrame();
		r = new Rectangle(4,4,4,4, null);
		mainMenu.setBounds(500, 500, 150, 300);
		mainMenu.setBackground(Color.gray);
		
	}
	
	/**
	 * Buttons
	 */
	public void Buttons()
	{
		playGame = new JButton();
		playGame.setLocation(50, 50);
		//playGame.addActionListener(i);
	}
}
