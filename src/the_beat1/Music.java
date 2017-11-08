package the_beat1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {//
	
	private Player player; //?ë°? ì¤? ?¼?´ë¸ŒëŸ¬ë¦? ì¤? ?•˜?‚˜
	private boolean isLoop;//?˜„?¬ ê³¡ì´ ë¬´í•œë°˜ë³µ?¸ì§? ?™•?¸?„ ?œ„?•œ ë³??ˆ˜
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {//?ƒ?„±? ?•´?‹¹ê³¡ì´ë¦„ê³¼ ë¬´í•œë°˜ë³µ?—¬ë¶?ë¥? ?ŒŒ?¼ë¯¸í„°ë¡? ë°›ëŠ”?‹¤
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); //music?´?” ?•„?˜ ?•´?‹¹?´ë¦„ì˜ ?ŒŒ?¼?„ ?‹¤?–‰ toURL()ë¡? ê·? ?œ„ì¹˜ë?? ê°?? ¸?˜¤?„ë¡? ?•¨
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //?•´?‹¹?ŒŒ?¼?„ ë²„í¼?— ?‹´?•„?„œ ?½?–´?˜¬ ?ˆ˜ ?ˆ?„ë¡í•¨
			player = new Player(bis); //?•´?‹¹ ?ŒŒ?¼?„ ?‹´?„ ?ˆ˜ ?ˆ?„ë¡? ?•¨
		} catch (Exception e) { //?˜¤ë¥˜ì²˜ë¦?
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //?˜„?¬ ?‹¤?–‰ ?Œ?•…?´ ?–´?–¤ ?œ„ì¹˜ì—?„œ ?‹¤?–‰?˜ê³? ?ˆ?Š”ì§? ?•Œ? ¤ì¤? 0.001ì´ˆë‹¨?œ„ë¡? ?•Œ? ¤ì¤? ?–¨?–´ì§??Š” ?…¸?Š¸ë¥? ë¶„ì„?•˜?Š” ?•¨?ˆ˜
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() { //?Œ?•…?´ ?–¸? œ ?‹¤?–‰?˜?˜ì§? ì¢…ë£Œ?•˜?„ë¡? ?•˜?Š” ?•¨?ˆ˜ 
		isLoop = false;   //?‚¬?š©?ê°? ?Œ?•…?‹¤?–‰ì¤? ?’¤ë¡œê?ê¸°ë?? ?ˆ„ë¥´ë”?¼?„ ?•´?‹¹ ê³¡ì˜ ?•ˆ? •? ?œ¼ë¡? ì¢…ë£Œ?•˜?„ë¡? ?•¨
		player.close();
		this.interrupt();  //?•´?‹¹ threadë¥? ì¤‘ì??ƒ?ƒœë¡? ë§Œë“¬
	}
	
	@Override
	public void run() { //threadë¥? ?ƒ?†ë°›ìœ¼ë©? ?‚¬?š©?•´?•¼?•˜?Š” ?•¨?ˆ˜
		try {
			do {
				player.play(); //ê³¡ì‹¤?–‰
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); //ë¬´í•œë°˜ë³µê²??‚¬
		} catch (Exception e) { //?˜¤ë¥˜ì²˜ë¦?
			System.out.println(e.getMessage());
		}
	}

}
