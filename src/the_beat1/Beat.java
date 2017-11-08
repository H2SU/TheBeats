package the_beat1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Beat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit.png")); 
	private ImageIcon startButtonBasicImage= new ImageIcon(Main.class.getResource("../images/start.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftbutton.png")); 
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftbutton2.png")); 
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightbutton.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightbutton2.png"));  
	private ImageIcon startButtonselectImage = new ImageIcon(Main.class.getResource("../images/startbutton2.png"));
	private ImageIcon startButtonselectedImage = new ImageIcon(Main.class.getResource("../images/startbutton.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backbutton.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.png")).getImage();

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton musicstartButton = new JButton(startButtonselectImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY; 

	private boolean isMainScreen = false; 
	private boolean isGameScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic; 
	private Music introMusic =new Music("intro.mp3",true);
	private int nowSelected = 0;

	public static Game game;
	
	public Beat() {
		trackList.add(new Track("elise1.png", "eliseselect.jpg",
				"selectedImage.png", "elise.mp3", "elise.mp3", "elise"));//0
		trackList.add(new Track("summer1.png", "summerselect.jpg",
				"selectedImage.png", "summer.mp3", "summer.mp3", "summer"));//1
		trackList.add(new Track("canon1.png", "canonselect.jpg",
				"selectedImage.png", "canon.mp3", "canon.mp3", "canon"));//2

		
		setUndecorated(true);
		setTitle("The Beats");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); 
		setLayout(null);

		addKeyListener (new KeyListener());
		
		introMusic.start();

		exitButton.setBounds(1170, 0, 120, 60);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { 
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} 
				System.exit(0); 
			}
		});
		add(exitButton); 

		startButton.setBounds(10, 0, 120, 60);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false); 
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { 
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
			}
		});
		add(startButton);

		leftButton.setVisible(false);
		leftButton.setBounds(80, 250, 200, 200); 
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();

			}
		});
		add(leftButton); 

		rightButton.setVisible(false);
		rightButton.setBounds(1000, 250, 200, 200); 
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);

		musicstartButton.setVisible(false);
		musicstartButton.setBounds(1035,500, 250, 250);
		musicstartButton.setBorderPainted(false);
		musicstartButton.setContentAreaFilled(false);
		musicstartButton.setFocusPainted(false);
		musicstartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				musicstartButton.setIcon(startButtonselectImage);
				musicstartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				musicstartButton.setIcon(startButtonselectedImage);
				musicstartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected);
			}
		});
		add(musicstartButton);

		backButton.setVisible(false);
		backButton.setBounds(20,50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);
	}
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); 
		if(isMainScreen){
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 250, 55, null);
		}
		if(isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}  
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() { 
		if(nowSelected == 0) 
			nowSelected = trackList.size() - 1;
		else
			nowSelected--; 
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if(nowSelected == trackList.size() - 1) 
			nowSelected = 0;
		else
			nowSelected++; 
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close(); 
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		musicstartButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).gettitleName(),trackList.get(nowSelected).getGameMusic() );
		game.start(); //자동 run실행해서 노트 자동 떨어짐 
		setFocusable(true);
		
	}
	public void backMain() {
		isMainScreen =true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		musicstartButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/background1.png"))
				.getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}
	public void enterMain() {
		startButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/background1.png"))
				.getImage(); 
		isMainScreen = true; 
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		musicstartButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}

}
