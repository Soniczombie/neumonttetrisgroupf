package Views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

import Controllers.FileWriterAndReader;
import Controllers.Score;

public class HighScoresTable extends JComponent {
	private static final long serialVersionUID = 1L;
	private Score[] scores;
	private JLabel scoresLabel;
	private JLabel namesLabel;

	public HighScoresTable() {
		scores = FileWriterAndReader.readScoresFromFile();
		instantiateLabels();
		this.setLayout(new GridLayout(1, 2));
		populateJLabelsWithScores();
		this.add(namesLabel);
		this.add(scoresLabel);
	}

	private void instantiateLabels() {
		scoresLabel = new JLabel("",JLabel.CENTER);
		scoresLabel.setForeground(Color.WHITE);
		scoresLabel.setVerticalTextPosition(JLabel.TOP);
		namesLabel = new JLabel("",JLabel.CENTER);
		namesLabel.setForeground(Color.WHITE);
		namesLabel.setVerticalTextPosition(JLabel.TOP);
	}

	private void populateJLabelsWithScores() {
		namesLabel.setText("<html>");
		scoresLabel.setText("<html>");
		for (Score score : scores) {
			namesLabel
					.setText(namesLabel.getText() + "<br/>" + score.getName());
			scoresLabel.setText(scoresLabel.getText() + "<br/>"
					+ score.getValue());
		}
		namesLabel.setText(namesLabel.getText() + "</html>");
		scoresLabel.setText(scoresLabel.getText() + "</html>");
	}

}
