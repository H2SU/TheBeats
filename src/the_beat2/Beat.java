package the_beat2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Beat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit.png")); 
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/LeftClick.png")); 
	private static ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Left.png")); 
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/RightClick.png"));
	private static ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Right.png"));  
	private static ImageIcon mainStartBasicImage = new ImageIcon(Main.class.getResource("../images/img_Main_StartBasic1.png"));
	private ImageIcon gameBackBasicImage = new ImageIcon(Main.class.getResource("../images/img_Game_BackBasic1.png"));

	private static Image background = new ImageIcon(Main.class.getResource("../images/img_Start.png")).getImage();

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private static JButton leftButton = new JButton(leftButtonBasicImage);
	private static JButton rightButton = new JButton(rightButtonBasicImage);
	private static JButton mainStartButton = new JButton(mainStartBasicImage);
	private JButton gameBack = new JButton(gameBackBasicImage);

	private int mouseX, mouseY; 

	private static boolean isMainScreen = false; 
	private boolean isGameScreen = false;

	static ArrayList<Track> trackList = new ArrayList<Track>();

	private static Image titleImage;
	private static Image selectedImage;
	private static Music selectedMusic; 
	private static Music introMusic = new Music("elise - beatVer.mp3",true);
	private int nowSelected = 0;

	public static Game game;

	public Beat() {
		trackList.add(new Track("img_Title_Elise.png", "img_Main_Elise.png",
				"selectedImage.png", "elise.mp3", "elise.mp3", "elise"));//0
		trackList.add(new Track("img_Title_Summer.png", "img_Main_Summer.png",
				"selectedImage.png", "summer.mp3", "summer.mp3", "summer"));//1
		trackList.add(new Track("img_Title_Canon.png", "img_Main_Canon.png",
				"selectedImage.png", "canon.mp3", "canon.mp3", "canon"));//2


		setUndecorated(true); // 메뉴바 안보임
		setTitle("The Beats");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 크기변경 안되게
		setLocationRelativeTo(null); // 창을 가운데로
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로세스까지 깔끔하게 종료
		// DISPOSE_ON_CLOSE 는 JVM에 남아있다고 한다. (Java Virtual Machine)<-자바 가상머신
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); 
		setLayout(null);
		setFocusable(true);


		addKeyListener (new KeyListener());


		introMusic.start();

		exitButton.setBounds(1150, 0, 144, 95);
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

		leftButton.setVisible(false);
		leftButton.setBounds(180, 310, 145, 181); 
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
		rightButton.setBounds(965, 310, 145, 181); 
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

		mainStartButton.setVisible(false);
		mainStartButton.setBounds(1103, 540, 180, 180);
		mainStartButton.setBorderPainted(false);
		mainStartButton.setContentAreaFilled(false);
		mainStartButton.setFocusPainted(false);
		mainStartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//mainStartButton.setIcon(startButtonselectedImage);
				mainStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainStartButton.setIcon(mainStartBasicImage);
				mainStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected);
			}
		});
		add(mainStartButton);

		gameBack.setVisible(false);
		gameBack.setBounds(1100, 540, 180, 180);
		gameBack.setBorderPainted(false);
		gameBack.setContentAreaFilled(false);
		gameBack.setFocusPainted(false);
		gameBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gameBack.setIcon(gameBackBasicImage);
				gameBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				gameBack.setIcon(gameBackBasicImage);
				gameBack.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(gameBack);
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
			g.drawImage(titleImage, 340, 600, null);
			//setFocusable(false);
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
	public static void selectTrack(int nowSelected) {
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
		mainStartButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		gameBack.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).gettitleName(),trackList.get(nowSelected).getGameMusic() );
		game.start();
		setFocusable(true);

	}
	public void backMain() {
		isMainScreen =true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		mainStartButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/img_Main.png"))
				.getImage();
		gameBack.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}

	public static void enterMain() {
		background = new ImageIcon(Main.class.getResource("../images/img_Main.png"))
				.getImage(); 
		isMainScreen = true; 
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		mainStartButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}



}
