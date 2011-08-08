package Controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FileWriterAndReader {
	public static void writeScoreToFile(Score... scores) {
		boolean repeat = true;
		DataOutputStream out = null;
		File data = null;
		while (repeat) {
			try {
				out = new DataOutputStream(new FileOutputStream(data));
				repeat = false;
			} catch (Exception e) {
				data = new File("data");
				repeat = true;
			}

			try {
				for (Score score : scores) {
					out.writeUTF(score.getName());
					out.writeInt(score.getValue());
					out.writeChar('\n');
				}
				out.close();
			} catch (Exception e) {
			}
		}
	}

	public static Score[] readScoresFromFile() {
		int x = 0;
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream("data"));
		} catch (Exception e) {
			x = 0;
		}
		ArrayList<Score> scores = new ArrayList<Score>();
		try {
			while(in.available()>0){//There are scores in the file
				String scoreName = in.readUTF();
				int scoreValue = in.readInt();
				in.readChar();//Jump to the next line
				Score score = new Score();
				score.setName(scoreName);
				score.setValue(scoreValue);
				scores.add(score);
			}
			in.close();
		} catch (Exception e) {
		}
		return scores.toArray(new Score[0]);
	}
}
