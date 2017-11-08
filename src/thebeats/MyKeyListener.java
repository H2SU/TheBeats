package thebeats;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MyKeyListener extends KeyAdapter {

	Music btnEnteredMusic;

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_SPACE: 
			if (TheBeat.isMainScreen() == false && TheBeat.isGameScreen() == false) { // 인트로
				btnEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				btnEnteredMusic.start();

				TheBeat.setMainScreen(true);
				TheBeat.setBackground(
						new ImageIcon(Main.class.getResource("../images/background_main.png")).getImage()); // 배경화면
			}
			break;
		case KeyEvent.VK_S:
			TheBeat.game.pressS();
			break;
			
		case KeyEvent.VK_D:
			TheBeat.game.pressD();
			break;

		case KeyEvent.VK_F:
			TheBeat.game.pressF();
			break;
			
		case KeyEvent.VK_J:
			TheBeat.game.pressJ();
			break;
			
		case KeyEvent.VK_K:
			TheBeat.game.pressK();
			break;
			
		case KeyEvent.VK_L:
			TheBeat.game.pressL();
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
			TheBeat.game.releaseS();
			break;
			
		case KeyEvent.VK_D:
			TheBeat.game.releaseD();
			break;

		case KeyEvent.VK_F:
			TheBeat.game.releaseF();
			break;
			
		case KeyEvent.VK_J:
			TheBeat.game.releaseJ();
			break;
			
		case KeyEvent.VK_K:
			TheBeat.game.releaseK();
			break;
			
		case KeyEvent.VK_L:
			TheBeat.game.releaseL();
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
