package the_beat;

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
	private Image judgeImage;

	private String titleName;
	private String musicTitle;
	private Music gameMusic;

	private String combo = "";
	private int cb = 0;

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
		
		g.drawImage(judgeImage, 360, 400, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() >= 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
				rank.plusScore("Miss");
				resetCombo();

				if (count == beats.length - 1) { // 노트의 갯수만큼 이 메소드가 호출되었으면 게임 종료.
					System.out.println("이 것은 걍지나간곳에서호출");
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
		g.setFont(new Font("Arial", Font.BOLD, 52));
		g.setColor(Color.white);
		g.drawString(combo, 1040, 400);
		g.drawImage(comboImg, 870, 250, null);

	}

	/**
	 * 키 눌렀을때
	 *
	 * @param key
	 */
	public void pressdKey(char key) {
		judge(key); // judge호출

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
		System.out.println("끝: " + rank.calculateGrade());
		gameMusic.close();
		this.interrupt();

		thebeat.endGame(rank);
	}

	public void dropNotes() {
		beats = null;
		if (titleName.equals("littlestar")) {
			int startTime = 350;
			int gap = 500;
			beats = new NoteBeat[] { new NoteBeat(startTime + gap, 'S'), new NoteBeat(startTime + gap * 2, 'S'),
					new NoteBeat(startTime + gap * 3, 'K'), new NoteBeat(startTime + gap * 4, 'K'),
					new NoteBeat(startTime + gap * 5, 'L'), new NoteBeat(startTime + gap * 6, 'L'),
					new NoteBeat(startTime + gap * 7, 'K'), new NoteBeat(startTime + gap * 9, 'J'),
					new NoteBeat(startTime + gap * 10, 'J'), new NoteBeat(startTime + gap * 11, 'F'),
					new NoteBeat(startTime + gap * 12, 'F'), new NoteBeat(startTime + gap * 13, 'D'),
					new NoteBeat(startTime + gap * 14, 'D'), new NoteBeat(startTime + gap * 15, 'S'),
					new NoteBeat(startTime + gap * 17, 'K'), new NoteBeat(startTime + gap * 18, 'K'),
					new NoteBeat(startTime + gap * 19, 'J'), new NoteBeat(startTime + gap * 20, 'J'),
					new NoteBeat(startTime + gap * 21, 'F'), new NoteBeat(startTime + gap * 22, 'F'),
					new NoteBeat(startTime + gap * 23, 'D'), new NoteBeat(startTime + gap * 25, 'K'),
					new NoteBeat(startTime + gap * 26, 'K'), new NoteBeat(startTime + gap * 27, 'F'),
					new NoteBeat(startTime + gap * 28, 'F'), new NoteBeat(startTime + gap * 29, 'D'),
					new NoteBeat(startTime + gap * 30, 'D'), new NoteBeat(startTime + gap * 31, 'S'),
					new NoteBeat(startTime + gap * 33, 'S'), new NoteBeat(startTime + gap * 34, 'S'),
					new NoteBeat(startTime + gap * 35, 'K'), new NoteBeat(startTime + gap * 36, 'K'),
					new NoteBeat(startTime + gap * 37, 'L'), new NoteBeat(startTime + gap * 38, 'L'),
					new NoteBeat(startTime + gap * 39, 'K'), new NoteBeat(startTime + gap * 41, 'J'),
					new NoteBeat(startTime + gap * 42, 'J'), new NoteBeat(startTime + gap * 43, 'F'),
					new NoteBeat(startTime + gap * 44, 'F'), new NoteBeat(startTime + gap * 45, 'D'),
					new NoteBeat(startTime + gap * 46, 'D'), new NoteBeat(startTime + gap * 47, 'S'),

			};
		}
		else if (titleName.equals("cottonCandy")) {
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
		else{
			int startTime = 530;
			int gap = 167;
			beats = new NoteBeat[] { 
					new NoteBeat(startTime + gap * 0 , 'D'), 
					new NoteBeat(startTime + gap * 2 , 'L'),
					new NoteBeat(startTime + gap * 4 , 'K'), 
					new NoteBeat(startTime + gap * 6 , 'J'), 
					new NoteBeat(startTime + gap * 8 , 'F'),  
					new NoteBeat(startTime + gap * 14, 'S'), 
					new NoteBeat(startTime + gap * 15, 'L'), 
					new NoteBeat(startTime + gap * 16, 'K'), 
					new NoteBeat(startTime + gap * 18, 'J'), 
					new NoteBeat(startTime + gap * 20 , 'K'), 
					new NoteBeat(startTime + gap * 22 , 'F'), 
					new NoteBeat(startTime + gap * 24 , 'L'),
					new NoteBeat(startTime + gap * 32 , 'J'),
					new NoteBeat(startTime + gap * 34 , 'K'),
					new NoteBeat(startTime + gap * 36 , 'L'), 
					new NoteBeat(startTime + gap * 38 , 'L'), 
					new NoteBeat(startTime + gap * 40 , 'K'),  
					new NoteBeat(startTime + gap * 48 , 'J'), 
					new NoteBeat(startTime + gap * 50 , 'L'), 
					new NoteBeat(startTime + gap * 52 , 'D'), 
					new NoteBeat(startTime + gap * 54 , 'L'), 
					new NoteBeat(startTime + gap * 56 , 'K'),  
					new NoteBeat(startTime + gap * 64 , 'J'), 
					new NoteBeat(startTime + gap * 66 , 'K'), 
					new NoteBeat(startTime + gap * 68 , 'S'), 
					new NoteBeat(startTime + gap * 70 , 'K'), 
					new NoteBeat(startTime + gap * 72 , 'J'), 
					new NoteBeat(startTime + gap * 80 , 'D'), 
					new NoteBeat(startTime + gap * 82 , 'L'), 
					new NoteBeat(startTime + gap * 84 , 'L'), 
					new NoteBeat(startTime + gap * 86 , 'L'), 
					new NoteBeat(startTime + gap * 88 , 'L'), 
					new NoteBeat(startTime + gap * 96 , 'L'), 
					new NoteBeat(startTime + gap * 98 , 'K'), 
					new NoteBeat(startTime + gap * 100 , 'J'), 
					new NoteBeat(startTime + gap * 102, 'F'), 
					new NoteBeat(startTime + gap * 104, 'D'), 
					new NoteBeat(startTime + gap * 106, 'S'), 
					new NoteBeat(startTime + gap * 108, 'S'), 
					new NoteBeat(startTime + gap * 110, 'S'), 
					new NoteBeat(startTime + gap * 112, 'F'), 
					new NoteBeat(startTime + gap * 114, 'F'), 
					new NoteBeat(startTime + gap * 116, 'F'), 
					new NoteBeat(startTime + gap * 118, 'S'), 
					new NoteBeat(startTime + gap * 120, 'D'), 
					new NoteBeat(startTime + gap * 124, 'F'), 
					new NoteBeat(startTime + gap * 128, 'K'), 
					new NoteBeat(startTime + gap * 130, 'L'), 
					new NoteBeat(startTime + gap * 132, 'J'), 
					new NoteBeat(startTime + gap * 136, 'F'), 
					new NoteBeat(startTime + gap * 138, 'J'), 
					new NoteBeat(startTime + gap * 140, 'F'), 
					new NoteBeat(startTime + gap * 144, 'D'), 
					new NoteBeat(startTime + gap * 146, 'K'), 
					new NoteBeat(startTime + gap * 148, 'D'), 
					new NoteBeat(startTime + gap * 151, 'K'), 
					new NoteBeat(startTime + gap * 152, 'L'), 
					new NoteBeat(startTime + gap * 160, 'K'), 
					new NoteBeat(startTime + gap * 162, 'J'), 
					new NoteBeat(startTime + gap * 164, 'S'), 
					new NoteBeat(startTime + gap * 167, 'D'), 
					new NoteBeat(startTime + gap * 168, 'S'), 
					new NoteBeat(startTime + gap * 170, 'S'), 
					new NoteBeat(startTime + gap * 172, 'S'), 
					new NoteBeat(startTime + gap * 174, 'F'), 
					new NoteBeat(startTime + gap * 175, 'F'), 
					new NoteBeat(startTime + gap * 176, 'F'), 
					new NoteBeat(startTime + gap * 178, 'S'), 
					new NoteBeat(startTime + gap * 180, 'K'), 
					new NoteBeat(startTime + gap * 182, 'F'), 
					new NoteBeat(startTime + gap * 184, 'K'), 
					new NoteBeat(startTime + gap * 188, 'L'), 
					new NoteBeat(startTime + gap * 192, 'L'), 
					new NoteBeat(startTime + gap * 194, 'K'), 
					new NoteBeat(startTime + gap * 196, 'L'), 
					new NoteBeat(startTime + gap * 200, 'S'), 
					new NoteBeat(startTime + gap * 202, 'D'), 
					new NoteBeat(startTime + gap * 204, 'F'), 
					new NoteBeat(startTime + gap * 208, 'D'), 
					new NoteBeat(startTime + gap * 210, 'K'), 
					new NoteBeat(startTime + gap * 212, 'J'), 
					new NoteBeat(startTime + gap * 215, 'K'), 
					new NoteBeat(startTime + gap * 216, 'L'), 
					new NoteBeat(startTime + gap * 224, 'D'), 
					new NoteBeat(startTime + gap * 226, 'F'), 
					new NoteBeat(startTime + gap * 228, 'S'), 
					new NoteBeat(startTime + gap * 231, 'J'), 
					new NoteBeat(startTime + gap * 232, 'F'), 
					new NoteBeat(startTime + gap * 234, 'D'), 
					new NoteBeat(startTime + gap * 236, 'L'), 
					new NoteBeat(startTime + gap * 238, 'J'), 
					new NoteBeat(startTime + gap * 239, 'S'), 
					new NoteBeat(startTime + gap * 240, 'K'), 
					new NoteBeat(startTime + gap * 242, 'D'), 
					new NoteBeat(startTime + gap * 244, 'L'), 
					new NoteBeat(startTime + gap * 246, 'F'), 
					new NoteBeat(startTime + gap * 248, 'S'), 
					new NoteBeat(startTime + gap * 252, 'J'), 
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
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void judge(char input) {

		if (count == beats.length - 1) { // 노트의 갯수만큼 이 메소드가 호출되었으면 게임 종료.
			System.out.println("이 것은 져지에서호출");
			gameEnd();
		}
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input == note.getNoteType()) {
				rank.plusScore(judgeEvent(note.judge())); // 점수 더하면서 judge판단하기
				++count; // judge메소드가 호출된 횟수 증가시켜줌
				break;
			}

		}

	}

	// 콤보 리셋
	public void resetCombo() {
		comboImg = null;
		combo = "";
		cb = 0;

	}

	// 콤보일때
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