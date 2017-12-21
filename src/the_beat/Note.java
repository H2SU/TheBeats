package the_beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image notebasicImage = new ImageIcon(Main.class.getResource("../images/note_basic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // -120 y 1초에 580
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


	public Note(char noteType) { // x동적으로

		switch (noteType) {
		case 'S':
			x = 228;
			break;
		case 'D':
			x = 332;
			break;
		case 'F':
			x = 436;
			break;
		case 'J':
			x = 540;
			break;
		case 'K':
			x = 644;
			break;
		case 'L':
			x = 748;
			break;
		}

		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(notebasicImage, x, y, null);
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y >= 620) {
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) { // 1초에 700픽셀 만큼 아래쪽으로 떨어짐
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String judge() {
	      if (y >= 620) {
	         close();
	         return "Miss";
	      } else if (y >= 605) {
	         close();
	         return "Bad";

	      } else if (y >= 590) {
	         close();
	         return "Good";

	      } else if (y >= 575) {
	         close();
	         return "Perfect";

	      } else if (y >= 555) {
	         close();
	         return "Good";

	      } else if (y >= 540) {
	         close();
	         return "Bad";

	      }
	      return "None";
	   }
	
	public int getY() {
		return y;
	}
}