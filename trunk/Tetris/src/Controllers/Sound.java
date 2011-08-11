package Controllers;

import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound  // Holds one audio file
{
	private AudioClip song; // Sound player
	private URL songPath; // Sound path
	private int CurrentSong;
	private String FileName;

	public Sound(String filename) {
		try {
			CurrentSong = 1;
			FileName = filename;
			String prefix = System.getProperty("user.dir");
			File soundFile = new File(prefix+System.getProperty("file.separator")+"sounds"+System.getProperty("file.separator")+FileName);
			songPath = soundFile.toURI().toURL(); // Get the Sound URL
			song = Applet.newAudioClip(songPath); // Load the Sound
		} catch (Exception e) {
			e.printStackTrace();
		} // Satisfy the catch
		
	}

	public void playSound() {
		song.loop(); // Play
	}

	public void stopSound() {
		song.stop(); // Stop
	}

	public void playSoundOnce() {
		song.play(); // Play only once
	}
	
	public void changeTrack()
	{
			this.stopSound();
			switch(CurrentSong)
			{
			case 1:
				FileName = "Tetris-TypeB.wav";
				CurrentSong = 2;
				break;
			case 2:
				FileName = "Tetris-TypeC.wav";
				CurrentSong = 3;
				break;
			case 3:
				FileName = "Pokemon.wav";
				CurrentSong = 4;
				break;
			case 4:
				FileName = "8-BitFriday.wav";
				CurrentSong = 5;
				break;
			case 5:
				FileName = "Tetris-TypeA.wav";
				CurrentSong = 1;
				break;
			}
			String prefix = System.getProperty("user.dir");
			File soundFile = new File(prefix+System.getProperty("file.separator")+"sounds"+System.getProperty("file.separator")+FileName);
			try {
				songPath = soundFile.toURI().toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Get the Sound URL
			song = Applet.newAudioClip(songPath); // Load the Sound
			this.playSound();
		
	}
		


	
}
