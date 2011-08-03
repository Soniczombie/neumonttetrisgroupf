/**
 * 
 */
package Views;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controllers.FileWriterAndReader;
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
		mainMenu = new JFrame();
		mainMenu.setBounds(r);
		
		setBounds(x, y, w, h);
		frame = new Rectangle(0, 0, w, h);
		frame.setBackground(Color.gray);
		background = new Rectangle(w / 10, h / 10, (int) (.8 * w),
				(int) (.8 * h));
		background.setBackground(new Color(204, 255, 204));
		score = new JLabel("Score: " + intScore);
		level = new JLabel("Level: " + intLevel);
		highestScore = new JLabel("Record: "
				+ FileWriterAndReader.readScoreFromFile());
		intHighestScore = FileWriterAndReader.readScoreFromFile();
		highestScore.setBounds(w / 8, (int) (0.6 * h), w, h / 4);
		score.setBounds(w / 8, h / 8, w, h / 4);
		level.setBounds(w / 8, h / 3, w, h / 4);
		add(frame, 0);
		add(background, 0);
		add(score, 0);
		add(level, 0);
		add(highestScore, 0);
		driver = d;
	}
	
	/**
	 * 
	 */
	public void Buttons()
	{
		
	}
}
