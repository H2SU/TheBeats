package thebeats;

public class NoteBeat {
	private int time;
	private char noteName;
	
	public NoteBeat(int time, char noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public char getNoteName() {
		return noteName;
	}
	
	public void setNoteName(char noteName) {
		this.noteName = noteName;
	}
	
	
}
