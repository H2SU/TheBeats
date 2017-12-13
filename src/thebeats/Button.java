package thebeats;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {

	private TheBeat thebeat;
	private static final Button instance = new Button();

	private JButton startBtn;
	private JButton exitBtn;
	private JButton rightBtn;
	private JButton leftBtn;

	private Button() {
		this.exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exit.png")));
		this.startBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_start.png")));
		this.rightBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_right.png")));
		this.leftBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_left.png")));
	}

	public static Button getInstance() {
		return instance;
	}

	public void setTheBeat(TheBeat thebeat) {
		this.thebeat = thebeat;
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public JButton getExitBtn() {
		return exitBtn;
	}

	public JButton getRightBtn() {
		return rightBtn;
	}

	public JButton getLeftBtn() {
		return leftBtn;
	}

	public void initButtons() {
		initLocateBtn();
		initListener();
	}


	public void changeBtnVisibility(boolean state) {
		startBtn.setVisible(state);
		rightBtn.setVisible(state);
		leftBtn.setVisible(state);
	}

	private void initLocateBtn() {
		locateExitBtn();
		locateStartBtn();
		locateRightBtn();
		locateLeftBtn();
	}

	private void initListener() {
		exitBtn.addMouseListener(thebeat);
		startBtn.addMouseListener(thebeat);
		rightBtn.addMouseListener(thebeat);
		leftBtn.addMouseListener(thebeat);
	}


	private void locateExitBtn() {
		exitBtn.setBounds(1170, 0, 120, 60);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false); 
		thebeat.add(exitBtn);
	}


	private void locateStartBtn() {
		startBtn.setVisible(false);
		startBtn.setBounds(1104, 540, 180, 180);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		thebeat.add(startBtn);
	}


	private void locateRightBtn() {
		rightBtn.setVisible(false);
		rightBtn.setBounds(965, 310, 120, 120);
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setFocusPainted(false);
		thebeat.add(rightBtn);
	}


	private void locateLeftBtn() {
		leftBtn.setVisible(false);
		leftBtn.setBounds(200, 310, 120, 120);
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setFocusPainted(false);
		thebeat.add(leftBtn);
	}

}
