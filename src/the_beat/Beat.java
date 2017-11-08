package the_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
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
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftbutton.png")); //메인화면 왼쪽버튼 진입상태
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftbutton2.png")); //메인화면 왼쪽버튼
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightbutton.png"));//메인화면 오른쪽버튼 진입상태
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightbutton2.png")); //메인화면 오른쪽 
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.png")).getImage(); 

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private int mouseX, mouseY; 

	private boolean isMainScreen = false; 
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;//음악제목이미지
	private Image selectedImage;//현재 선택된 곡의 이미지
	private Music selectedMusic; //선택된 곡
	private int nowSelected = 0;//현재 선택된 곡 초기화를 첫번째곡으로 설정
	
	public Beat() {
		setUndecorated(true);
		setTitle("The Beats");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); 
		setLayout(null);

		Music introMusic =new Music("intro.mp3",true);
		introMusic.start();

		trackList.add(new Track("elise1.png", "eliseselect.jpg",
				"selectedImage.png", "elise.mp3", "elise.mp3"));//0
		trackList.add(new Track("summer1.png", "summerselect.jpg",
				"selectedImage.png", "summer.mp3", "summer.mp3"));//1
		trackList.add(new Track("canon1.png", "canonselect.jpg",
				"selectedImage.png", "canon.mp3", "canon.mp3"));//2

		exitButton.setBounds(1170, 0, 120, 60);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { 
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//해당 음악(버튼진입음악)이 한번만 재생
				buttonEnteredMusic.start();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//해당 음악(버튼클릭음악)이 한번만 재생
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} //음악이 나오자마자 프로그램이 종료되지 않고 1초있다가 종료되도록 설정
				System.exit(0); //해당 프로그램 자체가 종료
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
				introMusic.close();//메인화면에 들어왔으므로 인트로음악중지
				selectTrack(0);//메뉴에 처음들어왔을 때 첫번째 곡이 보이도록 설정
				startButton.setVisible(false);//시작버튼 안보이게 설정
				leftButton.setVisible(true);//메인화면의 왼쪽버튼 보이게 설정
				rightButton.setVisible(true);//메인화면의 오른쪽버튼 보이게 설정
				background = new ImageIcon(Main.class.getResource("../images/background1.png"))
						.getImage(); //배경화면 이미지 바꿈
				isMainScreen = true; //시작화면에서 메인화면으로 바뀌므로 true로 설정
			}
		});
		add(startButton);
		
		//메인화면 왼쪽 버튼
		leftButton.setVisible(false);//맨처음 화면에서는 보이지 않다가 메인화면에 왔을때 screendraw()내에서 검사한 뒤 시작하기가 눌러졌을 때 보이도록 설정
		leftButton.setBounds(80, 250, 200, 200); //메인화면의 왼쪽버튼 크기와 위치 설정
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
				selectLeft();//왼쪽 버튼이 눌러졌을 때 곡 변경함수
				//왼쪽버튼 이벤트
			}
		});
		add(leftButton); //왼쪽버튼 추가
		//메인화면 오른쪽 버튼

		rightButton.setVisible(false);//맨처음 화면에서는 보이지 않다가 메인화면에 왔을때 screendraw()내에서 검사한 뒤 시작하기가 눌러졌을 때 보이도록 설정
		rightButton.setBounds(1000, 250, 200, 200); //메인화면의 오른쪽버튼 크기와 위치 설정
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
				selectRight();//오른쪽 버튼이 눌러졌을 때 곡 변경함수
			}
		});
		add(rightButton);
		//쉬움버튼
	}
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); 
		if(isMainScreen){
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 250, 55, null);//선택된 음악의 제목이미지
		}
		paintComponents(g);
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
	//트랙리스트 선택중 왼쪽 버튼이 눌러졌을때
	public void selectLeft() { 
		if(nowSelected == 0) //첫번째 곡일때 마지막 곡이 선택됨
			nowSelected = trackList.size() - 1;
		else
			nowSelected--; //첫번째 곡이 아닌 경우 그 왼쪽 곡이 선택된 곡이 됨
		selectTrack(nowSelected);//위에서 결정된 값에 대하여 selcetTrack()가 실행
	}
	//트랙리스트 선택중 오른쪽 버튼이 눌러졌을때
	public void selectRight() {
		if(nowSelected == trackList.size() - 1) //마지막 곡일때 첫번째 곡이 결정
			nowSelected = 0;
		else
			nowSelected++; //마지막 곡이 아닌 경우 현재 곡의 오른쪽 곡이 선택됨
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected) {//현재 선택된 곡의 번호과 난이도를 파라미터 변수로 하는 트랙선택 함수
		if(selectedMusic != null)
			selectedMusic.close(); //이미 선택된 곡을 있다면 기존 곡종료
		isMainScreen = false;//메인화면이 아님을 알려줌
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();//현재 선택된 곡의 이미지를 설정
	}

}
