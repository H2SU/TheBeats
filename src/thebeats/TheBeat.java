package thebeats;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import thebeats.Main;

@SuppressWarnings("serial")
public class TheBeat extends JFrame implements MouseListener {

	static Game game;
	private Image screenImage;
	private Graphics screenGraphic;

	private JButton startBtn;
	private JButton exitBtn;
	private JButton rightBtn;
	private JButton leftBtn;

	private Image background;
	private boolean isMainScreen = false; // 메인화면인지 아닌지 구분
	private boolean isGameScreen = false; // 게임화면인지 아닌지 구분
	private boolean isResultScreen = false;

	private Music introMusic;
	private Music btnEnteredMusic;

	private Image scoreImg;

	private Image selectedImg; // 선택된 앨범 이미지
	private Image titleImg;
	private static Music selectedMusic; // 선택된 곡

	static int nowSelected = 0; // 현재 선택된 항목

	private ArrayList<Track> trackList = new ArrayList<>();

	public TheBeat() {
		addKeyListener(new MyKeyListener(this)); // 키 리스너 등록
		background = new ImageIcon(Main.class.getResource("../images/background_intro.png")).getImage();
		initBtn();
		initLocateBtn();
		initListener();
		add(exitBtn);
		add(startBtn);
		add(rightBtn);
		add(leftBtn);
		setUndecorated(true);
		setFocusable(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);// 상수를 이용하여 전체게임창 크기설정
		setResizable(false);// 한 번 만들어진 게임창을 사용자가 임의로 변경할 수 없음
		setLocationRelativeTo(null);// 게임창이 컴퓨터 정중앙에 위치하도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 게임을 종료했을때 프로그램도 종료하도록
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);// 버튼이나 JLabel을 했을 때 그 위치에 놓이도록 함
		setVisible(true);// 게임창이 화면에 출력되도록 설정 default는 false

		trackList.add(new Track("track_elise.png", "track_elise_title.png", "selectedImg.png", "elise.mp3", "elise.mp3",
				"elise"));
		trackList.add(new Track("track_summer.png", "track_summer_title.png", "selectedImg.png", "summer.mp3",
				"summer.mp3", "summer"));
		trackList.add(new Track("track_canon.png", "track_canon_title.png", "selectedImg.png", "canon.mp3",
				"cannon.mp3", "canon"));

		btnEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
		introMusic = new Music("intro.mp3", true);
		introMusic.start();

	}

	public boolean isMainScreen() {
		return isMainScreen;
	}

	public boolean isGameScreen() {
		return isGameScreen;
	}

	//버튼 등록
	public void initBtn() {
		exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exit.png")));
		startBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_start.png")));
		rightBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_right.png")));
		leftBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_left.png")));
	}

	// 리스너 등록
	private void initListener() {
		exitBtn.addMouseListener(this);
		startBtn.addMouseListener(this);
		rightBtn.addMouseListener(this);
		leftBtn.addMouseListener(this);
	}

	public void paint(Graphics g) { // JFrame을 상속받은 클래스에서 가장 첫번째로 화면을 그려주는 메소드
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();// 스크린이미지를 이용해서 그래픽을 얻어옴
		screenDraw((Graphics2D) screenGraphic);// 스크린 그래픽에 그림을 그려줌
		g.drawImage(screenImage, 0, 0, null);// 스크린이미지를 (0,0)위치에 그려줌
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background이미지를 전체(스크린이미지)에 그려줌

		if (isMainScreen) {
			changeBtnVisibility(true); // 버튼들 보이게 하기.
			setFocusable(false);
			g.drawImage(selectedImg, 340, 100, null);// 선택된 음악의 이미지
			g.drawImage(titleImg, 340, 600, null);
		} else if (isGameScreen) {
			requestFocusInWindow();
			game.screenDraw(g);

		} else if (isResultScreen) {
			g.drawImage(scoreImg, 0, 100, null);
		}
		paintComponents(g);// JLabel같은 것을 JFrame 내에 추가하면 그려줌 항상 고정된 것을 그릴때 사용
		this.repaint();// 다시 paint()를 불러옴 전체화면 이미지를 매순간마다 프로그램이 종료되는 순간까지 그려줌
	}

	/**
	 * 메인 진입 시 호출되는 메소드.
	 */
	public void enteredMainScreen() {
		isMainScreen = true;
		background = new ImageIcon(Main.class.getResource("../images/background_main.png")).getImage(); // 배경화면
		introMusic.close();
		btnEnteredMusic.start();
		selectTrack(nowSelected);
	}

	public void selectTrack(int selected) {
		if (selectedMusic != null) // 나오는 음악있으면 끄기
			selectedMusic.close();

		selectedImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getAlbumImg()))
				.getImage();
		titleImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getTitleImg()))
				.getImage();
		selectedMusic = new Music(trackList.get(selected).getAlbumMusic(), true);
		selectedMusic.start();

	}

	/**
	 * 게임시작
	 */
	public void gameStart(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		isMainScreen = false;
		isGameScreen = true;
		changeBtnVisibility(false); // 버튼 다 안보이게 하기.
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImg()))
				.getImage();
		setFocusable(true);
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic(), this);
		game.start();
	}

	/**
	 * 게임 끝
	 * 
	 */
	public void gameEnd(char score, int perfect, int good, int bad, int miss) {
		isGameScreen = false;
		isResultScreen = true;
		background = (new ImageIcon(Main.class.getResource("../images/background_result.png")).getImage());

		// 점수 이미지 등록
		switch (score) {
		case 'S':
			scoreImg = new ImageIcon(Main.class.getResource("../images/rank_s.png")).getImage();
			break;
		case 'A':
			scoreImg = new ImageIcon(Main.class.getResource("../images/rank_a.png")).getImage();
			break;
		case 'B':
			scoreImg = new ImageIcon(Main.class.getResource("../images/rank_b.png")).getImage();
			break;
		case 'C':
			scoreImg = new ImageIcon(Main.class.getResource("../images/rank_c.png")).getImage();
			break;
		case 'F':
			scoreImg = new ImageIcon(Main.class.getResource("../images/rank_f.png")).getImage();
			break;
		}
	}

	// 버튼 가시상태 변경 메소드
	public void changeBtnVisibility(boolean state) {
		startBtn.setVisible(state);
		rightBtn.setVisible(state);
		leftBtn.setVisible(state);
	}

	/**
	 * 마우스 리스너
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();

		if (source == leftBtn) { // 왼쪽 버튼 클릭
			if (nowSelected > 0) {
				selectTrack(--nowSelected);

			} else
				selectTrack(nowSelected = trackList.size() - 1);

		}

		else if (source == rightBtn) { // 오른쪽 버튼 클릭
			if (nowSelected < trackList.size() - 1) {
				selectTrack(++nowSelected);

			} else
				selectTrack(nowSelected = 0);

		} else if (source == startBtn) {
			Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
			buttonEnteredMusic.start();
			gameStart(nowSelected);

		}

	}

	// 마우스가 영역에 진입했을 때
	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();

		Music btnEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
		btnEnteredMusic.start();

		if (source == exitBtn) {
			exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 손가락

		} else if (source == rightBtn) {
			rightBtn.setIcon(new ImageIcon(Main.class.getResource("../images/btn_right2.png")));
		} else if (source == leftBtn) {
			leftBtn.setIcon(new ImageIcon(Main.class.getResource("../images/btn_left2.png")));
		}

	}

	// 마우스를 뗐을 때
	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();

		if (source == rightBtn) {
			rightBtn.setIcon(new ImageIcon(Main.class.getResource("../images/btn_right.png")));
		} else if (source == leftBtn) {
			leftBtn.setIcon(new ImageIcon(Main.class.getResource("../images/btn_left.png")));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();

		if (source == exitBtn) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.exit(0); // 프로그램 종료
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	// 버튼 위치 지정 메소드 호출
	private void initLocateBtn() {
		locateExitBtn();
		locateStartBtn();
		locateRightBtn();
		locateLeftBtn();
	}

	/**
	 * exit버튼 위치 등 설정
	 */
	public void locateExitBtn() {
		exitBtn.setBounds(1170, 0, 120, 60);// 나가기 버튼의 위치와 크기설정 메뉴바의 가장 오른쪽에 위치
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false); // 생각한 형태로 이미지가 나오도록 설정
	}

	/**
	 * start버튼 위치 등 설정
	 */
	public void locateStartBtn() {
		startBtn.setVisible(false);
		startBtn.setBounds(1104, 540, 180, 180);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
	}

	/**
	 * right버튼 위치 등 설정
	 */
	public void locateRightBtn() {
		rightBtn.setVisible(false);
		rightBtn.setBounds(965, 310, 120, 120);
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setFocusPainted(false);
	}

	/**
	 * left버튼 위치 등 설정
	 */
	public void locateLeftBtn() {
		leftBtn.setVisible(false);
		leftBtn.setBounds(200, 310, 120, 120);
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setFocusPainted(false);
	}

}
