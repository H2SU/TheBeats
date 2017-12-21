package the_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TheBeat extends JFrame implements MouseListener {

	private Game game;
	private Rank rank;

	private Graphics screenGraphic;

	private Button btns; //버튼들

	private Image background;
	private Image screenImage;
	private Image scoreImg;
	private Image selectedImg; // 선택된 앨범 이미지
	private Image titleImg;

	private boolean isMainScreen = false; // 메인화면인지 구분
	private boolean isGameScreen = false; // 게임화면인지 구분
	private boolean isResultScreen = false; // 결과화면인지 구분

	private Music introMusic;
	private Music selectedMusic; // 선택된 곡

	private int nowSelected = 0; // 현재 선택된 항목
	private ArrayList<Track> trackList = new ArrayList<>(); // 트랙 리스트

	public TheBeat() {
		introMusic = new Music("intro.mp3", true);
		introMusic.start();
		addKeyListener(new MyKeyListener(this)); // 키 리스너 등록하면서 현재 객체의 참조도 같이보냄
		background = new ImageIcon(Main.class.getResource("../images/background_intro.png")).getImage();
		btns = new Button(this);
		setUndecorated(true);
		setFocusable(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);// 상수를 이용하여 전체게임창 크기설정
		setResizable(false);// 한 번 만들어진 게임창을 사용자가 임의로 변경할 수 없음
		setLocationRelativeTo(null);// 게임창이 컴퓨터 정중앙에 위치하도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 게임을 종료했을때 프로그램도 종료하도록
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);// 버튼이나 JLabel을 했을 때 그 위치에 놓이도록 함
		setVisible(true);// 게임창이 화면에 출력되도록 설정 default는 false


		trackList.add(new Track("track_Little_Star.png", "track_little_Star1_title.png", "selectedImg.png", "Little_Star_cut.mp3",
				"Little_Star.mp3", "littlestar"));
		trackList.add(new Track("track_cottoncandy.png", "track_cottoncandy_title.png", "selectedImg.png", "cottonCandy.mp3",
				"cottonCandy.mp3", "cottonCandy"));
		trackList.add(new Track("track_Jingle_Bells.png", "track_Jingle_Bells_title.png", "selectedImg.png", "Jingle_Bells_cut.mp3",
				"Jingle_Bells.mp3", "Jingle_Bells"));

	}

	/*
	 * getter.
	 */
	public boolean isMainScreen() {
		return isMainScreen;
	}

	public boolean isGameScreen() {
		return isGameScreen;
	}

	public Game getGame() {
		return game;
	}


	@Override
	public void paint(Graphics g) { // JFrame을 상속받은 클래스에서 가장 첫번째로 화면을 그려주는 메소드
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();// 스크린이미지를 이용해서 그래픽을 얻어옴
		screenDraw((Graphics2D) screenGraphic);// 스크린 그래픽에 그림을 그려줌
		g.drawImage(screenImage, 0, 0, null);// 스크린이미지를 (0,0)위치에 그려줌
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background이미지를 전체(스크린이미지)에 그려줌

		if (isMainScreen) { // 메인화면일때.
			btns.changeBtnVisibility(true); // 버튼들 보이게 하기.
			setFocusable(false);
			g.drawImage(selectedImg, 340, 100, null);// 선택된 음악의 이미지
			g.drawImage(titleImg, 340, 600, null);
		} else if (isGameScreen) {
			requestFocusInWindow();
			game.screenDraw(g);

		} else if (isResultScreen) {
	         g.drawImage(scoreImg, 80, 150, null);
	         g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	         g.setFont(new Font("Arial", Font.BOLD, 50));
	         g.setColor(Color.white);
	         g.drawString(String.valueOf(rank.getPerfect()), 900, 270);
	         g.drawString(String.valueOf(rank.getGood()), 900, 350);
	         g.drawString(String.valueOf(rank.getBad()), 900, 430);
	         g.drawString(String.valueOf(rank.getMiss()), 900, 520);
	      }
		paintComponents(g);// JLabel같은 것을 JFrame 내에 추가하면 그려줌 항상 고정된 것을 그릴때 사용
		this.repaint();// 다시 paint()를 불러옴 전체화면 이미지를 매순간마다 프로그램이 종료되는 순간까지 그려줌
	}

	/**
	 * 메인 진입 시 호출되는 메소드.
	 */
	public void enteredMainScreen() {
		if (introMusic != null)
			introMusic.close();

		isMainScreen = true;
		background = new ImageIcon(Main.class.getResource("../images/background_main.png")).getImage(); // 배경화면
		selectTrack(nowSelected);
	}

	/**
	 * 트랙 선택하면 호출되는 메소드
	 *
	 * @param selected
	 */
	public void selectTrack(int selected) {
		if (selectedMusic != null) // 나오는 음악있으면 끄기
			selectedMusic.close();

		selectedImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getAlbumImg()))
				.getImage();
		titleImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getTitleImg()))
				.getImage();
		selectedMusic = new Music(trackList.get(selected).getAlbumMusic(), true);
		selectedMusic.start(); // 미리듣기 시작

	}

	/**
	 * 게임시작
	 */
	public void startGame(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		isMainScreen = false;
		isGameScreen = true;
		btns.changeBtnVisibility(false); // 버튼 다 안보이게 하기.

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
	public void endGame(Rank r) {
		rank = r;
		isGameScreen = false;
		isResultScreen = true;
		background = (new ImageIcon(Main.class.getResource("../images/background_result.png")).getImage());

		// 점수 이미지 등록
		switch (r.getScore()) {
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


	/**
	 * 마우스 리스너
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	// 마우스가 영역에 진입했을 때
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton source = (JButton) (e.getSource());
		source.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서모양 변경.

		Music btnEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
		btnEnteredMusic.start();

		if (source == btns.getStartBtn()) {
			btns.getStartBtn().setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (source == btns.getRightBtn()) {
			btns.getRightBtn().setIcon(new ImageIcon(Main.class.getResource("../images/btn_right2.png")));
		} else if (source == btns.getLeftBtn()) {
			btns.getLeftBtn().setIcon(new ImageIcon(Main.class.getResource("../images/btn_left2.png")));
		}

	}

	// 마우스를 뗐을 때
	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();

		if (source ==btns.getRightBtn()) {
			btns.getRightBtn().setIcon(new ImageIcon(Main.class.getResource("../images/btn_right.png")));
		} else if (source == btns.getLeftBtn()) {
			btns.getLeftBtn().setIcon(new ImageIcon(Main.class.getResource("../images/btn_left.png")));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();

		if (source == btns.getExitBtn()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.exit(0); // 프로그램 종료
		}

		else if (source == btns.getLeftBtn()) { // 왼쪽 버튼 클릭
			if (nowSelected > 0) {
				selectTrack(--nowSelected);

			} else
				selectTrack(nowSelected = trackList.size() - 1);

		}

		else if (source == btns.getRightBtn()) { // 오른쪽 버튼 클릭
			if (nowSelected < trackList.size() - 1) {
				selectTrack(++nowSelected);

			} else
				selectTrack(nowSelected = 0);

		} else if (source == btns.getStartBtn()) {
			Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
			buttonEnteredMusic.start();
			startGame(nowSelected);

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}



}