package thebeats;

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
			thebeat.getGame().pressKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.getGame().pressKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.getGame().pressKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.getGame().pressKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.getGame().pressKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.getGame().pressKey('L');
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
			thebeat.getGame().releaseKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.getGame().releaseKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.getGame().releaseKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.getGame().releaseKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.getGame().releaseKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.getGame().releaseKey('L');
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
