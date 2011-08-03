package Views;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;

import Controllers.Driver;
import Controllers.FileWriterAndReader;
import Model.Rectangle;

public class ScoreBoard extends Container {
	private static final long serialVersionUID = 1L;
	private Rectangle frame, background;
	private JLabel score, level, highestScore;
	private int intScore, intLevel = 1, intHighestScore, clearedLines = 0;
	private Driver driver;

	public ScoreBoard(int x, int y, int w, int h, Driver d) {
		super();
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

	public int getLevel() {
		return intLevel;
	}

	public void updateScore(int x) {
		clearedLines++;
		if (clearedLines == 10) {
			updateLevel(getLevel() + 1);
			clearedLines = 0;
		}
		intScore = x;
		score.setText("Score: " + x);
	}

	public void updateLevel(int x) {
		intLevel = x;
		level.setText("Level: " + x);
		driver.updatePiecesColors();
	}

	public int getScore() {
		return intScore;
	}

	public int getHighestScore() {
		return intHighestScore;
	}

	public void updateHighestScore() {
		intHighestScore = intScore;
		highestScore.setText("Record: " + intHighestScore);
		FileWriterAndReader.writeScoreToFile(intHighestScore);
	}

}
