package thebeats;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {

	private TheBeat thebeat;

	private JButton startBtn;
	private JButton exitBtn;
	private JButton rightBtn;
	private JButton leftBtn;

	Button(TheBeat thebeat) {
		this.thebeat = thebeat;
		this.exitBtn = new JButton(new ImageIcon(Main.class.getResource("../images/exit.png")));
		this.startBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_start.png")));
		this.rightBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_right.png")));
		this.leftBtn = new JButton(new ImageIcon(Main.class.getResource("../images/btn_left.png")));

		initListener();
		initLocateBtn();
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

	// 버튼 가시상태 변경 메소드
	public void changeBtnVisibility(boolean state) {
		startBtn.setVisible(state);
		rightBtn.setVisible(state);
		leftBtn.setVisible(state);
	}

	// 버튼 위치 지정 메소드 호출
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

	/**
	 * exit버튼 위치 등 설정 후 add
	 */
	private void locateExitBtn() {
		exitBtn.setBounds(1170, 0, 120, 60);// 나가기 버튼의 위치와 크기설정 메뉴바의 가장 오른쪽에 위치
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false); // 생각한 형태로 이미지가 나오도록 설정
		thebeat.add(exitBtn);
	}

	/**
	 * start버튼 위치 등 설정 후 add
	 */
	private void locateStartBtn() {
		startBtn.setVisible(false);
		startBtn.setBounds(1104, 540, 180, 180);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		thebeat.add(startBtn);
	}

	/**
	 * right버튼 위치 등 설정 후 add
	 */
	private void locateRightBtn() {
		rightBtn.setVisible(false);
		rightBtn.setBounds(965, 310, 120, 120);
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setFocusPainted(false);
		thebeat.add(rightBtn);
	}

	/**
	 * left버튼 위치 등 설정 후 add
	 */
	private void locateLeftBtn() {
		leftBtn.setVisible(false);
		leftBtn.setBounds(200, 310, 120, 120);
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setFocusPainted(false);
		thebeat.add(leftBtn);
	}

}
