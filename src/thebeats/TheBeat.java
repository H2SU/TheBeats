package thebeats;

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

	Game game;
	Rank rank;

	private Graphics screenGraphic;

	private Button btns;
	private Screen screen; 
	
	private Image background;
	private Image screenImage;
	private Image scoreImg;
	private Image selectedImg;
	private Image titleImg;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	private boolean isResultScreen = false; 

	private Music introMusic;
	private Music selectedMusic; 

	private int nowSelected = 0; 
	private ArrayList<Track> trackList = new ArrayList<>(); 

	public TheBeat() {
		introMusic = new Music("intro.mp3", true);
		introMusic.start();
		addKeyListener(new MyKeyListener(this)); 
		background = new ImageIcon(Main.class.getResource("../images/background_intro.png")).getImage();
		btns = Button.getInstance();
		btns.setTheBeat(this); 
		btns.initButtons();
		screen = new Screen(this); 
		trackList.add(new Track("track_littlestar.png", "track_littlestar_title.png", "selectedImg.png",
				"littlestar.mp3", "littlestar.mp3", "littlestar"));
		trackList.add(new Track("track_cottoncandy.png", "track_cottoncandy_title.png", "selectedImg.png", "cottoncandy.mp3", "cottoncandy.mp3",
				"cottoncandy"));
		trackList.add(new Track("track_jinglebell.png", "track_jinglebell_title.png", "selectedImg.png","jinglebell.mp3","jinglebell.mp3",
				"jinglebell"));
	}

	
	public boolean isMainScreen() {
		return isMainScreen;
	}

	public boolean isGameScreen() {
		return isGameScreen;
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); 

		if (isMainScreen) { 
			btns.changeBtnVisibility(true); 
			setFocusable(false);
			g.drawImage(selectedImg, 340, 100, null);
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
		paintComponents(g);
		this.repaint();
	}


	public void enteredMainScreen() {
		if (introMusic != null)
			introMusic.close();

		isMainScreen = true;
		background = new ImageIcon(Main.class.getResource("../images/background_main.png")).getImage(); 
		selectTrack(nowSelected);
	}

	
	public void selectTrack(int selected) {
		if (selectedMusic != null)
			selectedMusic.close();

		selectedImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getAlbumImg()))
				.getImage();
		titleImg = new ImageIcon(Main.class.getResource("../images/" + trackList.get(selected).getTitleImg()))
				.getImage();
		selectedMusic = new Music(trackList.get(selected).getAlbumMusic(), true);
		selectedMusic.start(); 

	}

	public void startGame(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		isMainScreen = false;
		isGameScreen = true;
		btns.changeBtnVisibility(false); 

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImg()))
				.getImage();
		setFocusable(true);
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic(), this);
		game.start();
	}


	public void endGame(Rank r) {
		rank = r;
		isGameScreen = false;
		isResultScreen = true;
		background = (new ImageIcon(Main.class.getResource("../images/background_result.png")).getImage());

		// ???? ?´ë?¸ì? ?±ë?
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



	@Override
	public void mouseClicked(MouseEvent e) {

	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton source = (JButton) (e.getSource());
		source.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

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


	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();

		if (source == btns.getRightBtn()) {
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
			System.exit(0); 
		}

		else if (source == btns.getLeftBtn()) { 
			if (nowSelected > 0) {
				selectTrack(--nowSelected);

			} else
				selectTrack(nowSelected = trackList.size() - 1);

		}

		else if (source == btns.getRightBtn()) { 
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
