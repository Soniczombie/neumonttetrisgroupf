/**
 * 
 */
package Views;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controllers.GameArea;

/**
 * @author mestrada
 *
 */
public class PauseMenu extends Container{
	private GameArea game;

	/**
	 * 
	 */
	public PauseMenu(GameArea game){
		this.game = game;
		int w = game.getWidth()/3;
		int h = game.getHeight()/3;
		this.setBounds(w,h,w,h);
	}
	
	/**
	 * Buttons
	 */
	public void Buttons()
	{
		JButton resume = new JButton("Resume Game");
		resume.setBounds(0, 0, this.getWidth(), this.getHeight()/4);
		resume.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getTimer().start();
				setEnabled(false);
			}
		});
	}
	
	public void configButton(JButton b){
		Font font = new Font("Serif", Font.BOLD, 28);
		b.setOpaque(true);
		b.setFont(font);
		this.add(b);
	}
}
