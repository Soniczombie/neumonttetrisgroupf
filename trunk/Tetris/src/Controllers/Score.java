package Controllers;

public class Score {
	
	private String name;
	private int value;
	
	public Score(){
		
	}
	
	public Score(String name, int value){
		this.setName(name);
		this.setValue(value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int score) {
		this.value = score;
	}

}
