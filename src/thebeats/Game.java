package thebeats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
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

	private Judge judge; // 판단

	private NoteBeat[] beats = null;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
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
				judge.plusScore("miss");
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
		g.drawString("S", 270, 615);
		g.drawString("D", 374, 615);
		g.drawString("F", 478, 615);
		g.drawString("J", 572, 615);
		g.drawString("K", 676, 615);
		g.drawString("L", 780, 615);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_routePressed.png")).getImage();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		System.out.println("끝");
		this.interrupt();
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
					new NoteBeat(startTime + gap * 22, "F"), new NoteBeat(startTime + gap * 24, "D"),
					new NoteBeat(startTime + gap * 26, "L"), new NoteBeat(startTime + gap * 28, "D"),
					new NoteBeat(startTime + gap * 30, "L"),
					new NoteBeat(startTime + gap * 32, "S"), new NoteBeat(startTime + gap * 34, "J"),
					new NoteBeat(startTime + gap * 36, "S"), new NoteBeat(startTime + gap * 38, "J"),
					new NoteBeat(startTime + gap * 40, "K"), new NoteBeat(startTime + gap * 42, "D"),
					new NoteBeat(startTime + gap * 44, "K"), new NoteBeat(startTime + gap * 46, "D"),
					new NoteBeat(startTime + gap * 48, "L"), new NoteBeat(startTime + gap * 50, "F"),
					new NoteBeat(startTime + gap * 52, "L"), new NoteBeat(startTime + gap * 54, "F"),
					new NoteBeat(startTime + gap * 56, "D"), new NoteBeat(startTime + gap * 58, "L"),
					new NoteBeat(startTime + gap * 60, "D"), new NoteBeat(startTime + gap * 62, "L"),
					new NoteBeat(startTime + gap * 64, "L"), new NoteBeat(startTime + gap * 70, "D"),
					new NoteBeat(startTime + gap * 75, "F"), new NoteBeat(startTime + gap * 80, "J"),
					new NoteBeat(startTime + gap * 85, "F"), new NoteBeat(startTime + gap * 90, "D"),
					new NoteBeat(startTime + gap * 95, "J"), new NoteBeat(startTime + gap * 100, "D"),
					new NoteBeat(startTime + gap * 105, "L"), new NoteBeat(startTime + gap * 110, "D"),
					new NoteBeat(startTime + gap * 115, "F"), new NoteBeat(startTime + gap * 120, "J"),
					new NoteBeat(startTime + gap * 125, "F"), new NoteBeat(startTime + gap * 130, "D"),
					new NoteBeat(startTime + gap * 135, "F"), new NoteBeat(startTime + gap * 140, "J"),
					new NoteBeat(startTime + gap * 145, "F"), new NoteBeat(startTime + gap * 150, "J"),
					new NoteBeat(startTime + gap * 155, "L"), new NoteBeat(startTime + gap * 160, "D"),
					new NoteBeat(startTime + gap * 165, "F"), new NoteBeat(startTime + gap * 170, "J"),
					new NoteBeat(startTime + gap * 175, "F"), new NoteBeat(startTime + gap * 180, "D"),
					new NoteBeat(startTime + gap * 185, "F"), new NoteBeat(startTime + gap * 190, "D"),
					new NoteBeat(startTime + gap * 195, "L"), new NoteBeat(startTime + gap * 200, "D"),
					new NoteBeat(startTime + gap * 205, "F"), new NoteBeat(startTime + gap * 210, "J"),
					new NoteBeat(startTime + gap * 215, "F"), new NoteBeat(startTime + gap * 220, "D"),
					new NoteBeat(startTime + gap * 225, "F"), new NoteBeat(startTime + gap * 230, "L"),
					new NoteBeat(startTime + gap * 235, "J"), new NoteBeat(startTime + gap * 240, "F"),
					new NoteBeat(startTime + gap * 245, "J"), new NoteBeat(startTime + gap * 250, "K"),
					new NoteBeat(startTime + gap * 255, "L"), new NoteBeat(startTime + gap * 260, "F"),
					new NoteBeat(startTime + gap * 265, "L"), new NoteBeat(startTime + gap * 270, "F"),
					new NoteBeat(startTime + gap * 275, "L"), new NoteBeat(startTime + gap * 280, "F"),
					new NoteBeat(startTime + gap * 285, "L"), new NoteBeat(startTime + gap * 290, "F"),
					new NoteBeat(startTime + gap * 295, "L"), new NoteBeat(startTime + gap * 300, "J"),
					new NoteBeat(startTime + gap * 305, "D"), new NoteBeat(startTime + gap * 310, "J"),
					new NoteBeat(startTime + gap * 315, "K"), new NoteBeat(startTime + gap * 320, "L"),
					new NoteBeat(startTime + gap * 325, "F"), new NoteBeat(startTime + gap * 330, "L"),
					new NoteBeat(startTime + gap * 335, "F"), new NoteBeat(startTime + gap * 340, "L"),
					new NoteBeat(startTime + gap * 345, "F"), new NoteBeat(startTime + gap * 350, "L"),
					new NoteBeat(startTime + gap * 355, "F"), new NoteBeat(startTime + gap * 360, "L"),
					new NoteBeat(startTime + gap * 365, "J"), new NoteBeat(startTime + gap * 370, "D"),
					new NoteBeat(startTime + gap * 375, "F"), new NoteBeat(startTime + gap * 380, "D"),
					new NoteBeat(startTime + gap * 385, "F"), new NoteBeat(startTime + gap * 390, "J"),
					new NoteBeat(startTime + gap * 395, "F"), new NoteBeat(startTime + gap * 400, "J"),
					new NoteBeat(startTime + gap * 405, "F"), new NoteBeat(startTime + gap * 410, "J"),
					new NoteBeat(startTime + gap * 415, "F"), new NoteBeat(startTime + gap * 420, "J"),
					new NoteBeat(startTime + gap * 425, "F"), new NoteBeat(startTime + gap * 430, "J"),
					new NoteBeat(startTime + gap * 435, "S"), new NoteBeat(startTime + gap * 440, "F"),
					new NoteBeat(startTime + gap * 445, "S"), new NoteBeat(startTime + gap * 450, "J"),
					new NoteBeat(startTime + gap * 455, "F"), new NoteBeat(startTime + gap * 460, "D"),
					new NoteBeat(startTime + gap * 465, "F"), new NoteBeat(startTime + gap * 470, "L"),
					new NoteBeat(startTime + gap * 475, "D"), new NoteBeat(startTime + gap * 480, "F"),
					new NoteBeat(startTime + gap * 485, "J"), new NoteBeat(startTime + gap * 490, "F"),
					new NoteBeat(startTime + gap * 495, "D"), new NoteBeat(startTime + gap * 500, "F"),
					new NoteBeat(startTime + gap * 505, "D"), new NoteBeat(startTime + gap * 510, "L"),
					new NoteBeat(startTime + gap * 515, "D"), new NoteBeat(startTime + gap * 520, "F"),
					new NoteBeat(startTime + gap * 525, "J"), new NoteBeat(startTime + gap * 530, "F"),
					new NoteBeat(startTime + gap * 535, "D"), new NoteBeat(startTime + gap * 540, "F"),
					new NoteBeat(startTime + gap * 545, "J"), new NoteBeat(startTime + gap * 550, "F"),
					new NoteBeat(startTime + gap * 555, "J"), new NoteBeat(startTime + gap * 560, "L"),
					new NoteBeat(startTime + gap * 565, "D"), new NoteBeat(startTime + gap * 570, "F"),
					new NoteBeat(startTime + gap * 575, "J"), new NoteBeat(startTime + gap * 580, "F"),
					new NoteBeat(startTime + gap * 585, "D"), new NoteBeat(startTime + gap * 590, "F"),
					new NoteBeat(startTime + gap * 595, "D"), new NoteBeat(startTime + gap * 600, "L"),
					new NoteBeat(startTime + gap * 605, "D"), new NoteBeat(startTime + gap * 610, "F"),
					new NoteBeat(startTime + gap * 615, "J"), new NoteBeat(startTime + gap * 620, "F"),
					new NoteBeat(startTime + gap * 625, "D"), new NoteBeat(startTime + gap * 630, "F"),
					new NoteBeat(startTime + gap * 635, "L"), new NoteBeat(startTime + gap * 640, "J"),
					new NoteBeat(startTime + gap * 645, "F"), new NoteBeat(startTime + gap * 650, "J"),
					new NoteBeat(startTime + gap * 655, "K"), new NoteBeat(startTime + gap * 660, "L"),
					new NoteBeat(startTime + gap * 665, "F"), new NoteBeat(startTime + gap * 670, "L"),
					new NoteBeat(startTime + gap * 675, "F"), new NoteBeat(startTime + gap * 680, "L"),
					new NoteBeat(startTime + gap * 685, "F"), new NoteBeat(startTime + gap * 690, "L"),
					new NoteBeat(startTime + gap * 695, "F"), new NoteBeat(startTime + gap * 700, "L"),
					new NoteBeat(startTime + gap * 705, "J"), new NoteBeat(startTime + gap * 710, "D"),
					new NoteBeat(startTime + gap * 715, "J"), new NoteBeat(startTime + gap * 720, "K"),
					new NoteBeat(startTime + gap * 725, "L"), new NoteBeat(startTime + gap * 730, "F"),
					new NoteBeat(startTime + gap * 735, "L"), new NoteBeat(startTime + gap * 740, "F"),
					new NoteBeat(startTime + gap * 745, "L"), new NoteBeat(startTime + gap * 750, "F"),
					new NoteBeat(startTime + gap * 755, "L"), new NoteBeat(startTime + gap * 760, "F"),
					new NoteBeat(startTime + gap * 765, "L"), new NoteBeat(startTime + gap * 770, "J"),
					new NoteBeat(startTime + gap * 775, "D"), new NoteBeat(startTime + gap * 780, "F"),
					new NoteBeat(startTime + gap * 785, "D"), new NoteBeat(startTime + gap * 790, "F"),
					new NoteBeat(startTime + gap * 795, "J"), new NoteBeat(startTime + gap * 800, "J"),
					new NoteBeat(startTime + gap * 805, "F"), new NoteBeat(startTime + gap * 810, "J"),
					new NoteBeat(startTime + gap * 815, "F"), new NoteBeat(startTime + gap * 820, "J"),
					new NoteBeat(startTime + gap * 825, "S"), new NoteBeat(startTime + gap * 830, "J"),
					new NoteBeat(startTime + gap * 835, "F"), new NoteBeat(startTime + gap * 840, "D"),
					new NoteBeat(startTime + gap * 845, "S"), new NoteBeat(startTime + gap * 850, "D"),
					new NoteBeat(startTime + gap * 855, "F"), new NoteBeat(startTime + gap * 860, "D"),
					new NoteBeat(startTime + gap * 865, "L"), new NoteBeat(startTime + gap * 870, "J"),
					new NoteBeat(startTime + gap * 875, "F"), new NoteBeat(startTime + gap * 880, "J"),
					new NoteBeat(startTime + gap * 885, "K"), new NoteBeat(startTime + gap * 890, "J"),
					new NoteBeat(startTime + gap * 895, "F"), new NoteBeat(startTime + gap * 900, "J"),
					new NoteBeat(startTime + gap * 905, "F"), new NoteBeat(startTime + gap * 910, "J"),
					new NoteBeat(startTime + gap * 915, "F"), new NoteBeat(startTime + gap * 920, "D"),
					new NoteBeat(startTime + gap * 925, "J"), new NoteBeat(startTime + gap * 930, "F"),
					new NoteBeat(startTime + gap * 935, "J"), new NoteBeat(startTime + gap * 940, "F"),
					new NoteBeat(startTime + gap * 945, "D"), new NoteBeat(startTime + gap * 950, "J"),
					new NoteBeat(startTime + gap * 955, "F"), new NoteBeat(startTime + gap * 960, "J"),
					new NoteBeat(startTime + gap * 965, "F"), new NoteBeat(startTime + gap * 970, "D"),
					new NoteBeat(startTime + gap * 975, "L"), new NoteBeat(startTime + gap * 980, "F"),
					new NoteBeat(startTime + gap * 985, "L"), new NoteBeat(startTime + gap * 990, "F"),
					new NoteBeat(startTime + gap * 995, "L"), new NoteBeat(startTime + gap * 1000, "D"),
					new NoteBeat(startTime + gap * 1005, "F"), new NoteBeat(startTime + gap * 1010, "L"),
					new NoteBeat(startTime + gap * 1015, "F"), new NoteBeat(startTime + gap * 1020, "D"),
					new NoteBeat(startTime + gap * 1025, "L"), new NoteBeat(startTime + gap * 1030, "F"),
					new NoteBeat(startTime + gap * 1035, "L"), new NoteBeat(startTime + gap * 1040, "F"),
					new NoteBeat(startTime + gap * 1045, "L"), new NoteBeat(startTime + gap * 1050, "D"),
					new NoteBeat(startTime + gap * 1055, "F"), new NoteBeat(startTime + gap * 1060, "L"),
					new NoteBeat(startTime + gap * 1065, "F"), new NoteBeat(startTime + gap * 1070, "D"),
					new NoteBeat(startTime + gap * 1075, "L"), new NoteBeat(startTime + gap * 1080, "F"),
					new NoteBeat(startTime + gap * 1085, "L"), new NoteBeat(startTime + gap * 1090, "F"),
					new NoteBeat(startTime + gap * 1095, "L"), new NoteBeat(startTime + gap * 1100, "D"),
					new NoteBeat(startTime + gap * 1105, "F"), new NoteBeat(startTime + gap * 1110, "L"),
					new NoteBeat(startTime + gap * 1115, "F"), new NoteBeat(startTime + gap * 1120, "D"),
					new NoteBeat(startTime + gap * 1125, "L"), new NoteBeat(startTime + gap * 1130, "F"),
					new NoteBeat(startTime + gap * 1135, "L"), new NoteBeat(startTime + gap * 1140, "F"),
					new NoteBeat(startTime + gap * 1145, "L"), new NoteBeat(startTime + gap * 1150, "D"),
					new NoteBeat(startTime + gap * 1155, "F"), new NoteBeat(startTime + gap * 1160, "L"),
					new NoteBeat(startTime + gap * 1165, "F"), new NoteBeat(startTime + gap * 1170, "D"),
					new NoteBeat(startTime + gap * 1175, "L"), new NoteBeat(startTime + gap * 1180, "F"),
					new NoteBeat(startTime + gap * 1185, "L"), new NoteBeat(startTime + gap * 1190, "F"),
					new NoteBeat(startTime + gap * 1195, "L"), new NoteBeat(startTime + gap * 1200, "D"),
					new NoteBeat(startTime + gap * 1205, "F"), new NoteBeat(startTime + gap * 1210, "L"),
					new NoteBeat(startTime + gap * 1215, "F"), new NoteBeat(startTime + gap * 1220, "D"), };
		}
		int i = 0;
		gameMusic.start(); // 음악 시작
		judge = new Judge(beats.length);

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
		System.out.println("**끝" + judge.calculateGrade());

	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				String s = "";
				judgeEvent(s = note.judge());
				judge.plusScore(s); // 점수 더하기
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
