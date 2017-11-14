package thebeats;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MyKeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_SPACE: 
			if (TheBeat.isMainScreen() == false && TheBeat.isGameScreen() == false) { // 인트로화면
				TheBeat.enteredMainScreen(); //메인 스크린 진입
			}
			break;
		case KeyEvent.VK_S:
			TheBeat.game.pressKey('S');
			break;
			
		case KeyEvent.VK_D:
			TheBeat.game.pressKey('D');
			break;

		case KeyEvent.VK_F:
			TheBeat.game.pressKey('F');
			break;
			
		case KeyEvent.VK_J:
			TheBeat.game.pressKey('J');
			break;
			
		case KeyEvent.VK_K:
			TheBeat.game.pressKey('K');
			break;
			
		case KeyEvent.VK_L:
			TheBeat.game.pressKey('L');
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(TheBeat.game == null)
			return;
		
		
		switch(keyCode){
		case KeyEvent.VK_S:
			TheBeat.game.releaseKey('S');
			break;
			
		case KeyEvent.VK_D:
			TheBeat.game.releaseKey('D');
			break;

		case KeyEvent.VK_F:
			TheBeat.game.releaseKey('F');
			break;
			
		case KeyEvent.VK_J:
			TheBeat.game.releaseKey('J');
			break;
			
		case KeyEvent.VK_K:
			TheBeat.game.releaseKey('K');
			break;
			
		case KeyEvent.VK_L:
			TheBeat.game.releaseKey('L');
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
