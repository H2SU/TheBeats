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

	private Button btns; //��ư��

	private Image background;
	private Image screenImage;
	private Image scoreImg;
	private Image selectedImg; // ���õ� �ٹ� �̹���
	private Image titleImg;

	private boolean isMainScreen = false; // ����ȭ������ ����
	private boolean isGameScreen = false; // ����ȭ������ ����
	private boolean isResultScreen = false; // ���ȭ������ ����

	private Music introMusic;
	private Music selectedMusic; // ���õ� ��

	private int nowSelected = 0; // ���� ���õ� �׸�
	private ArrayList<Track> trackList = new ArrayList<>(); // Ʈ�� ����Ʈ

	public TheBeat() {
		introMusic = new Music("intro.mp3", true);
		introMusic.start();
		addKeyListener(new MyKeyListener(this)); // Ű ������ ����ϸ鼭 ���� ��ü�� ������ ���̺���
		background = new ImageIcon(Main.class.getResource("../images/background_intro.png")).getImage();
		btns = new Button(this);
		setUndecorated(true);
		setFocusable(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);// ����� �̿��Ͽ� ��ü����â ũ�⼳��
		setResizable(false);// �� �� ������� ����â�� ����ڰ� ���Ƿ� ������ �� ����
		setLocationRelativeTo(null);// ����â�� ��ǻ�� ���߾ӿ� ��ġ�ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ������ ���������� ���α׷��� �����ϵ���
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);// ��ư�̳� JLabel�� ���� �� �� ��ġ�� ���̵��� ��
		setVisible(true);// ����â�� ȭ�鿡 ��µǵ��� ���� default�� false


		trackList.add(new Track("track_Little_Star.png", "track_little_Star1_title.png", "selectedImg.png", "Little_Star.mp3",
				"Little_Star.mp3", "littlestar"));
		trackList.add(new Track("track_cottoncandy.png", "track_cottoncandy_title.png", "selectedImg.png", "cottonCandy.mp3",
				"cottonCandy.mp3", "cottonCandy"));
		trackList.add(new Track("track_cottoncandy.png", "track_cottoncandy_title.png", "selectedImg.png", "Jingle_Bells.mp3",
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
	public void paint(Graphics g) { // JFrame�� ��ӹ��� Ŭ�������� ���� ù��°�� ȭ���� �׷��ִ� �޼ҵ�
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();// ��ũ���̹����� �̿��ؼ� �׷����� ����
		screenDraw((Graphics2D) screenGraphic);// ��ũ�� �׷��ȿ� �׸��� �׷���
		g.drawImage(screenImage, 0, 0, null);// ��ũ���̹����� (0,0)��ġ�� �׷���
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background�̹����� ��ü(��ũ���̹���)�� �׷���

		if (isMainScreen) { // ����ȭ���϶�.
			btns.changeBtnVisibility(true); // ��ư�� ���̰� �ϱ�.
			setFocusable(false);
			g.drawImage(selectedImg, 340, 100, null);// ���õ� ������ �̹���
			g.drawImage(titleImg, 340, 600, null);
		} else if (isGameScreen) {
			requestFocusInWindow();
			game.screenDraw(g);

		} else if (isResultScreen) {
			g.drawImage(scoreImg, 100, 150, null);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("Arial", Font.BOLD, 52));
			g.setColor(Color.white);
			g.drawString(String.valueOf(rank.getPerfect()), 920, 270);
			g.drawString(String.valueOf(rank.getGood()), 920, 360);
			g.drawString(String.valueOf(rank.getBad()), 920, 450);
			g.drawString(String.valueOf(rank.getMiss()), 920, 540);
		}
		paintComponents(g);// JLabel���� ���� JFrame ���� �߰��ϸ� �׷��� �׻� ������ ���� �׸��� ���
		this.repaint();// �ٽ� paint()�� �ҷ��� ��üȭ�� �̹����� �ż������� ���α׷��� ����Ǵ� �������� �׷���
	}

	/**
	 * ���� ���� �� ȣ��Ǵ� �޼ҵ�.
	 */
	public void enteredMainScreen() {
		if (introMusic != null)
			introMusic.close();

		isMainScreen = true;
		background = new ImageIcon(Main.class.getResource("../images/background_main.png")).getImage(); // ���ȭ��
		selectTrack(nowSelected);
	}

	/**
	 * Ʈ�� �����ϸ� ȣ��Ǵ� �޼ҵ�
	 *
	 * @param selected
	 */
	public void selectTrack(int selected) {
		if (selectedMusic != null) // ������ ���������� ����
			selectedMusic.close();

		selectedImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getAlbumImg()))
				.getImage();
		titleImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getTitleImg()))
				.getImage();
		selectedMusic = new Music(trackList.get(selected).getAlbumMusic(), true);
		selectedMusic.start(); // �̸���� ����

	}

	/**
	 * ���ӽ���
	 */
	public void startGame(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		isMainScreen = false;
		isGameScreen = true;
		btns.changeBtnVisibility(false); // ��ư �� �Ⱥ��̰� �ϱ�.

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImg()))
				.getImage();
		setFocusable(true);
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic(), this);
		game.start();
	}

	/**
	 * ���� ��
	 *
	 */
	public void endGame(Rank r) {
		rank = r;
		isGameScreen = false;
		isResultScreen = true;
		background = (new ImageIcon(Main.class.getResource("../images/background_result.png")).getImage());

		// ���� �̹��� ���
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
	 * ���콺 ������
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	// ���콺�� ������ �������� ��
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton source = (JButton) (e.getSource());
		source.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ����� ����.

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

	// ���콺�� ���� ��
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
			System.exit(0); // ���α׷� ����
		}

		else if (source == btns.getLeftBtn()) { // ���� ��ư Ŭ��
			if (nowSelected > 0) {
				selectTrack(--nowSelected);

			} else
				selectTrack(nowSelected = trackList.size() - 1);

		}

		else if (source == btns.getRightBtn()) { // ������ ��ư Ŭ��
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