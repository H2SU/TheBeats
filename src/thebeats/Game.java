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
	
	TheBeat thebeat;
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/note_routeLine.png")).getImage();

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

	private Rank rank; // 판단
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
		g.drawImage(judgeImage, 360, 420, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() >= 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
				rank.plusScore("miss");

				if (count == beats.length - 1) { // 노트의 갯수만큼 이 메소드가 호출되었으면 게임 종료.
					gameEnd();
				} else
					++count; // 호출횟수 증가

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
	}

	/**
	 * 키 눌렀을때
	 * 
	 * @param key
	 */
	public void pressKey(char key) {
		judge(String.valueOf(key)); // judge호출

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

	/**
	 * 키 뗐을 때
	 * 
	 * @param key
	 */
	public void releaseKey(char key) {
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

	/**
	 * 게임 끝났을 때. 3초 지연.
	 */
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
		System.out.println("끝" + rank.calculateGrade());
		gameMusic.close();
		this.interrupt();
		
		thebeat.gameEnd(rank);
	}

	public void dropNotes() {
		beats = null;
		if (titleName.equals("summer")) {
			int startTime = 1000 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new NoteBeat[] { new NoteBeat(startTime + gap * 2, "J"), new NoteBeat(startTime + gap * 4, "S"),
					new NoteBeat(startTime + gap * 6, "J"), new NoteBeat(startTime + gap * 8, "K"),
					new NoteBeat(startTime + gap * 10, "D"), new NoteBeat(startTime + gap * 12, "K"),
					new NoteBeat(startTime + gap * 14, "D"), new NoteBeat(startTime + gap * 16, "L"),
					new NoteBeat(startTime + gap * 18, "F"), new NoteBeat(startTime + gap * 20, "L"),

					
			};
		}
		int i = 0;
		gameMusic.start(); // 음악 시작
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
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}


	public void judge(String input) {

		if (count == beats.length - 1) { // 노트의 갯수만큼 이 메소드가 호출되었으면 게임 종료.
			gameEnd();
		}
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				String s = "";
				judgeEvent(s = note.judge());
				rank.plusScore(s); // 점수 더하기
				++count; // judge메소드가 호출된 횟수 증가시켜줌
				break;
			}

		}

	}

	public void judgeEvent(String judge) {
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		} else if (judge.equals("Bad")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/bad.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
		}

	}

}
