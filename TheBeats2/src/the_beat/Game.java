package the_beat;

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
	
	Judge judge = new Judge();
	
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
					new NoteBeat(startTime+gap*2, "K"),
                    new NoteBeat(startTime+gap*4, "S"),
                    new NoteBeat(startTime+gap*6, "J"),
                    new NoteBeat(startTime+gap*8, "S"),
                    new NoteBeat(startTime+gap*10, "F"),
                    new NoteBeat(startTime+gap*12, "J"),
                    new NoteBeat(startTime+gap*14, "S"),
                    new NoteBeat(startTime+gap*16, "K"),
                    new NoteBeat(startTime+gap*18, "F"),
                    new NoteBeat(startTime+gap*20, "L"),
                    new NoteBeat(startTime+gap*22, "F"),
                    new NoteBeat(startTime+gap*24, "S"),
                    new NoteBeat(startTime+gap*26, "L"),
                    new NoteBeat(startTime+gap*28, "D"),
                    new NoteBeat(startTime+gap*30, "F"),
                    
                    new NoteBeat(startTime+gap*32, "D"),
                    new NoteBeat(startTime+gap*34, "J"),
                    new NoteBeat(startTime+gap*36, "S"),
                    new NoteBeat(startTime+gap*38, "J"),
                    new NoteBeat(startTime+gap*40, "K"),
                    new NoteBeat(startTime+gap*42, "D"),
                    new NoteBeat(startTime+gap*44, "L"),
                    new NoteBeat(startTime+gap*46, "S"),
                    new NoteBeat(startTime+gap*48, "F"),
                    new NoteBeat(startTime+gap*50, "K"),
                    new NoteBeat(startTime+gap*52, "L"),
                    new NoteBeat(startTime+gap*54, "F"),
                    new NoteBeat(startTime+gap*56, "D"),
                    new NoteBeat(startTime+gap*58, "L"),
                    new NoteBeat(startTime+gap*60, "D"),
                    new NoteBeat(startTime+gap*62, "J"),
                 
                    new NoteBeat(startTime+gap*64, "L"),
                    new NoteBeat(startTime+gap*70, "D"),
                    new NoteBeat(startTime+gap*75, "F"),
                    new NoteBeat(startTime+gap*80, "J"),
                    new NoteBeat(startTime+gap*85, "F"),
                    new NoteBeat(startTime+gap*90, "D"),
                    new NoteBeat(startTime+gap*95, "J"),
                    new NoteBeat(startTime+gap*100, "D"),
                    new NoteBeat(startTime+gap*105, "L"),
                    new NoteBeat(startTime+gap*110, "D"),
                    new NoteBeat(startTime+gap*115, "F"),
                    new NoteBeat(startTime+gap*120, "J"),
                    new NoteBeat(startTime+gap*125, "F"),
                    new NoteBeat(startTime+gap*130, "D"),
                    new NoteBeat(startTime+gap*135, "F"),
                    new NoteBeat(startTime+gap*140, "J"),
                    new NoteBeat(startTime+gap*145, "F"),
                    new NoteBeat(startTime+gap*150, "J"),                     
                    new NoteBeat(startTime+gap*155, "L"),
                    new NoteBeat(startTime+gap*160, "D"),
                    new NoteBeat(startTime+gap*165, "F"),
                    new NoteBeat(startTime+gap*170, "J"),
                    new NoteBeat(startTime+gap*175, "F"),
                    new NoteBeat(startTime+gap*180, "D"),
                    new NoteBeat(startTime+gap*185, "F"),
                    new NoteBeat(startTime+gap*190, "D"),
                    new NoteBeat(startTime+gap*195, "L"),
                    new NoteBeat(startTime+gap*200, "D"),
                    new NoteBeat(startTime+gap*205, "F"),
                    new NoteBeat(startTime+gap*210, "J"),
                    new NoteBeat(startTime+gap*215, "F"),
                    new NoteBeat(startTime+gap*220, "D"),
                    new NoteBeat(startTime+gap*225, "F"),
                    new NoteBeat(startTime+gap*230, "L"),
                    new NoteBeat(startTime+gap*235, "J"),
                    new NoteBeat(startTime+gap*240, "F"),
                    new NoteBeat(startTime+gap*245, "J"),
                    new NoteBeat(startTime+gap*250, "K"),
                    new NoteBeat(startTime+gap*255, "L"),
                    new NoteBeat(startTime+gap*260, "F"),
                    new NoteBeat(startTime+gap*265, "L"),
                    new NoteBeat(startTime+gap*270, "F"),
                    new NoteBeat(startTime+gap*275, "L"),
                    new NoteBeat(startTime+gap*280, "F"),
                    new NoteBeat(startTime+gap*285, "L"),
                    new NoteBeat(startTime+gap*290, "F"),
                    new NoteBeat(startTime+gap*295, "L"),
                    new NoteBeat(startTime+gap*300, "J"),
                    new NoteBeat(startTime+gap*305, "D"),
                    new NoteBeat(startTime+gap*310, "J"),
                    new NoteBeat(startTime+gap*315, "K"),
                    new NoteBeat(startTime+gap*320, "L"),
                    new NoteBeat(startTime+gap*325, "F"),
                    new NoteBeat(startTime+gap*330, "L"),
                    new NoteBeat(startTime+gap*335, "F"),
                    new NoteBeat(startTime+gap*340, "L"),
                    new NoteBeat(startTime+gap*345, "F"),
                    new NoteBeat(startTime+gap*350, "L"),
                    new NoteBeat(startTime+gap*355, "F"),
                    new NoteBeat(startTime+gap*360, "L"),
                    new NoteBeat(startTime+gap*365, "J"),
                    new NoteBeat(startTime+gap*370, "D"),
                    new NoteBeat(startTime+gap*375, "F"),
                    new NoteBeat(startTime+gap*380, "D"),
                    new NoteBeat(startTime+gap*385, "F"),
                    new NoteBeat(startTime+gap*390, "J"),
                    new NoteBeat(startTime+gap*395, "F"),
                    new NoteBeat(startTime+gap*400, "J"),
                    new NoteBeat(startTime+gap*405, "F"),
                    new NoteBeat(startTime+gap*410, "J"),
                    new NoteBeat(startTime+gap*415, "F"),
                    new NoteBeat(startTime+gap*420, "J"),
                    new NoteBeat(startTime+gap*425, "F"),
                    new NoteBeat(startTime+gap*430, "J"),
                    new NoteBeat(startTime+gap*435, "S"),
                    new NoteBeat(startTime+gap*440, "F"),
                    new NoteBeat(startTime+gap*445, "S"),
                    new NoteBeat(startTime+gap*450, "J"),
                    new NoteBeat(startTime+gap*455, "F"),
                    new NoteBeat(startTime+gap*460, "D"),
                    new NoteBeat(startTime+gap*465, "F"),                     
                    new NoteBeat(startTime+gap*470, "L"),
                    new NoteBeat(startTime+gap*475, "D"),
                    new NoteBeat(startTime+gap*480, "F"),
                    new NoteBeat(startTime+gap*485, "J"),
                    new NoteBeat(startTime+gap*490, "F"),
                    new NoteBeat(startTime+gap*495, "D"),
                    new NoteBeat(startTime+gap*500, "F"),
                    new NoteBeat(startTime+gap*505, "D"),
                    new NoteBeat(startTime+gap*510, "L"),
                    new NoteBeat(startTime+gap*515, "D"),
                    new NoteBeat(startTime+gap*520, "F"),
                    new NoteBeat(startTime+gap*525, "J"),
                    new NoteBeat(startTime+gap*530, "F"),
                    new NoteBeat(startTime+gap*535, "D"),
                    new NoteBeat(startTime+gap*540, "F"),
                    new NoteBeat(startTime+gap*545, "J"),
                    new NoteBeat(startTime+gap*550, "F"),
                    new NoteBeat(startTime+gap*555, "J"),                     
                    new NoteBeat(startTime+gap*560, "L"),
                    new NoteBeat(startTime+gap*565, "D"),
                    new NoteBeat(startTime+gap*570, "F"),
                    new NoteBeat(startTime+gap*575, "J"),
                    new NoteBeat(startTime+gap*580, "F"),
                    new NoteBeat(startTime+gap*585, "D"),
                    new NoteBeat(startTime+gap*590, "F"),
                    new NoteBeat(startTime+gap*595, "D"),
                    new NoteBeat(startTime+gap*600, "L"),
                    new NoteBeat(startTime+gap*605, "D"),
                    new NoteBeat(startTime+gap*610, "F"),
                    new NoteBeat(startTime+gap*615, "J"),
                    new NoteBeat(startTime+gap*620, "F"),
                    new NoteBeat(startTime+gap*625, "D"),
                    new NoteBeat(startTime+gap*630, "F"),
                    new NoteBeat(startTime+gap*635, "L"),
                    new NoteBeat(startTime+gap*640, "J"),
                    new NoteBeat(startTime+gap*645, "F"), 
                    new NoteBeat(startTime+gap*650, "J"),
                    new NoteBeat(startTime+gap*655, "K"),
                    new NoteBeat(startTime+gap*660, "L"),
                    new NoteBeat(startTime+gap*665, "F"),
                    new NoteBeat(startTime+gap*670, "L"),
                    new NoteBeat(startTime+gap*675, "F"),
                    new NoteBeat(startTime+gap*680, "L"),
                    new NoteBeat(startTime+gap*685, "F"),
                    new NoteBeat(startTime+gap*690, "L"),
                    new NoteBeat(startTime+gap*695, "F"),
                    new NoteBeat(startTime+gap*700, "L"),
                    new NoteBeat(startTime+gap*705, "J"),
                    new NoteBeat(startTime+gap*710, "D"),
                    new NoteBeat(startTime+gap*715, "J"),
                    new NoteBeat(startTime+gap*720, "K"),
                    new NoteBeat(startTime+gap*725, "L"),
                    new NoteBeat(startTime+gap*730, "F"),
                    new NoteBeat(startTime+gap*735, "L"),
                    new NoteBeat(startTime+gap*740, "F"),
                    new NoteBeat(startTime+gap*745, "L"),
                    new NoteBeat(startTime+gap*750, "F"),
                    new NoteBeat(startTime+gap*755, "L"),
                    new NoteBeat(startTime+gap*760, "F"),
                    new NoteBeat(startTime+gap*765, "L"),
                    new NoteBeat(startTime+gap*770, "J"),
                    new NoteBeat(startTime+gap*775, "D"),
                    new NoteBeat(startTime+gap*780, "F"),
                    new NoteBeat(startTime+gap*785, "D"),
                    new NoteBeat(startTime+gap*790, "F"),
                    new NoteBeat(startTime+gap*795, "J"),
                    new NoteBeat(startTime+gap*800, "J"),
                    new NoteBeat(startTime+gap*805, "F"),
                    new NoteBeat(startTime+gap*810, "J"),
                    new NoteBeat(startTime+gap*815, "F"),
                    new NoteBeat(startTime+gap*820, "J"),
                    new NoteBeat(startTime+gap*825, "S"),
                    new NoteBeat(startTime+gap*830, "J"),
                    new NoteBeat(startTime+gap*835, "F"),
                    new NoteBeat(startTime+gap*840, "D"),
                    new NoteBeat(startTime+gap*845, "S"),
                    new NoteBeat(startTime+gap*850, "D"),
                    new NoteBeat(startTime+gap*855, "F"), 
                    new NoteBeat(startTime+gap*860, "D"),
                    new NoteBeat(startTime+gap*865, "L"),
                    new NoteBeat(startTime+gap*870, "J"),
                    new NoteBeat(startTime+gap*875, "F"),
                    new NoteBeat(startTime+gap*880, "J"),
                    new NoteBeat(startTime+gap*885, "K"),
                    new NoteBeat(startTime+gap*890, "J"),
                    new NoteBeat(startTime+gap*895, "F"),                     
                    new NoteBeat(startTime+gap*900, "J"),
                    new NoteBeat(startTime+gap*905, "F"),
                    new NoteBeat(startTime+gap*910, "J"),
                    new NoteBeat(startTime+gap*915, "F"),
                    new NoteBeat(startTime+gap*920, "D"),
                    new NoteBeat(startTime+gap*925, "J"),
                    new NoteBeat(startTime+gap*930, "F"),
                    new NoteBeat(startTime+gap*935, "J"),
                    new NoteBeat(startTime+gap*940, "F"),
                    new NoteBeat(startTime+gap*945, "D"),
                    new NoteBeat(startTime+gap*950, "J"),
                    new NoteBeat(startTime+gap*955, "F"),
                    new NoteBeat(startTime+gap*960, "J"),
                    new NoteBeat(startTime+gap*965, "F"),
                    new NoteBeat(startTime+gap*970, "D"),
                    new NoteBeat(startTime+gap*975, "L"),
                    new NoteBeat(startTime+gap*980, "F"),
                    new NoteBeat(startTime+gap*985, "L"),
                    new NoteBeat(startTime+gap*990, "F"),
                    new NoteBeat(startTime+gap*995, "L"),
                    new NoteBeat(startTime+gap*1000, "D"),
                    new NoteBeat(startTime+gap*1005, "F"),
                    new NoteBeat(startTime+gap*1010, "L"),
                    new NoteBeat(startTime+gap*1015, "F"),
                    new NoteBeat(startTime+gap*1020, "D"),
                    new NoteBeat(startTime+gap*1025, "L"),
                    new NoteBeat(startTime+gap*1030, "F"),
                    new NoteBeat(startTime+gap*1035, "L"),
                    new NoteBeat(startTime+gap*1040, "F"),
                    new NoteBeat(startTime+gap*1045, "L"),
                    new NoteBeat(startTime+gap*1050, "D"),
                    new NoteBeat(startTime+gap*1055, "F"),
                    new NoteBeat(startTime+gap*1060, "L"),
                    new NoteBeat(startTime+gap*1065, "F"),
                    new NoteBeat(startTime+gap*1070, "D"),
                    new NoteBeat(startTime+gap*1075, "L"),
                    new NoteBeat(startTime+gap*1080, "F"),
                    new NoteBeat(startTime+gap*1085, "L"),
                    new NoteBeat(startTime+gap*1090, "F"),
                    new NoteBeat(startTime+gap*1095, "L"),
                    new NoteBeat(startTime+gap*1100, "D"),
                    new NoteBeat(startTime+gap*1101, "F"),
                    new NoteBeat(startTime+gap*1103, "L"),
                    new NoteBeat(startTime+gap*1105, "F"),
                    new NoteBeat(startTime+gap*1107, "D"),
                    new NoteBeat(startTime+gap*1108, "L"),
                    new NoteBeat(startTime+gap*1109, "F"),
                    new NoteBeat(startTime+gap*1110, "L"),
                    new NoteBeat(startTime+gap*1111, "F"),
                    new NoteBeat(startTime+gap*1112, "L"),
                    new NoteBeat(startTime+gap*1113, "D"),
                    new NoteBeat(startTime+gap*1114, "F"),
                    new NoteBeat(startTime+gap*1116, "L"),
                    new NoteBeat(startTime+gap*1118, "F"),
                    new NoteBeat(startTime+gap*1120, "D"),
                    new NoteBeat(startTime+gap*1122, "L"),
                    new NoteBeat(startTime+gap*1124, "F"), 
                    new NoteBeat(startTime+gap*1126, "L"),
                    new NoteBeat(startTime+gap*1128, "F"),
                    new NoteBeat(startTime+gap*1130, "L"),
			};
		}
		else if(titleName.equals("summer")){
			int startTime = 1000 - Main.REACH_TIME * 1000 ;
			int gap = 125;
			beats = new NoteBeat[] {
			        new NoteBeat(startTime+gap*4, "S"),
                    new NoteBeat(startTime+gap*8, "K"),
                    new NoteBeat(startTime+gap*13, "D"),
                    new NoteBeat(startTime+gap*16, "K"),
                    new NoteBeat(startTime+gap*19, "D"),
                    new NoteBeat(startTime+gap*22, "L"),
                    new NoteBeat(startTime+gap*24, "D"),
                    new NoteBeat(startTime+gap*26, "L"),
                    new NoteBeat(startTime+gap*28, "D"),
                    new NoteBeat(startTime+gap*30, "L"),
                    
                    new NoteBeat(startTime+gap*32, "S"),
                    new NoteBeat(startTime+gap*34, "J"),
                    new NoteBeat(startTime+gap*36, "S"),
                    new NoteBeat(startTime+gap*38, "J"),
                    new NoteBeat(startTime+gap*40, "K"),
                    new NoteBeat(startTime+gap*42, "D"),
                    new NoteBeat(startTime+gap*44, "K"),
                    new NoteBeat(startTime+gap*46, "D"),
                    new NoteBeat(startTime+gap*48, "L"),
                    new NoteBeat(startTime+gap*50, "F"),
                    new NoteBeat(startTime+gap*52, "L"),
                    new NoteBeat(startTime+gap*54, "F"),
                    new NoteBeat(startTime+gap*56, "D"),
                    new NoteBeat(startTime+gap*58, "L"),
                    new NoteBeat(startTime+gap*60, "D"),
                    new NoteBeat(startTime+gap*62, "L"),
                 
                    new NoteBeat(startTime+gap*64, "L"),
                    new NoteBeat(startTime+gap*70, "D"),
                    new NoteBeat(startTime+gap*75, "F"),
                    new NoteBeat(startTime+gap*80, "J"),
                    new NoteBeat(startTime+gap*85, "F"),
                    new NoteBeat(startTime+gap*90, "D"),
                    new NoteBeat(startTime+gap*95, "J"),
                    new NoteBeat(startTime+gap*100, "D"),
                    new NoteBeat(startTime+gap*105, "L"),
                    new NoteBeat(startTime+gap*110, "D"),
                    new NoteBeat(startTime+gap*115, "F"),
                    new NoteBeat(startTime+gap*120, "J"),
                    new NoteBeat(startTime+gap*125, "F"),
                    new NoteBeat(startTime+gap*130, "D"),
                    new NoteBeat(startTime+gap*135, "F"),
                    new NoteBeat(startTime+gap*140, "J"),
                    new NoteBeat(startTime+gap*145, "F"),
                    new NoteBeat(startTime+gap*150, "J"),                     
                    new NoteBeat(startTime+gap*155, "L"),
                    new NoteBeat(startTime+gap*160, "D"),
                    new NoteBeat(startTime+gap*165, "F"),
                    new NoteBeat(startTime+gap*170, "J"),
                    new NoteBeat(startTime+gap*175, "F"),
                    new NoteBeat(startTime+gap*180, "D"),
                    new NoteBeat(startTime+gap*185, "F"),
                    new NoteBeat(startTime+gap*190, "D"),
                    new NoteBeat(startTime+gap*195, "L"),
                    new NoteBeat(startTime+gap*200, "D"),
                    new NoteBeat(startTime+gap*205, "F"),
                    new NoteBeat(startTime+gap*210, "J"),
                    new NoteBeat(startTime+gap*215, "F"),
                    new NoteBeat(startTime+gap*220, "D"),
                    new NoteBeat(startTime+gap*225, "F"),
                    new NoteBeat(startTime+gap*230, "L"),
                    new NoteBeat(startTime+gap*235, "J"),
                    new NoteBeat(startTime+gap*240, "F"),
                    new NoteBeat(startTime+gap*245, "J"),
                    new NoteBeat(startTime+gap*250, "K"),
                    new NoteBeat(startTime+gap*255, "L"),
                    new NoteBeat(startTime+gap*260, "F"),
                    new NoteBeat(startTime+gap*265, "L"),
                    new NoteBeat(startTime+gap*270, "F"),
                    new NoteBeat(startTime+gap*275, "L"),
                    new NoteBeat(startTime+gap*280, "F"),
                    new NoteBeat(startTime+gap*285, "L"),
                    new NoteBeat(startTime+gap*290, "F"),
                    new NoteBeat(startTime+gap*295, "L"),
                    new NoteBeat(startTime+gap*300, "J"),
                    new NoteBeat(startTime+gap*305, "D"),
                    new NoteBeat(startTime+gap*310, "J"),
                    new NoteBeat(startTime+gap*315, "K"),
                    new NoteBeat(startTime+gap*320, "L"),
                    new NoteBeat(startTime+gap*325, "F"),
                    new NoteBeat(startTime+gap*330, "L"),
                    new NoteBeat(startTime+gap*335, "F"),
                    new NoteBeat(startTime+gap*340, "L"),
                    new NoteBeat(startTime+gap*345, "F"),
                    new NoteBeat(startTime+gap*350, "L"),
                    new NoteBeat(startTime+gap*355, "F"),
                    new NoteBeat(startTime+gap*360, "L"),
                    new NoteBeat(startTime+gap*365, "J"),
                    new NoteBeat(startTime+gap*370, "D"),
                    new NoteBeat(startTime+gap*375, "F"),
                    new NoteBeat(startTime+gap*380, "D"),
                    new NoteBeat(startTime+gap*385, "F"),
                    new NoteBeat(startTime+gap*390, "J"),
                    new NoteBeat(startTime+gap*395, "F"),
                    new NoteBeat(startTime+gap*400, "J"),
                    new NoteBeat(startTime+gap*405, "F"),
                    new NoteBeat(startTime+gap*410, "J"),
                    new NoteBeat(startTime+gap*415, "F"),
                    new NoteBeat(startTime+gap*420, "J"),
                    new NoteBeat(startTime+gap*425, "F"),
                    new NoteBeat(startTime+gap*430, "J"),
                    new NoteBeat(startTime+gap*435, "S"),
                    new NoteBeat(startTime+gap*440, "F"),
                    new NoteBeat(startTime+gap*445, "S"),
                    new NoteBeat(startTime+gap*450, "J"),
                    new NoteBeat(startTime+gap*455, "F"),
                    new NoteBeat(startTime+gap*460, "D"),
                    new NoteBeat(startTime+gap*465, "F"),                     
                    new NoteBeat(startTime+gap*470, "L"),
                    new NoteBeat(startTime+gap*475, "D"),
                    new NoteBeat(startTime+gap*480, "F"),
                    new NoteBeat(startTime+gap*485, "J"),
                    new NoteBeat(startTime+gap*490, "F"),
                    new NoteBeat(startTime+gap*495, "D"),
                    new NoteBeat(startTime+gap*500, "F"),
                    new NoteBeat(startTime+gap*505, "D"),
                    new NoteBeat(startTime+gap*510, "L"),
                    new NoteBeat(startTime+gap*515, "D"),
                    new NoteBeat(startTime+gap*520, "F"),
                    new NoteBeat(startTime+gap*525, "J"),
                    new NoteBeat(startTime+gap*530, "F"),
                    new NoteBeat(startTime+gap*535, "D"),
                    new NoteBeat(startTime+gap*540, "F"),
                    new NoteBeat(startTime+gap*545, "J"),
                    new NoteBeat(startTime+gap*550, "F"),
                    new NoteBeat(startTime+gap*555, "J"),                     
                    new NoteBeat(startTime+gap*560, "L"),
                    new NoteBeat(startTime+gap*565, "D"),
                    new NoteBeat(startTime+gap*570, "F"),
                    new NoteBeat(startTime+gap*575, "J"),
                    new NoteBeat(startTime+gap*580, "F"),
                    new NoteBeat(startTime+gap*585, "D"),
                    new NoteBeat(startTime+gap*590, "F"),
                    new NoteBeat(startTime+gap*595, "D"),
                    new NoteBeat(startTime+gap*600, "L"),
                    new NoteBeat(startTime+gap*605, "D"),
                    new NoteBeat(startTime+gap*610, "F"),
                    new NoteBeat(startTime+gap*615, "J"),
                    new NoteBeat(startTime+gap*620, "F"),
                    new NoteBeat(startTime+gap*625, "D"),
                    new NoteBeat(startTime+gap*630, "F"),
                    new NoteBeat(startTime+gap*635, "L"),
                    new NoteBeat(startTime+gap*640, "J"),
                    new NoteBeat(startTime+gap*645, "F"), 
                    new NoteBeat(startTime+gap*650, "J"),
                    new NoteBeat(startTime+gap*655, "K"),
                    new NoteBeat(startTime+gap*660, "L"),
                    new NoteBeat(startTime+gap*665, "F"),
                    new NoteBeat(startTime+gap*670, "L"),
                    new NoteBeat(startTime+gap*675, "F"),
                    new NoteBeat(startTime+gap*680, "L"),
                    new NoteBeat(startTime+gap*685, "F"),
                    new NoteBeat(startTime+gap*690, "L"),
                    new NoteBeat(startTime+gap*695, "F"),
                    new NoteBeat(startTime+gap*700, "L"),
                    new NoteBeat(startTime+gap*705, "J"),
                    new NoteBeat(startTime+gap*710, "D"),
                    new NoteBeat(startTime+gap*715, "J"),
                    new NoteBeat(startTime+gap*720, "K"),
                    new NoteBeat(startTime+gap*725, "L"),
                    new NoteBeat(startTime+gap*730, "F"),
                    new NoteBeat(startTime+gap*735, "L"),
                    new NoteBeat(startTime+gap*740, "F"),
                    new NoteBeat(startTime+gap*745, "L"),
                    new NoteBeat(startTime+gap*750, "F"),
                    new NoteBeat(startTime+gap*755, "L"),
                    new NoteBeat(startTime+gap*760, "F"),
                    new NoteBeat(startTime+gap*765, "L"),
                    new NoteBeat(startTime+gap*770, "J"),
                    new NoteBeat(startTime+gap*775, "D"),
                    new NoteBeat(startTime+gap*780, "F"),
                    new NoteBeat(startTime+gap*785, "D"),
                    new NoteBeat(startTime+gap*790, "F"),
                    new NoteBeat(startTime+gap*795, "J"),
                    new NoteBeat(startTime+gap*800, "J"),
                    new NoteBeat(startTime+gap*805, "F"),
                    new NoteBeat(startTime+gap*810, "J"),
                    new NoteBeat(startTime+gap*815, "F"),
                    new NoteBeat(startTime+gap*820, "J"),
                    new NoteBeat(startTime+gap*825, "S"),
                    new NoteBeat(startTime+gap*830, "J"),
                    new NoteBeat(startTime+gap*835, "F"),
                    new NoteBeat(startTime+gap*840, "D"),
                    new NoteBeat(startTime+gap*845, "S"),
                    new NoteBeat(startTime+gap*850, "D"),
                    new NoteBeat(startTime+gap*855, "F"), 
                    new NoteBeat(startTime+gap*860, "D"),
                    new NoteBeat(startTime+gap*865, "L"),
                    new NoteBeat(startTime+gap*870, "J"),
                    new NoteBeat(startTime+gap*875, "F"),
                    new NoteBeat(startTime+gap*880, "J"),
                    new NoteBeat(startTime+gap*885, "K"),
                    new NoteBeat(startTime+gap*890, "J"),
                    new NoteBeat(startTime+gap*895, "F"),                     
                    new NoteBeat(startTime+gap*900, "J"),
                    new NoteBeat(startTime+gap*905, "F"),
                    new NoteBeat(startTime+gap*910, "J"),
                    new NoteBeat(startTime+gap*915, "F"),
                    new NoteBeat(startTime+gap*920, "D"),
                    new NoteBeat(startTime+gap*925, "J"),
                    new NoteBeat(startTime+gap*930, "F"),
                    new NoteBeat(startTime+gap*935, "J"),
                    new NoteBeat(startTime+gap*940, "F"),
                    new NoteBeat(startTime+gap*945, "D"),
                    new NoteBeat(startTime+gap*950, "J"),
                    new NoteBeat(startTime+gap*955, "F"),
                    new NoteBeat(startTime+gap*960, "J"),
                    new NoteBeat(startTime+gap*965, "F"),
                    new NoteBeat(startTime+gap*970, "D"),
                    new NoteBeat(startTime+gap*975, "L"),
                    new NoteBeat(startTime+gap*980, "F"),
                    new NoteBeat(startTime+gap*985, "L"),
                    new NoteBeat(startTime+gap*990, "F"),
                    new NoteBeat(startTime+gap*995, "L"),
                    new NoteBeat(startTime+gap*1000, "D"),
                    new NoteBeat(startTime+gap*1005, "F"),
                    new NoteBeat(startTime+gap*1010, "L"),
                    new NoteBeat(startTime+gap*1015, "F"),
                    new NoteBeat(startTime+gap*1020, "D"),
                    new NoteBeat(startTime+gap*1025, "L"),
                    new NoteBeat(startTime+gap*1030, "F"),
                    new NoteBeat(startTime+gap*1035, "L"),
                    new NoteBeat(startTime+gap*1040, "F"),
                    new NoteBeat(startTime+gap*1045, "L"),
                    new NoteBeat(startTime+gap*1050, "D"),
                    new NoteBeat(startTime+gap*1055, "F"),
                    new NoteBeat(startTime+gap*1060, "L"),
                    new NoteBeat(startTime+gap*1065, "F"),
                    new NoteBeat(startTime+gap*1070, "D"),
                    new NoteBeat(startTime+gap*1075, "L"),
                    new NoteBeat(startTime+gap*1080, "F"),
                    new NoteBeat(startTime+gap*1085, "L"),
                    new NoteBeat(startTime+gap*1090, "F"),
                    new NoteBeat(startTime+gap*1095, "L"),
                    new NoteBeat(startTime+gap*1100, "D"),
                    new NoteBeat(startTime+gap*1105, "F"),
                    new NoteBeat(startTime+gap*1110, "L"),
                    new NoteBeat(startTime+gap*1115, "F"),
                    new NoteBeat(startTime+gap*1120, "D"),
                    new NoteBeat(startTime+gap*1125, "L"),
                    new NoteBeat(startTime+gap*1130, "F"),
                    new NoteBeat(startTime+gap*1135, "L"),
                    new NoteBeat(startTime+gap*1140, "F"),
                    new NoteBeat(startTime+gap*1145, "L"),
                    new NoteBeat(startTime+gap*1150, "D"),
                    new NoteBeat(startTime+gap*1155, "F"),
                    new NoteBeat(startTime+gap*1160, "L"),
                 
			};
		}
		else if(titleName.equals("canon")){
			int startTime = 1000 - Main.REACH_TIME * 1000 ;
			int gap = 125;
	         beats = new NoteBeat[] {
	                 new NoteBeat(startTime+gap*10,"K" ),
	                 new NoteBeat(startTime+gap*22,"F" ),
	                 new NoteBeat(startTime+gap*33,"J" ),
	                 new NoteBeat(startTime+gap*45,"D" ),
	                 new NoteBeat(startTime+gap*54,"J" ),
	                 new NoteBeat(startTime+gap*66,"K" ),
	                 
	                 new NoteBeat(startTime+gap*75,"L" ),
	                 new NoteBeat(startTime+gap*85,"K" ),
	                 new NoteBeat(startTime+gap*96,"J" ),
	                 new NoteBeat(startTime+gap*107,"F" ),
	                 new NoteBeat(startTime+gap*118,"D" ),
	                 new NoteBeat(startTime+gap*129,"S" ),
	                 new NoteBeat(startTime+gap*140,"D" ),
	                 new NoteBeat(startTime+gap*151,"F" ),
	                 
	                 new NoteBeat(startTime+gap*162,"L" ),
	                 new NoteBeat(startTime+gap*172,"K" ),
	                 new NoteBeat(startTime+gap*181,"J" ),
	                 new NoteBeat(startTime+gap*191,"F" ),
	                 new NoteBeat(startTime+gap*202,"D" ),
	                 new NoteBeat(startTime+gap*213,"S" ),
	                 new NoteBeat(startTime+gap*225,"D" ),
	                 new NoteBeat(startTime+gap*235,"F" ),
	                 new NoteBeat(startTime+gap*242,"J" ),
	                 new NoteBeat(startTime+gap*244,"K" ),
	                 new NoteBeat(startTime+gap*247,"L" ),
	                 
	                 new NoteBeat(startTime+gap*258,"K" ),
	                 new NoteBeat(startTime+gap*261,"L" ),
	                 new NoteBeat(startTime+gap*263,"K" ),
	                 new NoteBeat(startTime+gap*265,"J" ),
	                 new NoteBeat(startTime+gap*267,"F" ),
	                 new NoteBeat(startTime+gap*276,"J" ),
	                 new NoteBeat(startTime+gap*278,"F" ),
	                 new NoteBeat(startTime+gap*280,"D" ),
	                 new NoteBeat(startTime+gap*283,"F" ),
	                 new NoteBeat(startTime+gap*286,"K" ),
	                 
	                 new NoteBeat(startTime+gap*290,"L" ),
	                 new NoteBeat(startTime+gap*293,"K" ),
	                 new NoteBeat(startTime+gap*296,"J" ),
	                 new NoteBeat(startTime+gap*298,"L" ),
	                 new NoteBeat(startTime+gap*300,"K" ),
	                 new NoteBeat(startTime+gap*302,"J" ),
	                 new NoteBeat(startTime+gap*305,"F" ),
	                 new NoteBeat(startTime+gap*308,"D" ),
	                 new NoteBeat(startTime+gap*310,"F" ),
	                 new NoteBeat(startTime+gap*313,"D" ),
	                 new NoteBeat(startTime+gap*316,"S" ),
	                 new NoteBeat(startTime+gap*319,"F" ),
	                 new NoteBeat(startTime+gap*321,"D" ),
	                 
	                 new NoteBeat(startTime+gap*330,"F" ),
	                 new NoteBeat(startTime+gap*333,"F" ),
	                 new NoteBeat(startTime+gap*336,"L" ),
	                 new NoteBeat(startTime+gap*338,"K" ),
	                 
	                 new NoteBeat(startTime+gap*341,"D" ),
	                 new NoteBeat(startTime+gap*344,"D" ),
	                 new NoteBeat(startTime+gap*347,"K" ),
	                 new NoteBeat(startTime+gap*349,"J" ),
	                 
	                 new NoteBeat(startTime+gap*351,"S" ),
	                 new NoteBeat(startTime+gap*354,"S" ),
	                 new NoteBeat(startTime+gap*356,"J" ),
	                 new NoteBeat(startTime+gap*359,"K" ),
	                 
	                 new NoteBeat(startTime+gap*362,"D" ),
	                 new NoteBeat(startTime+gap*364,"D" ),
	                 new NoteBeat(startTime+gap*365,"S" ),
	                 new NoteBeat(startTime+gap*367,"D" ),
	                 
	                 new NoteBeat(startTime+gap*369,"S" ),
	                 new NoteBeat(startTime+gap*371,"S" ),
	                 new NoteBeat(startTime+gap*372,"J" ),
	                 new NoteBeat(startTime+gap*373,"K" ),
	                 new NoteBeat(startTime+gap*374,"J" ),
	                 new NoteBeat(startTime+gap*376,"K" ),
	                 
	                 new NoteBeat(startTime+gap*378,"L" ),
	                 new NoteBeat(startTime+gap*379,"K" ),
	                 new NoteBeat(startTime+gap*380,"J" ),
	                 new NoteBeat(startTime+gap*381,"F" ),
	                 
	                 new NoteBeat(startTime+gap*383,"S" ),
	                 new NoteBeat(startTime+gap*385,"S" ),
	                 new NoteBeat(startTime+gap*386,"J" ),
	                 new NoteBeat(startTime+gap*387,"K" ),
	                 new NoteBeat(startTime+gap*388,"L" ),
	                 new NoteBeat(startTime+gap*389,"K" ),
	                 
	                 new NoteBeat(startTime+gap*391,"J" ),
	                 new NoteBeat(startTime+gap*393,"D" ),
	                 
	                 new NoteBeat(startTime+gap*395,"J" ),
	                 new NoteBeat(startTime+gap*396,"K" ),
	                 new NoteBeat(startTime+gap*397,"L" ),
	                 new NoteBeat(startTime+gap*398,"K" ),
	                 
	                 new NoteBeat(startTime+gap*400,"F" ),
	                 new NoteBeat(startTime+gap*403,"F" ),
	                 new NoteBeat(startTime+gap*405,"L" ),
	                 new NoteBeat(startTime+gap*407,"K" ),
	                 
	                 new NoteBeat(startTime+gap*409,"D" ),
	                 new NoteBeat(startTime+gap*412,"D" ),
	                 new NoteBeat(startTime+gap*414,"K" ),
	                 new NoteBeat(startTime+gap*416,"J" ),
	                 
	                 new NoteBeat(startTime+gap*418,"S" ),
	                 new NoteBeat(startTime+gap*420,"S" ),
	                 new NoteBeat(startTime+gap*422,"J" ),
	                 new NoteBeat(startTime+gap*424,"K" ),
	                 
	                 new NoteBeat(startTime+gap*426,"D" ),
	                 new NoteBeat(startTime+gap*428,"D" ),
	                 new NoteBeat(startTime+gap*429,"S" ),
	                 new NoteBeat(startTime+gap*431,"D" ),
	                 
	                 new NoteBeat(startTime+gap*434,"S" ),
	                 new NoteBeat(startTime+gap*436,"S" ),
	                 new NoteBeat(startTime+gap*438,"J" ),
	                 new NoteBeat(startTime+gap*439,"K" ),
	                 new NoteBeat(startTime+gap*440,"J" ),
	                 new NoteBeat(startTime+gap*442,"K" ),
	                 
	                 new NoteBeat(startTime+gap*444,"L" ),
	                 new NoteBeat(startTime+gap*445,"K" ),
	                 new NoteBeat(startTime+gap*446,"J" ),
	                 new NoteBeat(startTime+gap*447,"F" ),
	                 
	                 new NoteBeat(startTime+gap*450,"S" ),
	                 new NoteBeat(startTime+gap*452,"S" ),
	                 new NoteBeat(startTime+gap*454,"J" ),
	                 new NoteBeat(startTime+gap*455,"K" ),
	                 new NoteBeat(startTime+gap*456,"L" ),
	                 new NoteBeat(startTime+gap*457,"K" ),
	                 
	                 new NoteBeat(startTime+gap*460,"J" ),
	                 new NoteBeat(startTime+gap*462,"F" ),
	                 new NoteBeat(startTime+gap*464,"J" ),
	                 
	                 new NoteBeat(startTime+gap*470,"L" ),
	                 new NoteBeat(startTime+gap*478,"K" ),
	                 new NoteBeat(startTime+gap*486,"J" ),
	                 new NoteBeat(startTime+gap*491,"K" ),
	                 new NoteBeat(startTime+gap*492,"J" ),
	                 new NoteBeat(startTime+gap*493,"F" ),
	                 new NoteBeat(startTime+gap*496,"D" ),
	                 new NoteBeat(startTime+gap*498,"S" ),
	                 
	                 new NoteBeat(startTime+gap*500,"J" ),
	                 new NoteBeat(startTime+gap*505,"F" ),
	                 new NoteBeat(startTime+gap*506,"D" ),
	                 new NoteBeat(startTime+gap*508,"F" ),
	                 new NoteBeat(startTime+gap*510,"J" ),
	                 new NoteBeat(startTime+gap*512,"D" ),
	                 
	                 new NoteBeat(startTime+gap*515,"J" ),
	                 new NoteBeat(startTime+gap*518,"F" ),
	                 new NoteBeat(startTime+gap*519,"D" ),
	                 new NoteBeat(startTime+gap*520,"K" ),
	            
	                 new NoteBeat(startTime+gap*523,"J" ),
	                 new NoteBeat(startTime+gap*525,"K" ),
	                 new NoteBeat(startTime+gap*527,"L" ),
	                 new NoteBeat(startTime+gap*529,"K" ),
	                 new NoteBeat(startTime+gap*561,"J" ),
	                 new NoteBeat(startTime+gap*563,"K" ),
	                 new NoteBeat(startTime+gap*565,"J" ),
	                 new NoteBeat(startTime+gap*567,"F" ),
	                 new NoteBeat(startTime+gap*569,"J" ),
	                 new NoteBeat(startTime+gap*571,"K" ),
	                 
	                 new NoteBeat(startTime+gap*573,"L" ),
	                 new NoteBeat(startTime+gap*575,"K" ),
	                 new NoteBeat(startTime+gap*575,"J" ),
	                 
	                 new NoteBeat(startTime+gap*577,"L" ),
	                 new NoteBeat(startTime+gap*579,"K" ),
	                 new NoteBeat(startTime+gap*581,"J" ),
	                 new NoteBeat(startTime+gap*583,"F" ),
	                 new NoteBeat(startTime+gap*585,"J" ),
	                 new NoteBeat(startTime+gap*587,"K" ),
	                 new NoteBeat(startTime+gap*589,"J" ),
	                 new NoteBeat(startTime+gap*591,"F" ),
	                 new NoteBeat(startTime+gap*593,"K" ),
	                 new NoteBeat(startTime+gap*595,"L" ),
	                 
	                 new NoteBeat(startTime+gap*597,"K" ),
	                 new NoteBeat(startTime+gap*599,"J" ),
	                 new NoteBeat(startTime+gap*601,"K" ),
	                 new NoteBeat(startTime+gap*603,"S" ),
	                 new NoteBeat(startTime+gap*605,"D" ),
	                 
	                 new NoteBeat(startTime+gap*607,"D" ),
	                 new NoteBeat(startTime+gap*609,"F" ),
	                 new NoteBeat(startTime+gap*611,"J" ),
	                 new NoteBeat(startTime+gap*613,"F" ),
	                 new NoteBeat(startTime+gap*615,"J" ),
	                 new NoteBeat(startTime+gap*617,"K" ),
	                 new NoteBeat(startTime+gap*619,"L" ),
	                 new NoteBeat(startTime+gap*621,"K" ),
	                 
	                 new NoteBeat(startTime+gap*623,"L" ),
	                 new NoteBeat(startTime+gap*625,"K" ),
	                 new NoteBeat(startTime+gap*627,"J" ),
	                 new NoteBeat(startTime+gap*629,"L" ),
	              
	                 new NoteBeat(startTime+gap*631,"K" ),
	                 new NoteBeat(startTime+gap*633,"J" ),
	                 new NoteBeat(startTime+gap*635,"F" ),
	                 new NoteBeat(startTime+gap*637,"D" ),
	                 new NoteBeat(startTime+gap*639,"S" ),
	                 
	                 new NoteBeat(startTime+gap*641,"K" ),
	                 new NoteBeat(startTime+gap*643,"J" ),
	                 new NoteBeat(startTime+gap*645,"S" ),
	                 new NoteBeat(startTime+gap*647,"F" ),
	                 new NoteBeat(startTime+gap*649,"D" ),
	                 
	                 new NoteBeat(startTime+gap*651,"K" ),
	                 new NoteBeat(startTime+gap*653,"J" ),
	                 new NoteBeat(startTime+gap*655,"S" ),
	                 new NoteBeat(startTime+gap*657,"K" ),
	                 new NoteBeat(startTime+gap*659,"L" ),
	                 new NoteBeat(startTime+gap*661,"K" ),
	                 new NoteBeat(startTime+gap*663,"J" ),
	                 new NoteBeat(startTime+gap*665,"F" ),
	                 new NoteBeat(startTime+gap*667,"D" ),
	                 new NoteBeat(startTime+gap*669,"S" ),
	              
	                 new NoteBeat(startTime+gap*671,"K" ),
	                 new NoteBeat(startTime+gap*673,"J" ),
	                 new NoteBeat(startTime+gap*675,"K" ),
	                 new NoteBeat(startTime+gap*677,"S" ),
	                 new NoteBeat(startTime+gap*679,"D" ),
	              
	                 new NoteBeat(startTime+gap*681,"S" ),
	                 new NoteBeat(startTime+gap*683,"D" ),
	              
	                 new NoteBeat(startTime+gap*685,"D" ),
	                 new NoteBeat(startTime+gap*687,"F" ),
	                 new NoteBeat(startTime+gap*689,"J" ),
	                 new NoteBeat(startTime+gap*691,"K" ),
	                 new NoteBeat(startTime+gap*693,"L" ),
	                 new NoteBeat(startTime+gap*695,"K" ),
	                 new NoteBeat(startTime+gap*697,"J" ),
	                 new NoteBeat(startTime+gap*699,"F" ),
	                 new NoteBeat(startTime+gap*701,"K" ),
	                 new NoteBeat(startTime+gap*703,"K" ),
	                 
	                 new NoteBeat(startTime+gap*705,"K" ),
	                 new NoteBeat(startTime+gap*707,"J" ),
	                 new NoteBeat(startTime+gap*709,"S" ),
	                 new NoteBeat(startTime+gap*711,"D" ),
	                 new NoteBeat(startTime+gap*713,"S" ),
	                 new NoteBeat(startTime+gap*715,"F" ),
	                 new NoteBeat(startTime+gap*717,"J" ),
	                 new NoteBeat(startTime+gap*719,"K" ),
	                 
	                 new NoteBeat(startTime+gap*723,"L" ),
	                 new NoteBeat(startTime+gap*726,"K" ),
	                 new NoteBeat(startTime+gap*730,"J" ),
	                 new NoteBeat(startTime+gap*735,"F" ),
	                 new NoteBeat(startTime+gap*738,"J" ),
	                 new NoteBeat(startTime+gap*741,"F" ),
	                 new NoteBeat(startTime+gap*745,"D" ),
	                 new NoteBeat(startTime+gap*752,"S" ),
	                 
	                 new NoteBeat(startTime+gap*760,"K" ),
	                 new NoteBeat(startTime+gap*764,"J" ),
	                 new NoteBeat(startTime+gap*767,"F" ),
	                 new NoteBeat(startTime+gap*770,"D" ),
	                 new NoteBeat(startTime+gap*773,"F" ),
	                 new NoteBeat(startTime+gap*777,"D" ),
	                 new NoteBeat(startTime+gap*780,"S" ),
	                 
	                 new NoteBeat(startTime+gap*783,"L" ),
	                 new NoteBeat(startTime+gap*785,"J" ),
	                 new NoteBeat(startTime+gap*790,"K" ),
	                 new NoteBeat(startTime+gap*793,"L" ),
	                 new NoteBeat(startTime+gap*796,"J" ),
	                 new NoteBeat(startTime+gap*800,"K" ),
	                 
	                 new NoteBeat(startTime+gap*804,"J" ),
	                 new NoteBeat(startTime+gap*808,"K" ),
	                 new NoteBeat(startTime+gap*812,"L" ),
	                 new NoteBeat(startTime+gap*815,"K" ),
	                 new NoteBeat(startTime+gap*818,"J" ),
	                 new NoteBeat(startTime+gap*820,"K" ),
	                 new NoteBeat(startTime+gap*825,"J" ),
	                 new NoteBeat(startTime+gap*832,"F" ),
	                 new NoteBeat(startTime+gap*837,"J" ),
	                 new NoteBeat(startTime+gap*840,"K" ),
	                 
	                 new NoteBeat(startTime+gap*844,"L" ),
	                 new NoteBeat(startTime+gap*847,"D" ),
	                 new NoteBeat(startTime+gap*850,"S" ),
	                 new NoteBeat(startTime+gap*852,"D" ),
	                 new NoteBeat(startTime+gap*857,"F" ),
	                 new NoteBeat(startTime+gap*860,"J" ),
	                 new NoteBeat(startTime+gap*864,"K" ),
	                 new NoteBeat(startTime+gap*868,"L" ),
	                 
	                 new NoteBeat(startTime+gap*870,"K" ),
	                 new NoteBeat(startTime+gap*875,"D" ),
	                 new NoteBeat(startTime+gap*880,"F" ),
	                 new NoteBeat(startTime+gap*882,"K" ),
	                 
	                 new NoteBeat(startTime+gap*885,"S" ),
	                 new NoteBeat(startTime+gap*890,"D" ),
	                 new NoteBeat(startTime+gap*897,"F" ),
	                 new NoteBeat(startTime+gap*900,"J" ),
	                 new NoteBeat(startTime+gap*905,"F" ),
	                 new NoteBeat(startTime+gap*908,"D" ),
	                 new NoteBeat(startTime+gap*910,"F" ),
	                 
	                 new NoteBeat(startTime+gap*913,"L" ),
	                 new NoteBeat(startTime+gap*917,"K" ),
	                 new NoteBeat(startTime+gap*920,"L" ),
	                 new NoteBeat(startTime+gap*925,"J" ),
	                 new NoteBeat(startTime+gap*930,"L" ),
	                 new NoteBeat(startTime+gap*933,"K" ),
	                 new NoteBeat(startTime+gap*935,"J" ),
	                 
	                 new NoteBeat(startTime+gap*940,"F" ),
	                 new NoteBeat(startTime+gap*944,"D" ),
	                 new NoteBeat(startTime+gap*948,"F" ),
	                 new NoteBeat(startTime+gap*950,"D" ),
	                 new NoteBeat(startTime+gap*955,"S" ),
	                 new NoteBeat(startTime+gap*960,"D" ),
	                 new NoteBeat(startTime+gap*963,"F" ),
	                 new NoteBeat(startTime+gap*970,"J" ),
	                 new NoteBeat(startTime+gap*973,"K" ),
	                 new NoteBeat(startTime+gap*975,"L" ),
	                 
	                 new NoteBeat(startTime+gap*980,"J" ),
	                 new NoteBeat(startTime+gap*982,"L" ),
	                 new NoteBeat(startTime+gap*986,"K" ),
	                 new NoteBeat(startTime+gap*990,"L" ),
	                 
	                 new NoteBeat(startTime+gap*994,"J" ),
	                 new NoteBeat(startTime+gap*1000,"K" ),
	                 
	                 new NoteBeat(startTime+gap*1003,"S" ),
	                 new NoteBeat(startTime+gap*1005,"D" ),
	                 new NoteBeat(startTime+gap*1009,"F" ),
	                 new NoteBeat(startTime+gap*1012,"D" ),               
	                 new NoteBeat(startTime+gap*1017,"F" ),
	                 new NoteBeat(startTime+gap*1020,"J" ),
	                 new NoteBeat(startTime+gap*1024,"K" ),
	                 new NoteBeat(startTime+gap*1027,"L" ),
	                 
	                 new NoteBeat(startTime+gap*1030,"L" ),
	                 new NoteBeat(startTime+gap*1037,"J" ),
	                 new NoteBeat(startTime+gap*1040,"K" ),
	                 new NoteBeat(startTime+gap*1046,"L" ),
	                 
	                 new NoteBeat(startTime+gap*1050,"K" ),
	                 new NoteBeat(startTime+gap*1055,"J" ),
	                 
	                 new NoteBeat(startTime+gap*1058,"K" ),
	                 new NoteBeat(startTime+gap*1060,"D" ),
	                 new NoteBeat(startTime+gap*1062,"F" ),
	                 new NoteBeat(startTime+gap*1065,"J" ),
	                 new NoteBeat(startTime+gap*1068,"K" ),
	                 new NoteBeat(startTime+gap*1070,"J" ),
	                 new NoteBeat(startTime+gap*1076,"D" ),
	                 new NoteBeat(startTime+gap*1079,"S" ),
	                 
	                 new NoteBeat(startTime+gap*1082,"K" ),
	                 new NoteBeat(startTime+gap*1085,"D" ),               
	                 new NoteBeat(startTime+gap*1088,"F" ),
	                 new NoteBeat(startTime+gap*1093,"K" ),
	                 
	                 new NoteBeat(startTime+gap*1096,"S" ),               
	                 new NoteBeat(startTime+gap*1100,"D" ),
	                 new NoteBeat(startTime+gap*1105,"F" ),
	                 new NoteBeat(startTime+gap*1108,"J" ),
	                 new NoteBeat(startTime+gap*1110,"F" ),
	                 new NoteBeat(startTime+gap*1118,"D" ),
	                 new NoteBeat(startTime+gap*1124,"F" ),
	                 
	                 new NoteBeat(startTime+gap*1128,"S" ),
	                 new NoteBeat(startTime+gap*1130,"D" ),
	                 new NoteBeat(startTime+gap*1135,"F" ),
	                 new NoteBeat(startTime+gap*1138,"L" ),      
	                 new NoteBeat(startTime+gap*1144,"K" ),
	                 
	                 new NoteBeat(startTime+gap*1148,"D" ),
	                 new NoteBeat(startTime+gap*1152,"F" ),
	                 new NoteBeat(startTime+gap*1156,"K" ),
	                 new NoteBeat(startTime+gap*1160,"J" ),
	                 
	                 new NoteBeat(startTime+gap*1163,"S" ),
	                 new NoteBeat(startTime+gap*1166,"S" ),
	                 new NoteBeat(startTime+gap*1169,"J" ),
	                 new NoteBeat(startTime+gap*1174,"K" ),
	                 
	                 new NoteBeat(startTime+gap*1178,"D" ),
	                 new NoteBeat(startTime+gap*1180,"F" ),
	                 new NoteBeat(startTime+gap*1182,"S" ),
	                 new NoteBeat(startTime+gap*1187,"D" ),
	                 
	                 new NoteBeat(startTime+gap*1190,"S" ),
	                 new NoteBeat(startTime+gap*1193,"S" ),
	                
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

