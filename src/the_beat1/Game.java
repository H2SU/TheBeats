package the_beat1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); 
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage(); 
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage(); 
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image flareImage ; 
	private Image judgeImage ;
	
	private String titleName;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String musicTitle) {
		this.titleName=titleName;
		this.musicTitle=musicTitle;
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
		
	
		for(int i=0; i<noteList.size(); i++) {
			Note note = noteList.get(i);
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}else {
			note.screenDraw(g);
			}
		}
		
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial",Font.BOLD,30));
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
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	
	
	@Override
	public void run() {
		dropNotes();
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes() {
		NoteBeat [] beats= null;
		if(titleName.equals("elise")){
			int startTime = 1000 - Main.REACH_TIME * 1000 ;
			int gap = 125;
			beats = new NoteBeat[] {
					new NoteBeat(startTime, "S"),
					new NoteBeat(startTime+gap*2,"D" ),
					new NoteBeat(startTime+gap*4,"D" ),
					new NoteBeat(startTime+gap*6,"S" ),
					new NoteBeat(startTime+gap*8,"D" ),
					new NoteBeat(startTime+gap*10,"J" ),
					new NoteBeat(startTime+gap*12,"K" ),
					new NoteBeat(startTime+gap*14,"L" ),
					new NoteBeat(startTime+gap*16,"K" ),
					new NoteBeat(startTime+gap*18,"J" ),
					new NoteBeat(startTime+gap*20,"D" ),
					new NoteBeat(startTime+gap*22,"F" ),
					new NoteBeat(startTime+gap*24,"F" ),
					new NoteBeat(startTime+gap*26,"S" ),
					new NoteBeat(startTime+gap*28,"D" ),
					new NoteBeat(startTime+gap*30,"J" ),
					new NoteBeat(startTime+gap*32,"K" ),
					new NoteBeat(startTime+gap*34,"L" ),
					new NoteBeat(startTime+gap*36,"S" ),
					new NoteBeat(startTime+gap*38,"D" ),
					new NoteBeat(startTime+gap*40,"J" ),
					new NoteBeat(startTime+gap*44,"S" ),
					new NoteBeat(startTime+gap*48,"D" ),
					new NoteBeat(startTime+gap*50,"J" ),
					new NoteBeat(startTime+gap*52,"K" ),
					new NoteBeat(startTime+gap*54,"L" ),
					new NoteBeat(startTime+gap*58,"S" ),
					new NoteBeat(startTime+gap*62,"D" ),
					new NoteBeat(startTime+gap*64,"J" ),
					new NoteBeat(startTime+gap*66,"J" ),
					new NoteBeat(startTime+gap*68,"K" ),
					new NoteBeat(startTime+gap*70,"L" ),
					new NoteBeat(startTime+gap*72,"S" ),
					new NoteBeat(startTime+gap*75,"D" ),
					new NoteBeat(startTime+gap*79,"J" ),
					new NoteBeat(startTime+gap*81,"S" ),
					new NoteBeat(startTime+gap*85,"F" ),
					new NoteBeat(startTime+gap*88,"F" ),
					new NoteBeat(startTime+gap*92,"S" ),
					new NoteBeat(startTime+gap*96,"D" ),
					new NoteBeat(startTime+gap*100,"L" ),
					new NoteBeat(startTime+gap*104,"S" ),
					new NoteBeat(startTime+gap*107,"D" ),
					new NoteBeat(startTime+gap*110,"J" ),
					new NoteBeat(startTime+gap*112,"S" ),
					new NoteBeat(startTime+gap*117,"D" ),
					new NoteBeat(startTime+gap*119,"J" ),
					new NoteBeat(startTime+gap*120,"K" ),
					new NoteBeat(startTime+gap*124,"L" ),
					new NoteBeat(startTime+gap*128,"S" ),
					new NoteBeat(startTime+gap*132,"D" ),
			};
		}
		else if(titleName.equals("summer")){
			int startTime = 1000 - Main.REACH_TIME * 1000 ;
			int gap = 125;
			beats = new NoteBeat[] {
					new NoteBeat(startTime, "S"),
					new NoteBeat(startTime+gap*2,"D" ),
					new NoteBeat(startTime+gap*4,"D" ),
					new NoteBeat(startTime+gap*6,"S" ),
					new NoteBeat(startTime+gap*8,"D" ),
					new NoteBeat(startTime+gap*10,"J" ),
					new NoteBeat(startTime+gap*12,"K" ),
					new NoteBeat(startTime+gap*14,"L" ),
					new NoteBeat(startTime+gap*16,"K" ),
					new NoteBeat(startTime+gap*18,"J" ),
					new NoteBeat(startTime+gap*20,"D" ),
					new NoteBeat(startTime+gap*22,"F" ),
					new NoteBeat(startTime+gap*24,"F" ),
					new NoteBeat(startTime+gap*26,"S" ),
					new NoteBeat(startTime+gap*28,"D" ),
					new NoteBeat(startTime+gap*30,"J" ),
					new NoteBeat(startTime+gap*32,"K" ),
					new NoteBeat(startTime+gap*34,"L" ),
					new NoteBeat(startTime+gap*36,"S" ),
					new NoteBeat(startTime+gap*38,"D" ),
					new NoteBeat(startTime+gap*40,"J" ),
					new NoteBeat(startTime+gap*44,"S" ),
					new NoteBeat(startTime+gap*48,"D" ),
					new NoteBeat(startTime+gap*50,"J" ),
					new NoteBeat(startTime+gap*52,"K" ),
					new NoteBeat(startTime+gap*54,"L" ),
					new NoteBeat(startTime+gap*58,"S" ),
					new NoteBeat(startTime+gap*62,"D" ),
					new NoteBeat(startTime+gap*64,"J" ),
					new NoteBeat(startTime+gap*66,"J" ),
					new NoteBeat(startTime+gap*68,"K" ),
					new NoteBeat(startTime+gap*70,"L" ),
					new NoteBeat(startTime+gap*72,"S" ),
					new NoteBeat(startTime+gap*75,"D" ),
					new NoteBeat(startTime+gap*79,"J" ),
					new NoteBeat(startTime+gap*81,"S" ),
					new NoteBeat(startTime+gap*85,"F" ),
					new NoteBeat(startTime+gap*88,"F" ),
					new NoteBeat(startTime+gap*92,"S" ),
					new NoteBeat(startTime+gap*96,"D" ),
					new NoteBeat(startTime+gap*100,"L" ),
					new NoteBeat(startTime+gap*104,"S" ),
					new NoteBeat(startTime+gap*107,"D" ),
					new NoteBeat(startTime+gap*110,"J" ),
					new NoteBeat(startTime+gap*112,"S" ),
					new NoteBeat(startTime+gap*117,"D" ),
					new NoteBeat(startTime+gap*119,"J" ),
					new NoteBeat(startTime+gap*120,"K" ),
					new NoteBeat(startTime+gap*124,"L" ),
					new NoteBeat(startTime+gap*128,"S" ),
					new NoteBeat(startTime+gap*132,"D" ),
			};
		}
		else if(titleName.equals("canon")){
			int startTime = 1000 - Main.REACH_TIME * 1000 ;
			int gap = 125;
			beats = new NoteBeat[] {
					new NoteBeat(startTime, "S"),
					new NoteBeat(startTime+gap*2,"D" ),
					new NoteBeat(startTime+gap*4,"D" ),
					new NoteBeat(startTime+gap*6,"S" ),
					new NoteBeat(startTime+gap*8,"D" ),
					new NoteBeat(startTime+gap*10,"J" ),
					new NoteBeat(startTime+gap*12,"K" ),
					new NoteBeat(startTime+gap*14,"L" ),
					new NoteBeat(startTime+gap*16,"K" ),
					new NoteBeat(startTime+gap*18,"J" ),
					new NoteBeat(startTime+gap*20,"D" ),
					new NoteBeat(startTime+gap*22,"F" ),
					new NoteBeat(startTime+gap*24,"F" ),
					new NoteBeat(startTime+gap*26,"S" ),
					new NoteBeat(startTime+gap*28,"D" ),
					new NoteBeat(startTime+gap*30,"J" ),
					new NoteBeat(startTime+gap*32,"K" ),
					new NoteBeat(startTime+gap*34,"L" ),
					new NoteBeat(startTime+gap*36,"S" ),
					new NoteBeat(startTime+gap*38,"D" ),
					new NoteBeat(startTime+gap*40,"J" ),
					new NoteBeat(startTime+gap*44,"S" ),
					new NoteBeat(startTime+gap*48,"D" ),
					new NoteBeat(startTime+gap*50,"J" ),
					new NoteBeat(startTime+gap*52,"K" ),
					new NoteBeat(startTime+gap*54,"L" ),
					new NoteBeat(startTime+gap*58,"S" ),
					new NoteBeat(startTime+gap*62,"D" ),
					new NoteBeat(startTime+gap*64,"J" ),
					new NoteBeat(startTime+gap*66,"J" ),
					new NoteBeat(startTime+gap*68,"K" ),
					new NoteBeat(startTime+gap*70,"L" ),
					new NoteBeat(startTime+gap*72,"S" ),
					new NoteBeat(startTime+gap*75,"D" ),
					new NoteBeat(startTime+gap*79,"J" ),
					new NoteBeat(startTime+gap*81,"S" ),
					new NoteBeat(startTime+gap*85,"F" ),
					new NoteBeat(startTime+gap*88,"F" ),
					new NoteBeat(startTime+gap*92,"S" ),
					new NoteBeat(startTime+gap*96,"D" ),
					new NoteBeat(startTime+gap*100,"L" ),
					new NoteBeat(startTime+gap*104,"S" ),
					new NoteBeat(startTime+gap*107,"D" ),
					new NoteBeat(startTime+gap*110,"J" ),
					new NoteBeat(startTime+gap*112,"S" ),
					new NoteBeat(startTime+gap*117,"D" ),
					new NoteBeat(startTime+gap*119,"J" ),
					new NoteBeat(startTime+gap*120,"K" ),
					new NoteBeat(startTime+gap*124,"L" ),
					new NoteBeat(startTime+gap*128,"S" ),
					new NoteBeat(startTime+gap*132,"D" ),
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
		public void judge (String input) { //큐 처럼 구현 
			for (int i =0; i< noteList.size(); i++) {
				Note note = noteList.get(i);
				if(input.equals(note.getNoteType())) {
					judgeEvent(note.judge());
					break;
				}
			}
		}
		public void judgeEvent(String judge) {
			//if(!judge.equals("None")) {
			//	flareImage = new ImageIcon(Main.class.getResource("../images/flare.png")).getImage(); 
			//}
			if(judge.equals("Miss")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage(); 
			}
			else if(judge.equals("Bad")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/bad.png")).getImage(); 
			}
			else if(judge.equals("Good")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage(); 
			}
			else if(judge.equals("Perfact")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/perfact.png")).getImage(); 
			}
			
		}
	}

