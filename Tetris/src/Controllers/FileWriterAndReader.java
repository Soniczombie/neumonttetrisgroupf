package Controllers;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileWriterAndReader {
	public static void writeScoreToFile(int x) {
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
				out.writeInt(x);
				out.close();
			} catch (Exception e) {
			}
		}
	}

	public static int readScoreFromFile() {
		int x = 0;
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream("data"));
		} catch (Exception e) {
			x = 0;
		}

		try {
			x = in.readInt();
			in.close();
		} catch (Exception e) {
		}
		return x;
	}
}
