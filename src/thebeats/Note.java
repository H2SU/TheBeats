package thebeats;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{

	private Image notebasicImage = new ImageIcon(Main.class.getResource("../images/note_basic.png")).getImage(); 
	private int x, y=580-(1000/Main.SLEEP_TIME* Main.NOTE_SPEED) * Main.REACH_TIME; //-120 y 1초에 580  
	private char noteType;
	private boolean proceeded = true;
	
	public char getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(char noteType) { //x동적으로 
		if(noteType=='S') {
			x=228;
		}	
		else if(noteType=='D') {
			x=332;
		}
		else if(noteType=='F') {
			x=436;
		}
		else if(noteType=='J') {
			x=540;
		}
		else if(noteType=='K') {
			x=644;
		}
		else if(noteType=='L') {
			x=748;
		}
		this.noteType=noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(notebasicImage, x, y, null);	//다 숄트니까 
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;	
		if( y>=620 ) {
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) { //1초에 700픽셀 만큼 아래쪽으로 떨어짐 
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public String judge() {
		if(y>= 620) {
			close();
			return "Miss";
		}
		else if( y>=610) {
			close();
			return "Bad";

		}
		else if( y>=590) {
			close();
			return "Good";

		}
		else if( y>=580) {
			close();
			return "Perfect";

		}
		else if( y>=565) {
			close();
			return "Good";

		}
		else if( y>=550) {
			close();
			return "Bad";

		}
		return "None";
	}
	public int getY() {
		return y;
	}
}
