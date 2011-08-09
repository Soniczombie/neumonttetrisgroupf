package Views;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;

import Controllers.Driver;
import Controllers.FileWriterAndReader;
import Controllers.Score;

public class ScoreBoard extends Container {
	private static final long serialVersionUID = 1L;
	private JLabel score, level, highestScore,rows;
	private int intScore, intLevel = 1, intHighestScore, clearedLines = 0;
	private Driver driver;

	public ScoreBoard(int x, int y, int w, int h, Driver d) {
		super();
		setBounds(x, y, w, h);
		Font font = new Font("Serif", Font.BOLD, 28);
		
		Score[] scores = FileWriterAndReader.readScoresFromFile();
		intHighestScore = 0;
		for (Score score : scores) {
			if(score.getValue() > intHighestScore)
				intHighestScore = score.getValue();
		}
		
		
		score = new JLabel(intScore+"");
		score.setForeground(Color.cyan);
		score.setFont(font);
		
		level = new JLabel("Level: " + intLevel);
		level.setFont(font);
		level.setForeground(Color.cyan);
		
		rows = new JLabel("Rows: "+clearedLines);
		rows.setFont(font);
		rows.setForeground(Color.cyan);
		
		JLabel high = new JLabel("High: ");
		high.setFont(font);
		high.setForeground(Color.cyan);
		
		highestScore = new JLabel(intHighestScore+"");
		highestScore.setFont(font);
		highestScore.setForeground(Color.cyan);
	
		
		score.setBounds(w / 8, 0, w, h / 4);
		high.setBounds(w / 8, 40, w, h / 4);
		highestScore.setBounds(w / 8, 65, w, h / 4);
		level.setBounds(w / 8, 100, w, h / 4);
		rows.setBounds(w/8, 125,w,h/4);
		
		add(score, 0);
		add(high,0);
		add(level, 0);
		add(highestScore, 0);
		add(rows,0);
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
		score.setText("" + x);
		updateRows();
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
//		FileWriterAndReader.writeScoreToFile(intHighestScore);
	}
	
	private void updateRows(){
		rows.setText("Rows: "+clearedLines);
	}

}
