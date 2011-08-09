package testing;

import Controllers.Sound;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Tester();

	}
	
	public Tester() {
		Sound s = new Sound("Pokemon.wav");
		s.playSound();
	}

}
