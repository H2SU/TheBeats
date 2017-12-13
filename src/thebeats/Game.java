package thebeats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private TheBeat thebeat;

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/note_routeLine.png")).getImage();

	private Image comboImg = null;
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	private Image flareImage;
	private Image judgeImage;

	private String titleName;
	private String musicTitle;
	private Music gameMusic;

	private String combo = "";
	private int cb = 0;

	private Rank rank;
	private int count = 0;

	private NoteBeat[] beats = null;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String musicTitle, TheBeat thebeat) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		this.thebeat = thebeat;
		this.gameMusic = new Music(this.musicTitle, false);
	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteJImage, 540, 30, null);
		g.drawImage(noteRouteKImage, 644, 30, null);
		g.drawImage(noteRouteLImage, 748, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 640, 30, null);
		g.drawImage(noteRouteLineImage, 744, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		g.drawImage(flareImage, 360, 430, null);
		g.drawImage(judgeImage, 360, 400, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() >= 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
				rank.plusScore("Miss");
				resetCombo();

				if (count == beats.length - 1) { 
					gameEnd();
				} else
					++count; 

			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 620);
		g.drawString("D", 374, 620);
		g.drawString("F", 478, 620);
		g.drawString("J", 572, 620);
		g.drawString("K", 676, 620);
		g.drawString("L", 780, 620);
		g.setFont(new Font("Arial", Font.BOLD, 52));
		g.setColor(Color.white);
		g.drawString(combo, 1040, 400);
		g.drawImage(comboImg, 870, 250, null);

	}


	public void pressdKey(char key) {
		judge(key);

		switch (key) {
		case 'S':
			noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		case 'D':
			noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		case 'F':
			noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		case 'J':
			noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		case 'K':
			noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		case 'L':
			noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
			break;
		}

	}

	
	public void releasedKey(char key) {
		switch (key) {
		case 'S':
			noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		case 'D':
			noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		case 'F':
			noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		case 'J':
			noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		case 'K':
			noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		case 'L':
			noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
			break;
		}
	}

	@Override
	public void run() {
		dropNotes();
	}

	public void gameEnd() {
		Timer tm = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				close();
			}
		};
		tm.schedule(tt, 3000);
	}

	public void close() {
		System.out.println("??: " + rank.calculateGrade());
		gameMusic.close();
		this.interrupt();

		thebeat.endGame(rank);
	}

	public void dropNotes() {
		beats = null;
		if (titleName.equals("littlestar")) {
			int startTime = 350;
			int gap = 500;
			beats = new NoteBeat[] { new NoteBeat(startTime + gap, 'S'),
					new NoteBeat(startTime + gap * 2, 'S'),
					new NoteBeat(startTime + gap * 3, 'K'),
					new NoteBeat(startTime + gap * 4, 'K'),
					new NoteBeat(startTime + gap * 5, 'L'),
					new NoteBeat(startTime + gap * 6, 'L'),
					new NoteBeat(startTime + gap * 7, 'K'),
					new NoteBeat(startTime + gap * 9, 'J'),
					new NoteBeat(startTime + gap * 10, 'J'),
					new NoteBeat(startTime + gap * 11, 'F'),
					new NoteBeat(startTime + gap * 12, 'F'),
					new NoteBeat(startTime + gap * 13, 'D'),
					new NoteBeat(startTime + gap * 14, 'D'),
					new NoteBeat(startTime + gap * 15, 'S'),
					new NoteBeat(startTime + gap * 17, 'K'),
					new NoteBeat(startTime + gap * 18, 'K'),
					new NoteBeat(startTime + gap * 19, 'J'),
					new NoteBeat(startTime + gap * 20, 'J'),
					new NoteBeat(startTime + gap * 21, 'F'), 
					new NoteBeat(startTime + gap * 22, 'F'),
					new NoteBeat(startTime + gap * 23, 'D'),
					new NoteBeat(startTime + gap * 25, 'K'),
					new NoteBeat(startTime + gap * 26, 'K'),
					new NoteBeat(startTime + gap * 27, 'F'),
					new NoteBeat(startTime + gap * 28, 'F'),
					new NoteBeat(startTime + gap * 29, 'D'),
					new NoteBeat(startTime + gap * 30, 'D'),
					new NoteBeat(startTime + gap * 31, 'S'),
					new NoteBeat(startTime + gap * 33, 'S'),
					new NoteBeat(startTime + gap * 34, 'S'),
					new NoteBeat(startTime + gap * 35, 'K'),
					new NoteBeat(startTime + gap * 36, 'K'),
					new NoteBeat(startTime + gap * 37, 'L'), 
					new NoteBeat(startTime + gap * 38, 'L'),
					new NoteBeat(startTime + gap * 39, 'K'),
					new NoteBeat(startTime + gap * 41, 'J'),
					new NoteBeat(startTime + gap * 42, 'J'),
					new NoteBeat(startTime + gap * 43, 'F'),
					new NoteBeat(startTime + gap * 44, 'F'), 
					new NoteBeat(startTime + gap * 45, 'D'),
					new NoteBeat(startTime + gap * 46, 'D'),
					new NoteBeat(startTime + gap * 47, 'S'),

			};
		}
		else if(titleName.equals("cottoncandy")){
			  int startTime = 4950;
		         int gap= 248;
		         int gap2 = 124;
		         
		         beats = new NoteBeat[] { 
		               new NoteBeat(startTime + gap * 1 , 'S'), 
		               new NoteBeat(startTime + gap * 2, 'J'),
		               new NoteBeat(startTime + gap * 3, 'L'), 
		               new NoteBeat(startTime + gap * 4, 'J'),
		               new NoteBeat(startTime + gap * 5, 'S'),
		               
		               new NoteBeat(startTime + gap * 8, 'J'),
		               new NoteBeat(startTime + gap * 9, 'L'), 
		               new NoteBeat(startTime + gap * 11, 'J'),
		               
		               new NoteBeat(startTime + gap * 15, 'L'), 
		               new NoteBeat(startTime + gap * 16, 'S'),
		               new NoteBeat(startTime + gap * 18, 'S'), 
		               new NoteBeat(startTime + gap * 19, 'L'),
		               new NoteBeat(startTime + gap * 21, 'J'), 
		               
		               new NoteBeat(startTime + gap * 23, 'F'),
		               
		               new NoteBeat(startTime + gap * 30, 'S'),
		               new NoteBeat(startTime + gap * 31, 'J'),
		               new NoteBeat(startTime + gap * 32, 'L'),
		               new NoteBeat(startTime + gap * 33, 'J'),
		               new NoteBeat(startTime + gap * 34, 'S'),
		               
		               new NoteBeat(startTime + gap * 37, 'J'),
		               new NoteBeat(startTime + gap * 38, 'L'),
		               new NoteBeat(startTime + gap * 40, 'J'),
		               
		               new NoteBeat(startTime + gap * 44, 'J'),
		               new NoteBeat(startTime + gap * 45, 'S'),
		               new NoteBeat(startTime + gap * 47, 'S'),
		               new NoteBeat(startTime + gap * 48, 'D'),
		               new NoteBeat(startTime + gap * 50, 'F'),
		      
		               new NoteBeat(startTime + gap * 52, 'J'),
		               
		               new NoteBeat(startTime + gap2 * 118, 'D'),
		               new NoteBeat(startTime + gap2 * 120, 'D'),
		               new NoteBeat(startTime + gap2 * 122, 'D'),
		               new NoteBeat(startTime + gap2 * 124, 'J'),
		               new NoteBeat(startTime + gap2 * 126  , 'D'),
		                
		               new NoteBeat(startTime + gap2 * 133, 'L'),
		               new NoteBeat(startTime + gap2 * 135, 'L'),
		               new NoteBeat(startTime + gap2 * 137, 'L'),
		               new NoteBeat(startTime + gap2 * 139, 'K'),
		               new NoteBeat(startTime + gap2 * 141, 'J'),
		               
		               new NoteBeat(startTime + gap2 * 146, 'K'),
		               new NoteBeat(startTime + gap2 * 148, 'K'), 
		               new NoteBeat(startTime + gap2 * 152, 'K'),
		               new NoteBeat(startTime + gap2 * 154, 'K'),
		               new NoteBeat(startTime + gap2 * 158, 'L'),
		               
		               new NoteBeat(startTime + gap2 * 162, 'K'),
		               
		               new NoteBeat(startTime + gap2 * 175, 'L'),
		               new NoteBeat(startTime + gap2 * 179, 'L'),
		               new NoteBeat(startTime + gap2 * 183, 'J'),
		               new NoteBeat(startTime + gap2 * 185, 'J'),
		               new NoteBeat(startTime + gap2 * 188, 'J'),
		               
		               new NoteBeat(startTime + gap2 * 191, 'K'),
		               new NoteBeat(startTime + gap2 * 193, 'K'),
		               new NoteBeat(startTime + gap2 * 195, 'J'),
		               new NoteBeat(startTime + gap2 * 197, 'F'),
		               new NoteBeat(startTime + gap2 * 201, 'D'),
		               new NoteBeat(startTime + gap2 * 203, 'D'),
		               
		               new NoteBeat(startTime + gap2 * 206, 'S'),
		               new NoteBeat(startTime + gap2 * 208, 'L'),
		               new NoteBeat(startTime + gap2 * 210, 'J'),
		               new NoteBeat(startTime + gap2 * 212, 'K'),
		               new NoteBeat(startTime + gap2 * 216, 'K'),
		               
		               new NoteBeat(startTime + gap2 * 220, 'J'),
		            
		         };
				}
		else if(titleName.equals("jinglebell")) {
			 int startTime = 1400;
	         int gap= 248;
	         int gap2 = 124;
	         
	         beats = new NoteBeat[] { 
	               new NoteBeat(startTime + gap * 1 , 'S'), 
	               new NoteBeat(startTime + gap * 2, 'J'),
	               new NoteBeat(startTime + gap * 3, 'L'), 
	               new NoteBeat(startTime + gap * 4, 'J'),
	               new NoteBeat(startTime + gap * 5, 'S'),
	               
	               new NoteBeat(startTime + gap * 8, 'J'),
	               new NoteBeat(startTime + gap * 9, 'L'), 
	               new NoteBeat(startTime + gap * 11, 'J'),
	               
	               new NoteBeat(startTime + gap * 15, 'L'), 
	               new NoteBeat(startTime + gap * 16, 'S'),
	               new NoteBeat(startTime + gap * 18, 'S'), 
	               new NoteBeat(startTime + gap * 19, 'L'),
	               new NoteBeat(startTime + gap * 21, 'J'), 
	               
	               new NoteBeat(startTime + gap * 23, 'F'),
	               
	               new NoteBeat(startTime + gap * 30, 'S'),
	               new NoteBeat(startTime + gap * 31, 'J'),
	               new NoteBeat(startTime + gap * 32, 'L'),
	               new NoteBeat(startTime + gap * 33, 'J'),
	               new NoteBeat(startTime + gap * 34, 'S'),
	               
	               new NoteBeat(startTime + gap * 37, 'J'),
	               new NoteBeat(startTime + gap * 38, 'L'),
	               new NoteBeat(startTime + gap * 40, 'J'),
	               
	               new NoteBeat(startTime + gap * 44, 'J'),
	               new NoteBeat(startTime + gap * 45, 'S'),
	               new NoteBeat(startTime + gap * 47, 'S'),
	               new NoteBeat(startTime + gap * 48, 'D'),
	               new NoteBeat(startTime + gap * 50, 'F'),
	      
	               new NoteBeat(startTime + gap * 52, 'J'),
	               
	               new NoteBeat(startTime + gap2 * 118, 'D'),
	               new NoteBeat(startTime + gap2 * 120, 'D'),
	               new NoteBeat(startTime + gap2 * 122, 'D'),
	               new NoteBeat(startTime + gap2 * 124, 'J'),
	               new NoteBeat(startTime + gap2 * 126  , 'D'),
	                
	               new NoteBeat(startTime + gap2 * 133, 'L'),
	               new NoteBeat(startTime + gap2 * 135, 'L'),
	               new NoteBeat(startTime + gap2 * 137, 'L'),
	               new NoteBeat(startTime + gap2 * 139, 'K'),
	               new NoteBeat(startTime + gap2 * 141, 'J'),
	               
	               new NoteBeat(startTime + gap2 * 146, 'K'),
	               new NoteBeat(startTime + gap2 * 148, 'K'), 
	               new NoteBeat(startTime + gap2 * 152, 'K'),
	               new NoteBeat(startTime + gap2 * 154, 'K'),
	               new NoteBeat(startTime + gap2 * 158, 'L'),
	               
	               new NoteBeat(startTime + gap2 * 162, 'K'),
	               
	               new NoteBeat(startTime + gap2 * 175, 'L'),
	               new NoteBeat(startTime + gap2 * 179, 'L'),
	               new NoteBeat(startTime + gap2 * 183, 'J'),
	               new NoteBeat(startTime + gap2 * 185, 'J'),
	               new NoteBeat(startTime + gap2 * 188, 'J'),
	               
	               new NoteBeat(startTime + gap2 * 191, 'K'),
	               new NoteBeat(startTime + gap2 * 193, 'K'),
	               new NoteBeat(startTime + gap2 * 195, 'J'),
	               new NoteBeat(startTime + gap2 * 197, 'F'),
	               new NoteBeat(startTime + gap2 * 201, 'D'),
	               new NoteBeat(startTime + gap2 * 203, 'D'),
	            
	         };
		}
		int i = 0;
		gameMusic.start(); 
		rank = new Rank(beats.length);

		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;

			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void judge(char input) {

		if (count == beats.length - 1) { 
			System.out.println("?? �??? ?��??????��?");
			gameEnd();
		}
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input == note.getNoteType()) {
				rank.plusScore(judgeEvent(note.judge()));
				++count;
				break;
			}
		}
	}

	public void resetCombo() {
		comboImg = null;
		combo = "";
		cb = 0;

	}

	public void goCombo(String rank) {
		comboImg = new ImageIcon(Main.class.getResource("../images/combo.png")).getImage();
		combo = String.valueOf(++cb);
	}

	public String judgeEvent(String judge) {
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			resetCombo();
		} else if (judge.equals("Bad")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/bad.png")).getImage();
			goCombo(judge);
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
			goCombo(judge);
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
			goCombo(judge);
		}

		return judge;

	}

}
