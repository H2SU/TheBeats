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
			if (thebeat.isMainScreen() == false && thebeat.isGameScreen() == false) { 
				thebeat.enteredMainScreen();
			}
			break;
		case KeyEvent.VK_S:
			thebeat.game.pressdKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.game.pressdKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.game.pressdKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.game.pressdKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.game.pressdKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.game.pressdKey('L');
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (thebeat.game == null)
			return;

		switch (keyCode) {
		case KeyEvent.VK_S:
			thebeat.game.releasedKey('S');
			break;

		case KeyEvent.VK_D:
			thebeat.game.releasedKey('D');
			break;

		case KeyEvent.VK_F:
			thebeat.game.releasedKey('F');
			break;

		case KeyEvent.VK_J:
			thebeat.game.releasedKey('J');
			break;

		case KeyEvent.VK_K:
			thebeat.game.releasedKey('K');
			break;

		case KeyEvent.VK_L:
			thebeat.game.releasedKey('L');
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
