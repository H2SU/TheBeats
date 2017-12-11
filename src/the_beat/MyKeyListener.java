package the_beat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {

	TheBeat thebeat;

	public MyKeyListener(TheBeat thebeat) {
		this.thebeat = thebeat;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_SPACE:
			if (thebeat.isMainScreen() == false && thebeat.isGameScreen() == false) { // 인트로화면
				thebeat.enteredMainScreen(); // 메인 진입 메소드 호출
			}
			break;
		case KeyEvent.VK_S:
			thebeat.getGame().pressdKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.getGame().pressdKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.getGame().pressdKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.getGame().pressdKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.getGame().pressdKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.getGame().pressdKey('L');
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (thebeat.getGame() == null)
			return;

		switch (keyCode) {
		case KeyEvent.VK_S:
			thebeat.getGame().releasedKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.getGame().releasedKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.getGame().releasedKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.getGame().releasedKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.getGame().releasedKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.getGame().releasedKey('L');
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}