package thebeats;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Screen {
	
	TheBeat thebeat;

	Graphics screenGraphic;
	
	Image background;
	Image screenImage;
	Image backgroundImage;
	
	Screen(){}
	
	Screen(TheBeat thebeat){
		this.thebeat = thebeat;
		defaultScreenSetting();
	}
	
	
	

	
	private void defaultScreenSetting() {
		thebeat.setUndecorated(true);
		thebeat.setFocusable(true);
		thebeat.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		thebeat.setResizable(false);
		thebeat.setLocationRelativeTo(null);
		thebeat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thebeat.setBackground(new Color(0, 0, 0, 0));
		thebeat.setLayout(null);
		thebeat.setVisible(true);
	}
	
	
	

}
